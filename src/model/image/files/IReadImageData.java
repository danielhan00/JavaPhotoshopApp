package model.image.files;

import model.image.IImage;

/**
 * Interface to manage creating and reading from image files as well as creating new files
 * of images.
 */
public interface IReadImageData {

  /**
   * Makes a new Image object from a given file.
   * @param fileName the name of the file.
   * @return Image object.
   */
  IImage createImage(String fileName);


  /**
   * Read the given file and set the values of our image.
   * @param fileName The filename to be read.
   */
  void read(String fileName);

  /**
   * Creates a blank file in the system.
   * @param fileName desired name for the created file.
   */
  void createFile(String fileName);





}
