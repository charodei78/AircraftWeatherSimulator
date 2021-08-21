package aircraft;

public abstract class Aircraft {
  private static long idCounter = 1;

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

  
  protected void logMessage(String message) {
    String log = String.format(
      "%s: %s",
      this.getFullName(),      
      message
    );

    System.out.println(log);
  }

  public String getFullName() {
    return String.format(
      "%s#%s(%d)", 
      this.getClass().getSimpleName(),
      this.name,
      this.id
    );
  }

  protected void move(int latitude, int longitude, int height) {
    int newLatitude = this.coordinates.getLatitude() + latitude;
    int newLongitude = this.coordinates.getLongitude() + longitude;
    int newHeight = this.coordinates.getHeight() + height;

    this.coordinates = new Coordinates(newLatitude, newLongitude, newHeight);
  }

}
