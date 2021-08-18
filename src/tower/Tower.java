package tower;
import java.util.ArrayList;
import java.util.List;

import aircraft.flyable.Flyable;

public abstract class Tower {
  private List<Flyable> observers = new ArrayList<Flyable>();
  
  private void logMessage(String message) {
    System.out.println("Tower says: " + message);
  }

  public void register(Flyable flyable) {
    boolean isDone = this.observers.add(flyable);
    if (isDone) {
      this.logMessage(flyable.getFullName() + " registered to weather tower.");
    }
  }

  public void unregister(Flyable flyable) {
    boolean isDone = this.observers.remove(flyable);
    if (isDone) {
      this.logMessage(flyable.getFullName() + " unregistered from weather tower.");
    }
  }

  protected void conditionsChanged() {
    for (Flyable flyable : observers) {
      flyable.updateConditions();
    }
  }
}
