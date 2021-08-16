package aircraft;

public abstract class Aircraft {
  private static long idCounter = 0;

  protected long id;
  protected String name;
  protected Coordinates coordinates;
  
  protected Aircraft(String name, Coordinates coordinates) {
    this.id = this.nextId();
    this.name = name;
    this.coordinates = coordinates;
  }

  private long nextId() {
    return idCounter++;
  };

}
