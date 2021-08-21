package tower;

import aircraft.Coordinates;
import weather.WeatherEnum;
import weather.WeatherProvider;

public class WeatherTower extends Tower {
  
  // Get weather via coordinates
  public WeatherEnum getWeather(Coordinates coordinates) {
    return WeatherProvider.getProvider().getCurrentWeather(coordinates);
  }

  void changeWeather() {
    conditionsChanged();
  }

  public void simulateWeather() {
    changeWeather();
  }
}