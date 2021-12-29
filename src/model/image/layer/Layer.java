package model.image.layer;

import model.image.IImage;
import model.image.Image;

/**
 * Represents an Image Layer.
 */
public class Layer {

  private IImage image;
  boolean isVisible;

  /**
   * Empty Constructor for a Layer.
   */
  public Layer() {
    // empty constructor
  }

  /**
   * A Layer object constructor.
   * @param image The Image of the current layer.
   * @param isVisible Is the current layer visible to the user?
   */
  public Layer(IImage image, boolean isVisible) {
    this.image = image;
    this.isVisible = isVisible;

  }

  /**
   * Getter for the Image in the Layer.
   * @return IImage
   */
  public IImage getImage() {
    IImage temp = new Image(image.getWidth(), image.getHeight(),
        image.getMaxValue(), image.getImage());
    return temp;
  }

  /**
   * Getter for the IsVisible boolean.
   * @return boolean
   */
  public boolean getIsVisible() {
    boolean temp = isVisible;
    return temp;
  }


}
