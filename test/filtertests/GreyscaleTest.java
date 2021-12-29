package filtertests;

import java.awt.Color;
import model.filter.colorfilter.Greyscale;
import model.image.programmatic.Checkerboard;
import model.image.IImage;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import model.image.Pixel;

/**
 * Test our greyscale filter class.
 */
public class GreyscaleTest {
  Greyscale greyscale = new Greyscale();

  IImage redGreenCheckerboard = new Checkerboard(50, 4, Color.RED, Color.GREEN);

  double[][] filter = {
      {0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722},
      {0.2126, 0.7152, 0.0722}
  };



  @Test
  public void testApply() {
    IImage greyRedGreenCheckerboard = greyscale.apply(redGreenCheckerboard);

    assertEquals(greyRedGreenCheckerboard.getPixelAt(25, 25).getRed(),
        new Pixel(25, 25, 54, 54, 54).getRed());
    assertEquals(greyRedGreenCheckerboard.getPixelAt(25, 25).getGreen(),
        new Pixel(25, 25, 54, 54, 54).getGreen());
    assertEquals(greyRedGreenCheckerboard.getPixelAt(25, 25).getBlue(),
        new Pixel(25, 25, 54, 54, 54).getBlue());
  }

  // giving apply a null picture.
  @Test(expected = IllegalArgumentException.class)
  public void testNullPicture() {
    greyscale.apply(null);
  }

  // test the apply method with a null picture - invalid.
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullPicture() {
    greyscale.apply(null);
  }

  @Test
  public void testApplyColor() {
    IImage greyRedGreenCheckerboard = greyscale.applyColor(filter, redGreenCheckerboard);

    assertEquals(greyscale.applyColor(filter, redGreenCheckerboard).getPixelAt(
        0, 0).getRed(),
        54);
  }
}


