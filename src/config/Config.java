package config;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import config.exceptions.InvalidAircraftFormatException;
import config.exceptions.InvalidConfigurationException;

public class Config {
  public final int SimulationsCount;
  public final AircraftConfig[] aircrafts;

  public Config(String configPath) throws IOException, InvalidConfigurationException {

    List<String> lines = Files.readAllLines(Paths.get(configPath), UTF_8);
    
    try {
      this.SimulationsCount = Integer.parseInt(lines.get(0));
      lines.remove(0);
      this.aircrafts = parseAircrafts(lines);
    } catch (Exception e) {
      throw new InvalidConfigurationException();
    }
  }

  private AircraftConfig[] parseAircrafts(List<String> lines) throws InvalidAircraftFormatException {
    AircraftConfig[] aircrafts = new AircraftConfig[lines.size()];

    for (int i = 0; i < lines.size(); i++) {
      aircrafts[i] = parseAircraft(lines.get(i));
    }
    return aircrafts;
  }

  private AircraftConfig parseAircraft(String line) throws InvalidAircraftFormatException {
    String type;
    String name;
    int longitude;
    int latitude;
    int height;

    String[] args = line.split(" ");
    if (args.length != 5) {
      throw new InvalidAircraftFormatException();
    }

    type = args[0];
    name = args[1];

    try {
      longitude = Integer.parseInt(args[2]);
      latitude = Integer.parseInt(args[2]);
      height = Integer.parseInt(args[2]);
    } catch (Exception e) {
      throw new InvalidAircraftFormatException();
    }

    return new AircraftConfig(type, name, longitude, latitude, height);
  }

  private class AircraftConfig {
    public final String type;
    public final String name;
    public final int longitude;
    public final int latitude;
    public final int height;

    public AircraftConfig(String type, String name, int longitude, int latitude, int height) {
      this.type = type;
      this.name = name;
      this.longitude = longitude;
      this.latitude = latitude;
      this.height = height;
    }

    public String toString() {
      final String nl = System.lineSeparator();
      return 
      "type: " + type + nl +
      "name: " + name + nl +
      "longitude: " + longitude + nl +
      "latitude: " + latitude + nl +
      "height: " + height;
    }
  }

  public String toString() {
    String result = String.format(
    "Number of simulations = %d\n\n" +
    "Aircrafts:\n"
    , this.SimulationsCount);

    for (String aircraft: this.aircrafts) {
      result += aircraft.toString() + '\n';
    }

    return result;
  }
}
