package aircraft;

import tower.WeatherTower;
import weather.WeatherEnum;

public class JetPlane extends Aircraft implements Flyable{
  private WeatherTower weatherTower;

  JetPlane(String name, Coordinates coordinates) {
    super(name, coordinates);
  }

  public void updateConditions() {
    WeatherEnum weather = this.weatherTower.getWeather(this.coordinates);
    switch (weather) {
      //  (latitude,  longitude,  height)
      case SUN:
        this.logMessage("Sun! I'm speeding up!");
        this.move(10, 0, 2);
        break;
      case RAIN:
        this.logMessage("Rain... Drops falling from the sky like bullets.");
        this.move(5, 0, 0);
        break;
      case FOG:
        this.logMessage("Fog... Really, where am I!");
        this.move(1, 0, 0);
        break;
      case SNOW:
        this.logMessage("Happy New Year!");
        this.move(0, 0, -7);
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
