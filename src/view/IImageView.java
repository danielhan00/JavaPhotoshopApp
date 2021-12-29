package view;

import java.io.IOException;

/**
 * Interface to represent a view for an Image editing program.
 */
public interface IImageView {

  /**
   * Renders a string message to the user.
   * @param message The message to be displayed to the user.
   * @throws IOException If the message cannot be rendered.
   */
  void render(String message) throws IOException;


}
