package iimagetests;

import static org.junit.Assert.assertEquals;

import model.image.Pixel;
import org.junit.Test;

/**
 * A class to test Pixel objects.
 */
public class PixelTest {

  Pixel clampPixel = new Pixel(0, 0, -100, 500, 100);
  Pixel normalPixel = new Pixel(0, 0, 100, 100, 100);

  @Test
  public void testClamping() {
    assertEquals(clampPixel.getRed(), 0);
    assertEquals(clampPixel.getGreen(), 255);
    assertEquals(clampPixel.getBlue(), 100);
  }

  @Test
  public void testGetX() {
    assertEquals(clampPixel.getPosnX(), 0);
  }

  @Test
  public void testGetY() {
    assertEquals(clampPixel.getPosnY(), 0);
  }

  @Test
  public void testGetRed() {
    assertEquals(normalPixel.getRed(), 100);
  }

  @Test
  public void testGetGreen() {
    assertEquals(normalPixel.getGreen(), 100);
  }

  @Test
  public void testGetBlue() {
    assertEquals(normalPixel.getBlue(), 100);
  }

}
