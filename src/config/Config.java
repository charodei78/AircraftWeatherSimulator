package config;

import static java.nio.charset.StandardCharsets.UTF_8;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import config.exceptions.InvalidAircraftFormatException;
import config.exceptions.InvalidConfigurationException;

/**
 * Class that parse and store simulation config
 */
public class Config {
  // number of times a weather change is triggered
  public final int numberOfSimulations;
  // deserialized aircrafts configurations
  public final AircraftConfig[] aircrafts;

  public Config(String configPath) throws IOException, InvalidConfigurationException {

    List<String> lines = Files.readAllLines(Paths.get(configPath), UTF_8);
    
    try {
      this.numberOfSimulations = Integer.parseInt(lines.get(0));
      lines.remove(0);
      this.aircrafts = parseAircrafts(lines);
    } catch (Exception e) {
      throw new InvalidConfigurationException();
    }
  }

  /**
   * Function for deserialize aircrafts from list of string 
   * formatted as "TYPE NAME LONGITUDE LATITUDE HEIGHT"
   * 
   * @param lines
   * @return Deserialized aircrafts
   * @throws InvalidAircraftFormatException
   */
  private AircraftConfig[] parseAircrafts(List<String> lines) throws InvalidAircraftFormatException {
    AircraftConfig[] aircrafts = new AircraftConfig[lines.size()];

    for (int i = 0; i < lines.size(); i++) {
      aircrafts[i] = parseAircraft(lines.get(i));
    }
    return aircrafts;
  }

  /**
   * Function for deserialize aircraft from string 
   * formatted as "TYPE NAME LONGITUDE LATITUDE HEIGHT"
   * 
   * @param line line for parse
   * @return Deserialized aircraft
   * @throws InvalidAircraftFormatException
   */
  private AircraftConfig parseAircraft(String line) throws InvalidAircraftFormatException {
    String type;
    String name;
    int longitude;
    int latitude;
    int height;

    String[] args = line.split(" ");
    if (args.length != 5 || args[0].length() < 1 || args[1].length() < 1) {
      throw new InvalidAircraftFormatException();
    }

    type = args[0];
    name = args[1];

    try {
      longitude = Integer.parseInt(args[2]);
      latitude = Integer.parseInt(args[3]);
      height = Integer.parseInt(args[4]);
    } catch (Exception e) {
      throw new InvalidAircraftFormatException();
    }

    return new AircraftConfig(type, name, longitude, latitude, height);
  }

  public String toString() {
    final String nl = System.lineSeparator();
    String result = String.format(
    "Number of simulations = %d" + nl + nl +
    "Aircrafts: " + nl
    , this.numberOfSimulations);

    for (AircraftConfig aircraft: this.aircrafts) {
      result += aircraft.toString() + nl + nl;
    }

    return result;
  }
  
  /**
   * Class for store aircraft configuration
   */
  public class AircraftConfig {
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
}
