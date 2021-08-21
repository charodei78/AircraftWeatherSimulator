import config.Config;
import config.Config.AircraftConfig;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import aircraft.AircraftFactory;
import aircraft.Flyable;
import config.exceptions.InvalidConfigurationException;
import tower.WeatherTower;

public class App {
  public static void main(String[] args) throws Exception {
    if (args.length != 1) {
      throw new IllegalArgumentException();
    }

    try {
      start(args[0]);
    } catch (IOException e) {
      System.out.println("Файл " + args[0] + " не найден");
    } catch (InvalidConfigurationException e) {
      System.out.println("Ошибка конфигурации " + args[0]);
    }
  }

  private static List<Flyable> getFlyables(AircraftConfig[] configs) {
    List<Flyable> flyableList = new  ArrayList<Flyable>(configs.length);

    for (AircraftConfig aircraftConfig: configs) {
      final Flyable flyable = AircraftFactory.newAircraft(
        aircraftConfig.type,
        aircraftConfig.name,
        aircraftConfig.longitude,
        aircraftConfig.latitude,
        aircraftConfig.height);

        flyableList.add(flyable);
    }

    return flyableList;
  }

  private static void start(String configPath) throws IOException, InvalidConfigurationException {
    Config config = new Config(configPath);
    
    WeatherTower weatherTower = new WeatherTower();
    List<Flyable> flyableList = getFlyables(config.aircrafts);
    for (Flyable flyable : flyableList) {
      flyable.registerTower(weatherTower);
    }

    for (int i = 0; i < config.numberOfSimulations; i++) {
      weatherTower.simulateWeather();
    }
  }
}
