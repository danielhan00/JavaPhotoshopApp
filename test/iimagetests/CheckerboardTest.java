package iimagetests;

import static org.junit.Assert.assertEquals;

import java.awt.Color;
import model.image.Pixel;
import model.image.programmatic.Checkerboard;
import model.image.IImage;
import org.junit.Test;

/**
 * Test class for a Checkerboard image object.
 */
public class CheckerboardTest {

  // checkerboard objects and pixel objects for testing.
  IImage checker1 = new Checkerboard(5, 4, Color.BLACK, Color.WHITE);

  Pixel[][] one = {{new Pixel(0, 0, 255, 255, 255),
      new Pixel(1, 0, 0, 0, 0)},
      {new Pixel(0, 1, 0, 0, 0),
          new Pixel(1, 1, 255, 255, 255)}};

  // test that the checkerboard is being created correctly.
  @Test
  public void testCreateBoard() {
    assertEquals(checker1.getPixelAt(0,0).getBlue(), 0);
    assertEquals(checker1.getPixelAt(5,0).getBlue(), 255);
    assertEquals(checker1.getPixelAt(0,5).getBlue(), 255);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullColor1() {
    new Checkerboard(1, 1, null, Color.BLACK);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testNullColor2() {
    new Checkerboard(1, 1, Color.BLACK, null);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidSizeSquareTiles() {
    new Checkerboard(0, 25, Color.BLACK, Color.WHITE);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidNumTilesZero() {
    new Checkerboard(2, 0, Color.BLACK, Color.WHITE);
  }

  @Test (expected = IllegalArgumentException.class)
  public void testInvalidNumTiles() {
    new Checkerboard(2, 11, Color.BLACK, Color.WHITE);
  }

  @Test
  public void testGetWidth() {
    assertEquals(10, checker1.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(10, checker1.getHeight());
  }

  @Test
  public void testGetMaxValue() {
    assertEquals(255, checker1.getMaxValue());
  }

  @Test
  public void testGetImage() {
    Checkerboard check = new Checkerboard(1, 4, Color.BLACK, Color.WHITE);
    assertEquals(new Pixel(0, 0, 0, 0, 0).getRed(),
        check.getImage()[0][0].getRed());
  }

  @Test
  public void testGetPixelAt() {
    assertEquals(0, checker1.getPixelAt(0,0).getBlue());
  }
}
