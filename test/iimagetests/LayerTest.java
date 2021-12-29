package iimagetests;

import java.awt.Color;
import model.image.IImage;
import model.image.layer.Layer;
import model.image.programmatic.Checkerboard;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * Test the layer object class. Represents stored images provided by the user.
 */
public class LayerTest {


  @Test
  public void testGetImage() {
    IImage checkerboard = new Checkerboard(1, 4, Color.BLACK, Color.WHITE);
    Layer checkerboardLayer = new Layer(checkerboard, true);

    assertEquals(2, checkerboardLayer.getImage().getWidth());
    assertEquals(2, checkerboardLayer.getImage().getHeight());
    assertEquals(255, checkerboardLayer.getImage().getMaxValue());
    assertEquals(0, checkerboardLayer.getImage().getPixelAt(0, 0).getRed());
  }

  @Test
  public void testVisible() {
    IImage checkerboard = new Checkerboard(1, 4, Color.BLACK, Color.WHITE);
    Layer checkerboardLayer = new Layer(checkerboard, true);

    assertTrue(checkerboardLayer.getIsVisible());
  }

}
