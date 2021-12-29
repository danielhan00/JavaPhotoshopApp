package model.filter;

import model.image.IImage;

/**
 * An interface to represent operations on an image.
 */
public interface IFilter {

  /**
   * Apply the filter to the picture object and return it.
   * @param picture Takes in the picture that the filter is applied to.
   * @return An IIMage with the filter applied to the original picture.
   */
  IImage apply(IImage picture);
}
