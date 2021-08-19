import aircraft.Coordinates;
import config.Config;
import weather.WeatherProvider;

public class App {
  public static void main(String[] args) throws Exception {
    // if (args.length != 1) {
    //   throw new IllegalArgumentException();
    // }

    WeatherProvider weatherProvider = WeatherProvider.getProvider();

    int i = 100;
    while (i-- != 0) {
      Coordinates coordinates = Coordinates.mock();
      System.out.println(coordinates);
      System.out.println(weatherProvider.getCurrentWeather(coordinates));
    }
        
    // try {
    //   Config config = new Config(args[0]);
    //   System.out.println(config);
    // } catch (Exception e) {
    //   System.out.println("Файл " + args[0] + " не найден");
    //   return;
    // }
  }
}
