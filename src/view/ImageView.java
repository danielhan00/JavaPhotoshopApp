package view;

import java.io.IOException;

/**
 * A class to represent one instance of a view of an Image editing program.
 */
public class ImageView implements IImageView {

  private final Appendable ap;

  /**
   * Constructor for an ImageView object with the location of the View as a param.
   *
   * @param ap Appendable location for View.
   */
  public ImageView(Appendable ap) {
    this.ap = ap;
  }

  @Override
  public void render(String message) throws IOException {
    try {
      this.ap.append(message);
    } catch (IOException e) {
      throw new IOException();
    }
  }
}
