package controllertests;

import controller.IImageController;
import controller.ImageController;
import java.io.StringReader;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

/**
 * Testing the operations of our controller and manipulation of the images/layers.
 */
public class ImageControllerTest {

  @Test
  public void testBadCommandSolo() {
    String badCommand = "hello\ndone";
    StringReader in = new StringReader(badCommand);
    StringBuilder out = new StringBuilder();
    IImageController controller = new ImageController(in, out);
    controller.executeCommands();

    assertEquals("Invalid command, please enter line again\n", out.toString());
  }

  @Test
  public void testBadCommandInLine() {
    String badCommand = "create layer 1\ncurrent 1\nload Koala.ppm\ngreyscale\nblah blah\ndone";
    StringReader in = new StringReader(badCommand);
    StringBuilder out = new StringBuilder();
    IImageController controller = new ImageController(in, out);
    controller.executeCommands();

    assertEquals("Invalid command, please enter line again\n", out.toString());
  }

  @Test
  public void testGoodCommand() {
    String goodCommand = "create layer 1\n"
        + "create layer 2\n"
        + "current 1\n"
        + "load Koala.ppm\n"
        + "greyscale\n"
        + "save koalaGrey2.jpg\n"
        + "current 2\n"
        + "load Koala.ppm\n"
        + "sharpen\n"
        + "invisible 1\n"
        + "save KoalaSharp.png\n"
        + "done";
    StringReader in = new StringReader(goodCommand);
    StringBuilder out = new StringBuilder();
    IImageController controller = new ImageController(in, out);
    controller.executeCommands();

    assertEquals("", out.toString());
  }

  @Test
  public void testGoodCommand2() {
    String goodCommand = "create layer 1\n"
        + "create layer 2\n"
        + "current 1\n"
        + "draw checkerboard 50 25\n"
        + "sepia\n"
        + "save redBlueChecker.ppm\n"
        + "current 2\n"
        + "draw checkerboard 50 25\n"
        + "blur\n"
        + "save greenYellowChecker.png\n"
        + "done";
    StringReader in = new StringReader(goodCommand);
    StringBuilder out = new StringBuilder();
    IImageController controller = new ImageController(in, out);
    controller.executeCommands();

    assertEquals("", out.toString());
  }
}
