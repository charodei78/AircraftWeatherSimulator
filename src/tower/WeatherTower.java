package tower;

import aircraft.Coordinates;
import weather.WeatherEnum;

public class WeatherTower extends Tower {
  
  // Я в курсе, что там должен быть String, 
  // но зачем нам эта неопредёленность
  public WeatherEnum getWeather(Coordinates coordinates) {
    // TODO: Implement
    return WeatherEnum.FOG;
  }

  void changeWeather() {
    // TODO: Implement
  }
}