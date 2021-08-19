package weather;

import aircraft.Coordinates;

public class WeatherProvider {
  private static final WeatherEnum[] weather = WeatherEnum.values();
  private static WeatherProvider weatherProvider;

  private WeatherProvider() {}

  public static WeatherProvider getProvider() {
    if (WeatherProvider.weatherProvider == null) {
      WeatherProvider.weatherProvider = new WeatherProvider();
    }
    return WeatherProvider.weatherProvider;
  }

  private WeatherEnum randomizeWeather(Coordinates coordinates) {
    double sunChance = getSunChance(coordinates);
    double rainChance = getRainChance(coordinates);
    double fogChance = getFogChance(coordinates);
    double snowChance = getSnowChance(coordinates);

    double chanceSum = sunChance + rainChance + fogChance + snowChance;
    double randomWeather = Math.random() * chanceSum;

    if (randomWeather <= sunChance) {
      return WeatherEnum.SUN;
    }
    if (randomWeather <= rainChance + sunChance) {
      return WeatherEnum.RAIN;
    } 
    if (randomWeather <= rainChance + sunChance + fogChance) {
      return WeatherEnum.FOG;
    }
    return WeatherEnum.SNOW;
  }

  public WeatherEnum getCurrentWeather(Coordinates coordinates) {
   return randomizeWeather(coordinates);    
  }

  private double getSnowChance(Coordinates coordinates) {
    double chance = (double)coordinates.getLatitude() / (double)110;
    if (coordinates.getHeight() > 70) {
      chance += 0.1;
    }
    return chance;
  }

  private double getSunChance(Coordinates coordinates) {
    return 1 - (double)coordinates.getLatitude() / (double)110;
  }

  private double getFogChance(Coordinates coordinates) {
    int latitude = coordinates.getLatitude();
    if (latitude > 70) {
      return 0.5 / (latitude - 70);
    }
    if (latitude < 30) {
      return 0.5 / (latitude - 70);
    }
    return 0.2;
  }

  private double getRainChance(Coordinates coordinates) {
    int latitude = coordinates.getLatitude();
    if (latitude > 80 || latitude < 20) {
      return 0.05;
    }
    return 0.3;
  }
}
