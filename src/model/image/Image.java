package model.image;

import model.image.IImage;
import model.image.Pixel;

/**
 * A class to represent an image of a certain width and height.
 */
public class Image implements IImage {
  private int width;
  private int height;
  private int maxValue;
  private Pixel[][] wholeImage;

  /**
   * An empty Image constructor for initialization.
   */
  public Image() {
    // empty constructor to create an image object and use the provided methods.
  }

  /**
   * Constructor to create an Image object.
   * @param width width of the image in pixels
   * @param height height of the image in pixels.
   * @param maxValue maxValue of a color channel in an Image.
   */
  public Image(int width, int height, int maxValue, Pixel[][] wholeImage) {

    if (wholeImage == null) {
      throw new IllegalArgumentException("Cannot create an image with a null pixel array.");
    }

    this.width = width;
    this.height = height;
    this.maxValue = maxValue;
    this.wholeImage = wholeImage;
  }


  /**
   * Gets the pixel width of an image.
   * @return the pixel width as an int.
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Gets the pixel height of an image.
   * @return the pixel height as an int.
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Gets the maxValue of the colors in an Image.
   * @return the max color value as an int.
   */
  @Override
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Gets the 2D array that stores the pixel information of an Image.
   * @return The 2D Pixel array - Pixel[][]
   */
  @Override
  public Pixel[][] getImage() {
    return wholeImage;
  }

  /**
   * Gets a pixel at a specific position in an Image object.
   * @param width x width of the Pixel.
   * @param height y height of the Pixel.
   * @return The desired pixel.
   */
  @Override
  public Pixel getPixelAt(int width, int height) {
    try {
      return wholeImage[width][height];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Could not find pixel at given width/height.");
    }

  }
}
