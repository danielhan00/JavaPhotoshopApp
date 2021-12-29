package iimagetests;

import java.util.ArrayList;
import java.util.Arrays;
import model.image.Image;
import model.image.Pixel;
import model.image.layer.Layer;
import model.image.layer.LayerList;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


/**
 * Test functionality of the layerlist class. This class represents the list of layers
 * used by the application.
 */
public class LayerListTest {

  // getter for a layer list
  @Test
  public void testgetLayerList() {
    Pixel[][] squareArray = {
        {new Pixel(0, 0, 0, 0, 0),
            new Pixel(1, 0, 0, 0, 0)},
        {new Pixel(0, 1, 0, 0, 0),
            new Pixel(1, 1, 0, 0, 0)}};

    ArrayList<Layer> list = new ArrayList<>(Arrays.asList(
        new Layer(new Image(50, 50, 255, squareArray), true),
        new Layer(new Image(50, 10, 200, squareArray), false)));

    LayerList temp = new LayerList(list);

    assertEquals(2, temp.getLayerList().size());


  }
}
