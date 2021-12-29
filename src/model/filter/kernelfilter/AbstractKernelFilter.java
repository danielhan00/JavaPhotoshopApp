package model.filter.kernelfilter;

import model.filter.IFilter;
import model.image.IImage;

/**
 * Represents a Filter operation that relies on a Kernel.
 */
public abstract class AbstractKernelFilter implements IFilter {

  /**
   * Applies a give kernel to a given image.
   * @param kernel The kernel filter to apply.
   * @param picture The image to reference when creating our new Image.
   * @return IImage, the updated Image.
   * @throws IllegalArgumentException if the Kernel is invalid.
   */
  abstract IImage applyKernel(double[][] kernel, IImage picture) throws IllegalArgumentException;

  /**
   * Returns a boolean, true if the kernel is Valid, false if not.
   * @param kernel The kernel to check
   * @return boolean
   */
  abstract boolean validKernel(double[][] kernel);
}
