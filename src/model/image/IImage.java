package model.image;


/**
 * An interface for representation of an Image.
 */
public interface IImage {

  /**
   * Gets the width of an image.
   * @return int
   */
  int getWidth();

  /**
   * Gets the height of an image.
   * @return int
   */
  int getHeight();

  /**
   * Gets the maxValue of colors in an Image.
   * @return int
   */
  int getMaxValue();

  /**
   * Gets the 2D array that stores the pixel information of an Image.
   * @return Pixel[][]
   */
  Pixel[][] getImage();

  /**
   * Gets a pixel at a specific position in an Image object.
   * @param width x width of the Pixel.
   * @param height y height of the Pixel.
   * @return Pixel
   */
  Pixel getPixelAt(int width, int height);
}
