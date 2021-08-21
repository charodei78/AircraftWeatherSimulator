package aircraft;

import tower.WeatherTower;
import weather.WeatherEnum;

public class Baloon extends Aircraft implements Flyable {
  private WeatherTower weatherTower;

  Baloon(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  public void updateConditions() {
    WeatherEnum weather = this.weatherTower.getWeather(this.coordinates);
    switch (weather) {
      //  (latitude,  longitude,  height)
      case SUN:
        this.logMessage("I believe I can touch the sky!");
        this.move(0, 2, 4);
        break;
      case RAIN:
        this.logMessage("Rain! Visibility is zero!");
        this.move(0, 0, -5);
        break;
      case FOG:
        this.logMessage("Fog! Where's the damn horse!");
        this.move(0, 0, -3);
        break;
      case SNOW:
        this.logMessage("Snow! Really?! We're falling!");
        this.move(0, 0, -15);
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
