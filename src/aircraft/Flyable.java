package aircraft;
import tower.WeatherTower;

public interface Flyable {
  public void updateConditions();
  public void registerTower(WeatherTower weatherTower);
  public String getFullName();
}