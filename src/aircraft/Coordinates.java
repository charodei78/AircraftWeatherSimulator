package aircraft;


public class Coordinates {
  public final int MAX_HEIGHT = 100; // by TS 
  public final int MIN_HEIGHT = 100; // by TS 
  public final int MAX_LATITUDE = 90;
  public final int MAX_LONGITUDE = 180;


  private int longitude;
  private int latitude;
  private int height;

  /**
   * Coordinates constructor.
   * 
   * @param longitude
   * @param latitude
   * @param height
   */
  Coordinates(int longitude, int latitude, int height) {
    this.longitude = normalizeLongitude(longitude);
    this.latitude = normalizeLatitude(latitude);
    this.height = normalizeHeight(height);
  };

  /**
   * @param height
   * @return normalized height
   */
  private int normalizeHeight(int height) {
    if (height > MAX_HEIGHT) {
      return MAX_HEIGHT;
    }
    if (height < MIN_HEIGHT) {
      return MIN_HEIGHT;
    }
    return height;
  }

  /**
   * @param latitude
   * @return normalized latitude (< 90)
   */
  private int normalizeLatitude(int latitude) {
    if (latitude < 0) {
      latitude *= -1;
    }

    latitude %= MAX_LATITUDE * 2; // latitude in 2 hemispheres 
    if (latitude > MAX_LATITUDE) {
      latitude = MAX_LATITUDE - (latitude % MAX_LATITUDE);
    }
    return latitude;
  }

  /**
   * @param longitude
   * @return normalized longitude (< 180)
   */
  private int normalizeLongitude(int longitude) {
    if (longitude < 0) {
      longitude *= -1;
    }

    longitude %= MAX_LONGITUDE * 2; // longitude in 2 hemispheres 
    if (longitude > MAX_LONGITUDE) {
      longitude = MAX_LONGITUDE - (longitude % MAX_LONGITUDE);
    }
    return longitude;
  }

  public int getLongitude() {
    return longitude;
  }

  public int getLatitude() {
    return latitude;
  }

  public int getHeight() {
    return height;
  }
}
