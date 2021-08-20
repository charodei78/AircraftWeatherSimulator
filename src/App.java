import aircraft.Coordinates;
import config.Config;
import weather.WeatherProvider;
import java.io.IOException;
import config.exceptions.InvalidConfigurationException;

public class App {
  public static int main(String[] args) throws Exception {
    if (args.length != 1) {
      throw new IllegalArgumentException();
    }

    
    try {
      Config config = new Config(args[0]);
      System.out.println(config);
    } catch (IOException e) {
      System.out.println("Файл " + args[0] + " не найден");
      return 1;
    } catch (InvalidConfigurationException e) {
      System.out.println("Ошибка конфигурации " + args[0]);
      return 1;
    }
    return 0;
  }
}
