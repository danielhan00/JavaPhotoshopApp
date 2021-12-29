package iimagetests;


import static org.junit.Assert.assertEquals;

import model.image.files.ReadFiles;
import model.image.IImage;
import org.junit.Test;

/**
 * Test class for ImageUtil class, ReadFiles.
 */
public class ReadFilesTest {

  /**
   * Creates the Koala imageArray for testing.
   */
  private ReadFiles koala;

  // test the getWidth getter.
  @Test
  public void testGetWidth() {
    koala = new ReadFiles();
    koala.read("koalaGrey.jpg");
    assertEquals(koala.getWidth(), 1024);
  }

  // test the getHeight getter.
  @Test
  public void testGetHeight() {
    koala = new ReadFiles();
    koala.read("koalaGrey.jpg");
    assertEquals(koala.getHeight(), 768);
  }

  // test the getMaxValue getter.
  @Test
  public void testGetMexValue() {
    koala = new ReadFiles();
    koala.read("koalaGrey.jpg");
    assertEquals(koala.getMaxValue(), 255);
  }

  // test the getImageArray getter.
  @Test
  public void testGetImageArray() {
    koala = new ReadFiles();
    koala.read("koalaGrey.jpg");
    assertEquals(koala.getImageArray()[0][0].getRed(), 86);
  }

  @Test
  public void testCreateImage() {
    koala = new ReadFiles();
    IImage koalaImage = koala.createImage("koalaGrey.jpg");

    assertEquals(768, koalaImage.getHeight());
    assertEquals(1024, koalaImage.getWidth());
    assertEquals(255, koalaImage.getMaxValue());
    assertEquals(86, koalaImage.getPixelAt(0,0).getRed());

  }
}

