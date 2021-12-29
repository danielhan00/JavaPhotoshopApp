package iimagetests;

import static org.junit.Assert.assertEquals;

import model.image.Image;
import model.image.Pixel;
import org.junit.Test;

/**
 * Test class for Image class object.
 */
public class ImageTest {

  Pixel[][] squareArray = {
      {new Pixel(0, 0, 0, 0, 0),
          new Pixel(1, 0, 0, 0, 0)},
      {new Pixel(0, 1, 0, 0, 0),
          new Pixel(1, 1, 0, 0, 0)}};


  Image square = new Image(2, 2, 255, squareArray);

  // test the null pixel array check in the constructor.
  @Test(expected = IllegalArgumentException.class)
  public void testNullPixelArray() {
    new Image(100, 100, 100, null);
  }

  @Test
  public void testGetWidth() {
    assertEquals(square.getWidth(), 2);
  }

  @Test
  public void testGetHeight() {
    assertEquals(square.getHeight(), 2);

  }

  @Test
  public void testGetMaxValue() {
    assertEquals(square.getMaxValue(), 255);

  }

  @Test
  public void testGetImage() {
    assertEquals(square.getImage(), squareArray);
  }

  @Test
  public void testGetPixelAt() {
    assertEquals(square.getPixelAt(0, 0), squareArray[0][0]);
  }
}

