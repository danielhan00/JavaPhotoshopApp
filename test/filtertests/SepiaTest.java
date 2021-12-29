package filtertests;

import static org.junit.Assert.assertEquals;
import java.awt.Color;
import model.filter.colorfilter.Sepia;
import model.image.programmatic.Checkerboard;
import model.image.IImage;
import model.image.Pixel;
import org.junit.Test;

/**
 * Test class for Sepia color filter.
 */
public class SepiaTest {

  Sepia sepia = new Sepia();

  IImage redGreenCheckerboard = new Checkerboard(50, 4, Color.RED, Color.GREEN);

  double[][] filter = {
      {0.393, 0.769, 0.189},
      {0.349, 0.686, 0.168},
      {0.272, 0.534, 0.131}
  };

  // test the apply method for sepia picture.
  @Test
  public void testApply() {
    IImage sepiaRedGreenCheckerboard = sepia.apply(redGreenCheckerboard);
    assertEquals(sepiaRedGreenCheckerboard.getPixelAt(25, 25).getRed(),
        new Pixel(25, 25, 100, 88, 69).getRed());
    assertEquals(sepiaRedGreenCheckerboard.getPixelAt(25, 25).getGreen(),
        new Pixel(25, 25, 100, 88, 69).getGreen());
    assertEquals(sepiaRedGreenCheckerboard.getPixelAt(25, 25).getBlue(),
        new Pixel(25, 25, 100, 88, 69).getBlue());
  }

  // giving apply a null picture.
  @Test(expected = IllegalArgumentException.class)
  public void testNullPicture() {
    sepia.apply(null);
  }

  // test the apply method with a null picture - invalid.
  @Test(expected = IllegalArgumentException.class)
  public void testApplyNullPicture() {
    sepia.apply(null);
  }

  @Test
  public void testApplyColor() {
    IImage greyRedGreenCheckerboard = sepia.applyColor(filter, redGreenCheckerboard);

    assertEquals(sepia.applyColor(filter, redGreenCheckerboard).getPixelAt(
        0, 0).getRed(), 100);
  }

}
