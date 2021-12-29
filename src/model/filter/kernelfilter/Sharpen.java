package model.filter.kernelfilter;

import model.image.IImage;
import model.image.Image;
import model.image.Pixel;

/**
 * A class to sharpen an image.
 */
public class Sharpen extends AbstractKernelFilter {

  /**
   * Constructor to create the sharpen object to use with the picture.
   */
  public Sharpen() {
    // empty constructor, no fields. Gives us access to see Sharpen elsewhere.
  }

  /**
   * Take the image and put a sharpen filter on it.
   *
   * @param picture Takes in an IImage to have a filter added.
   * @return The picture after the filter is added.
   */
  @Override
  public IImage apply(IImage picture) {
    if (picture == null) {
      throw new IllegalArgumentException("Cannot greyscale a null picture.");
    }

    double[][] kernel = {
        {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
        {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
        {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
        {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
        {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0}
    };

    return applyKernel(kernel, picture);
  }


  @Override
  public IImage applyKernel(double[][] kernel, IImage picture) throws IllegalArgumentException {

    if (!validKernel(kernel)) {
      throw new IllegalArgumentException("Invalid kernel.");
    }

    int imageWidth = picture.getWidth();
    int imageHeight = picture.getHeight();
    Pixel[][] changedImage = new Pixel[imageWidth][imageHeight];
    int redChannel = 0;
    int blueChannel = 0;
    int greenChannel = 0;

    // this finds the pixels that are around the pixel we're currently on.
    // used in our for loop to make sure we're checking every pixel that's required by the kernel.
    int h1 = -1 * kernel.length / 2;
    int w1 = -1 * kernel.length / 2;
    int h2 = kernel.length / 2;
    int w2 = kernel.length / 2;

    for (int i = 0; i < imageHeight; i++) {
      for (int j = 0; j < imageWidth; j++) {

        // checking around the center pixel
        for (int h = h1; h < kernel.length - h2; h++) {
          for (int w = w1; w < kernel.length - w2; w++) {

            try {
              redChannel += (int) (kernel[w + w2][h + h2] * picture.getPixelAt(
                  j + w, h + i).getRed());
            } catch (IllegalArgumentException e) {
              redChannel += 0;
            }

            try {
              blueChannel += (int) (kernel[w + w2][h + h2] * picture.getPixelAt(
                  j + w, h + i).getBlue());
            } catch (IllegalArgumentException e) {
              blueChannel += 0;
            }

            try {
              greenChannel += (int) (kernel[w + w2][h + h2] * picture.getPixelAt(
                  j + w, h + i).getGreen());
            } catch (IllegalArgumentException e) {
              greenChannel += 0;
            }
          }
        }
        changedImage[j][i] = new Pixel(j, i, redChannel, greenChannel, blueChannel);
        redChannel = 0;
        blueChannel = 0;
        greenChannel = 0;

      }
    }
    return new Image(picture.getWidth(), picture.getHeight(),
        picture.getMaxValue(), changedImage);
  }

  @Override
  public boolean validKernel(double[][] kernel) {
    return kernel.length % 2 == 1 && kernel[0].length % 2 == 1 && kernel.length == kernel[0].length;
  }
}
