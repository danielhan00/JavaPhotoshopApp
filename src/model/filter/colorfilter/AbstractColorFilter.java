package model.filter.colorfilter;

import model.filter.IFilter;
import model.image.IImage;

/**
 * Represents a color filter operation using a color array and provided image. This abstract class
 * is a filter.
 */
public abstract class AbstractColorFilter implements IFilter {

  /**
   * Applies a colorArray filter operation to every RGB channel of each pixel in the
   * provided picture.
   * @param colorArray The 2D array representing the channel color RGB value modifications.
   * @param picture The IImage to be modified by the colorArray.
   * @return The modified image.
   */
  abstract IImage applyColor(double[][] colorArray, IImage picture) throws IllegalArgumentException;

}
