package iimagetests;

import static org.junit.Assert.assertEquals;

import model.image.IImage;
import model.image.files.ReadImage;
import org.junit.Test;


/**
 * Test class for ImageUtil class, ReadImage.
 */
public class ReadImageTest {

  /**
   * Creates the Koala imageArray for testing.
   */
  private ReadImage koala;

  // test the createImage method to make an image from a file
  @Test
  public void testCreateImage() {
    koala = new ReadImage();
    IImage koala2 = koala.createImage("koala.ppm");

    assertEquals(612, koala2.getWidth());
    assertEquals(408, koala2.getHeight());
    assertEquals(255, koala2.getMaxValue());
    assertEquals(77, koala2.getPixelAt(0, 0).getBlue());

  }

  // test the getWidth getter.
  @Test
  public void testGetWidth() {
    koala = new ReadImage();
    koala.read("koala.ppm");
    assertEquals(koala.getWidth(), 612);
  }

  // test the getHeight getter.
  @Test
  public void testGetHeight() {
    koala = new ReadImage();
    koala.read("koala.ppm");
    assertEquals(koala.getHeight(), 408);
  }

  // test the getMaxValue getter.
  @Test
  public void testGetMexValue() {
    koala = new ReadImage();
    koala.read("koala.ppm");
    assertEquals(koala.getMaxValue(), 255);
  }

  // test the getImageArray getter.
  @Test
  public void testGetImageArray() {
    koala = new ReadImage();
    koala.read("koala.ppm");
    assertEquals(koala.getImageArray()[0][0].getRed(), 105);
  }
}
