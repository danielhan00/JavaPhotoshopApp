package model.image.programmatic;

import java.awt.Color;
import model.image.IImage;
import model.image.Pixel;

/**
 * A programmatic way to represent a checkerboard image.
 */
public class Checkerboard implements IImage {

  private int numTiles;
  private int width;
  private int height;
  private int maxValue;
  private Pixel[][] checkerboard;

  /**
   * Constructor for a Checkerboard IImage object.
   *
   * @param sizeSquareTile The size of the square tile - 1 unit = 1 pixel.
   * @param numTiles The number of tiles in the checkerboard. Must be a valid square number to make
   *                 a valid checkerboard.
   * @param color1 The color of the first square on the checkerboard.
   * @param color2 The color of the second square on the checkerboard.
   */
  public Checkerboard(int sizeSquareTile, int numTiles, Color color1, Color color2) {

    // data integrity checks
    if (color1 == null || color2 == null) {
      throw new IllegalArgumentException("Null values...");
    }
    if (sizeSquareTile < 1) {
      throw new IllegalArgumentException("Invalid sizeSquareTile");
    }

    //
    int x = (int) Math.sqrt(numTiles);
    if (Math.pow(x,2) != numTiles || numTiles < 1) {
      throw new IllegalArgumentException("Invalid numTiles");
    }

    this.numTiles = numTiles;
    this.width = sizeSquareTile * (int) Math.sqrt(numTiles);
    this.height = sizeSquareTile * (int) Math.sqrt(numTiles);
    this.checkerboard = createBoard(sizeSquareTile, color1, color2);
    this.maxValue = setMaxValue(color1, color2);
  }

  /**
   * Creates the checkerboard Image object itself.
   *
   * @param sizeSquareTile size of length/width of each square tile.
   * @param color1         Color1 to make checkerboard
   * @param color2         Color2 to make checkerboard
   * @return Pixel[][] The pixel array of the board.
   */
  private Pixel[][] createBoard(int sizeSquareTile,
      Color color1, Color color2) {

    // size of a tile 100 means a 100 x 100 pixel square
    int color1Red = color1.getRed();
    int color1Green = color1.getGreen();
    int color1Blue = color1.getBlue();
    int color2Red = color2.getRed();
    int color2Green = color2.getGreen();
    int color2Blue = color2.getBlue();
    Pixel[][] outputArray = new Pixel[width][height];
    boolean countSwitchWidth = true;
    int counterWidth = 0;
    int counterHeight = 0;

    // if we're dealing with an even checkerboard, it looks different than an odd one.
    if (Math.sqrt(numTiles) % 2 == 0) {

      // iterate through all the pixels, switch when you get to a new block and
      // when it's appropriate.
      for (int i = 0; i < height; i++) {
        if (counterHeight % sizeSquareTile == 0 && counterHeight >= sizeSquareTile) {
          countSwitchWidth = !countSwitchWidth;
        }
        counterHeight++;
        for (int j = 0; j < width; j++) {
          if (countSwitchWidth) {
            outputArray[j][i] = new Pixel(j, i, color1Red, color1Green, color1Blue);
          } else {
            outputArray[j][i] = new Pixel(j, i, color2Red, color2Green, color2Blue);
          }
          counterWidth++;
          if (counterWidth % sizeSquareTile == 0 && counterWidth >= sizeSquareTile) {
            countSwitchWidth = !countSwitchWidth;
          }
        }
      }
    }

    // odd number of squares on the checkerboard.
    else {

      // iterate through all the pixels, switch when you get to a new block and
      // when it's appropriate.
      for (int i = 0; i < height; i++) {
        counterHeight++;
        if (counterHeight % sizeSquareTile != 0) {
          countSwitchWidth = !countSwitchWidth;
        }

        for (int j = 0; j < width; j++) {
          if (countSwitchWidth) {
            outputArray[j][i] = new Pixel(j, i, color2Red, color2Green, color2Blue);
          } else {
            outputArray[j][i] = new Pixel(j, i, color1Red, color1Green, color1Blue);
          }
          counterWidth++;
          if (counterWidth % sizeSquareTile == 0 && counterWidth >= sizeSquareTile) {
            countSwitchWidth = !countSwitchWidth;

          }

        }
      }
    }

    // return the updated pixel array.
    return outputArray;
  }

  /**
   * Method to find the max color value of an image in total.
   *
   * @param color1 Color1 in checkerboard
   * @param color2 Color2 in checkerboard
   * @return int The highest color value on the board.
   */
  private int setMaxValue(Color color1, Color color2) {
    int max = 0;

    if (color1.getRed() > max) {
      max = color1.getRed();
    }
    if (color1.getGreen() > max) {
      max = color1.getGreen();
    }
    if (color1.getBlue() > max) {
      max = color1.getBlue();
    }
    if (color2.getRed() > max) {
      max = color2.getRed();
    }
    if (color2.getGreen() > max) {
      max = color2.getGreen();
    }
    if (color2.getBlue() > max) {
      max = color2.getBlue();
    }

    return max;
  }

  /**
   * Getter for the pixel width of the checkerboard.
   * @return The pixel width as an int.
   */
  @Override
  public int getWidth() {
    return width;
  }

  /**
   * Getter for the pixel height of the checkerboard.
   * @return The pixel height as an int.
   */
  @Override
  public int getHeight() {
    return height;
  }

  /**
   * Getter for the max value color of the checkerboard.
   * @return The max color value as an int.
   */
  @Override
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Getter for the pixel array of the checkerboard.
   * @return The 2D pixel array of the checkerboard.
   */
  @Override
  public Pixel[][] getImage() {
    return checkerboard;
  }

  /**
   * Getter for a specific pixel at a given width and height.
   * @param width x width of the Pixel.
   * @param height y height of the Pixel.
   * @return The found pixel.
   */
  @Override
  public Pixel getPixelAt(int width, int height) {

    try {
      return checkerboard[width][height];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Could not find pixel at given width/height.");
    }
  }
}
