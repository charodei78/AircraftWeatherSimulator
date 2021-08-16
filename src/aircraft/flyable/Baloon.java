package aircraft.flyable;

import aircraft.Aircraft;
import aircraft.Coordinates;
import tower.WeatherTower;
import weather.WeatherEnum;

public class Baloon extends Aircraft implements Flyable {
  private WeatherTower weatherTower;

  Baloon(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  public void updateConditions() {
    WeatherEnum weather = this.weatherTower.getWeather(this.coordinates);
    // TODO: Implement
  }

  public void registerTower(WeatherTower weatherTower) {
    weatherTower.register(this);
  }
}
