package model.filter.colorfilter;

import model.image.IImage;
import model.image.Image;
import model.image.Pixel;

/**
 * A class to change a image's color to have a sepia tone.
 */
public class Sepia extends AbstractColorFilter {

  /**
   * Constructor to create the sepia object to use with the picture.
   */
  public Sepia() {
    // empty constructor, no fields. Gives us access to see Sepia elsewhere.
  }

  /**
   * Take the image and put a sepia filter on it.
   * @param picture Takes in an IImage to have a filter added.
   * @return The picture after the filter is added.
   */
  @Override
  public IImage apply(IImage picture) {
    if (picture == null) {
      throw new IllegalArgumentException("Cannot greyscale a null picture.");
    }

    double[][] filter = {
        {0.393, 0.769, 0.189},
        {0.349, 0.686, 0.168},
        {0.272, 0.534, 0.131}
    };

    return applyColor(filter, picture);
  }



  @Override
  public IImage applyColor(double[][] colorArray, IImage picture) {
    int width = picture.getWidth();
    int height = picture.getHeight();
    int r1;
    int g1;
    int b1;
    int r2;
    int g2;
    int b2;

    Pixel[][] changedImage = new Pixel[width][height];

    // iterate through the pixel array, update the pixels according to the correct greyscale math.
    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {

        r1 = picture.getPixelAt(j, i).getRed();
        g1 = picture.getPixelAt(j, i).getGreen();
        b1 = picture.getPixelAt(j, i).getBlue();

        // operations
        r2 = (int) ((colorArray[0][0] * r1) + (colorArray[0][1] * g1) + (colorArray[0][2] * b1));
        g2 = (int) ((colorArray[1][0] * r1) + (colorArray[1][1] * g1) + (colorArray[1][2] * b1));
        b2 = (int) ((colorArray[2][0] * r1) + (colorArray[2][1] * g1) + (colorArray[2][2] * b1));

        // set new pixel at j,i in changedImage
        changedImage[j][i] = new Pixel(j, i, r2, g2, b2);
      }
    }

    return new Image(picture.getWidth(), picture.getHeight(), picture.getMaxValue(), changedImage);
  }
}
