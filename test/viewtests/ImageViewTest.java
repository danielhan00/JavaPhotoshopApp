package viewtests;

import java.io.IOException;
import org.junit.Test;
import view.ImageView;
import view.IImageView;

import static org.junit.Assert.assertEquals;

/**
 * Test the operations of our image view. The only method is to render a message to the user. We
 * definitely want to be thorough in our testing however additional tests felt repetitive here.
 */
public class ImageViewTest {

  @Test
  public void testRender() throws IOException {
    StringBuilder out = new StringBuilder();
    IImageView view = new ImageView(out);
    try {
      view.render("This is information to be given to the user.");
    } catch (IOException e) {
      // test fails if we're here.
    }

    assertEquals("This is information to be given to the user.", out.toString());
  }
}
