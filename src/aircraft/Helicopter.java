package aircraft;

import tower.WeatherTower;
import weather.WeatherEnum;

public class Helicopter extends Aircraft implements Flyable{
  private WeatherTower weatherTower;

  Helicopter(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  public void updateConditions() {
    WeatherEnum weather = this.weatherTower.getWeather(this.coordinates);
    switch (weather) {
      //  (latitude,  longitude,  height)
      case SUN:
        this.logMessage("Sun! Right in the eye!");
        this.move(0, 10, 2);
        break;
      case RAIN:
        this.logMessage("Rain! I'm slowing down!");
        this.move(0, 5, 0);
        break;
      case FOG:
        this.logMessage("Fog! Where's the damn hedgehog!");
        this.move(0, 1, 0);
        break;
      case SNOW:
        this.logMessage("Snow! The screws are freezing! I'm going down!");
        this.move(0, 0, -12);
        break;
    };
    if (this.coordinates.getHeight() <= 0) {
      this.weatherTower.unregister(this);
    }
  }

  public void registerTower(WeatherTower weatherTower) {
    this.weatherTower = weatherTower;
    weatherTower.register(this);
  }  
}
