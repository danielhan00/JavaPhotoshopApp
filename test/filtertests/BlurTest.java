package filtertests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;
import model.filter.kernelfilter.Blur;
import model.image.IImage;
import model.image.programmatic.Checkerboard;
import org.junit.Test;

/**
 * Test class for filter class Blur.
 */
public class BlurTest {

  Blur blur = new Blur();

  IImage redGreenCheckerboard = new Checkerboard(
      50, 4, Color.RED, Color.GREEN);

  double[][] kernel = {
      {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0},
      {1.0 / 8.0, 1.0 / 4.0, 1.0 / 8.0},
      {1.0 / 16.0, 1.0 / 8.0, 1.0 / 16.0}
  };

  double[][] illegalKernel = {
      {-1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0, 1.0 / 4.0, -1.0 / 8.0},
      {-1.0 / 8.0, 1.0 / 4.0, 1.0 / 4.0, 1.0 / 4.0, -1.0 / 8.0},
  };

  // null picture given - invalid.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidPicture() {
    blur.apply(null);
  }

  // valid and invalid kernels testing.
  @Test
  public void testIllegalKernel() {
    assertFalse(blur.validKernel(illegalKernel));
    assertTrue(blur.validKernel(kernel));
  }

  // invalid kernel throws an exception.
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidKernelApplyKernel() {
    blur.applyKernel(illegalKernel, redGreenCheckerboard);
  }

  // test apply kernel - valid.
  @Test
  public void testApplyKernel() {
    assertEquals(140,
        blur.applyKernel(kernel, redGreenCheckerboard).getPixelAt(0, 0).getRed());
    assertEquals(0,
        blur.applyKernel(kernel, redGreenCheckerboard).getPixelAt(0, 0).getGreen());
    assertEquals(0,
        blur.applyKernel(kernel, redGreenCheckerboard).getPixelAt(0, 0).getBlue());
  }

  @Test
  public void testApply() {
    IImage sharpenRedGreenCheckerboard = blur.apply(redGreenCheckerboard);
    assertEquals(140,
        blur.apply(redGreenCheckerboard).getPixelAt(0, 0).getRed());
    assertEquals(0,
        blur.apply(redGreenCheckerboard).getPixelAt(0, 0).getGreen());
    assertEquals(0,
        blur.apply(redGreenCheckerboard).getPixelAt(0, 0).getBlue());
  }
}

