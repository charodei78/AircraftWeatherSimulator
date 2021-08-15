package config;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Config {
  public int SimulationsCount;
  public List<String> aircrafts;

  public Config(String configPath) throws IOException, NumberFormatException {

    List<String> lines = Files.readAllLines(Paths.get(configPath), UTF_8);
    
    int i = 0;
    this.SimulationsCount = Integer.parseInt(lines.get(i));
    lines.remove(i);
    this.aircrafts = lines;
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
