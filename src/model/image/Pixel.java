package model.image;

/**
 * A representation of a pixel.
 */
public class Pixel {
  private int posnX;
  private int posnY;
  private int red;
  private int green;
  private int blue;

  /**
   * Constructor for a pixel object.
   * @param posnX x position for the Pixel.
   * @param posnY y position for the Pixel.
   * @param red red value of the Pixel.
   * @param green green value of the Pixel.
   * @param blue blue value of the Pixel.
   */
  public Pixel(int posnX, int posnY, int red, int green, int blue) {

    this.posnX = posnX;
    this.posnY = posnY;

    // clamping
    if (red < 0) {
      this.red = 0;
    }
    else if (red > 255) {
      this.red = 255;
    }
    else {
      this.red = red;
    }

    if (green < 0) {
      this.green = 0;
    }
    else if (green > 255) {
      this.green = 255;
    }
    else {
      this.green = green;
    }

    if (blue < 0) {
      this.blue = 0;
    }
    else if (blue > 255) {
      this.blue = 255;
    }
    else {
      this.blue = blue;
    }
  }

  /**
   * Getter to return x position of a Pixel.
   * @return int (posnX)
   */
  public int getPosnX() {
    return posnX;
  }

  /**
   * Getter to return y position of a Pixel.
   * @return int (posnY)
   */
  public int getPosnY() {
    return posnY;
  }

  /**
   * Getter to return red value of a Pixel.
   * @return int
   */
  public int getRed() {
    return red;
  }

  /**
   * Getter to return green value of a Pixel.
   * @return int
   */
  public int getGreen() {
    return green;
  }

  /**
   * Getter to return blue value of a Pixel.
   * @return int
   */
  public int getBlue() {
    return blue;
  }


}
