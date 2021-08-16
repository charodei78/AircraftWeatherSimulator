package tower;
import java.util.ArrayList;
import java.util.List;

import aircraft.flyable.Flyable;

public abstract class Tower {
  private List<Flyable> observers = new ArrayList<Flyable>();
  
  public void register(Flyable flyable) {
    this.observers.add(flyable);
  }

  public void unregister(Flyable flyable) {
    this.observers.remove(flyable);
  }

  protected void conditionsChanged() {
    for (Flyable flyable : observers) {
      flyable.updateConditions();
    }
  }
}
