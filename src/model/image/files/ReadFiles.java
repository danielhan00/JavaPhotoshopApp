package model.image.files;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import model.image.IImage;
import model.image.Image;
import model.image.Pixel;

/**
 * Read images from files and create image objects.
 */
public class ReadFiles implements IReadImageData {

  private int width;
  private int height;
  private int maxValue;
  private Pixel[][] imageArray;


  /**
   * Empty constructor to read an image from the given file.
   */
  public ReadFiles() {
    // Used to create a Read Image object and access its methods.
  }

  /**
   * Generates an image object from a given file.
   * @param fileName the name of the file.
   * @return The object generated from the file.
   */
  @Override
  public IImage createImage(String fileName) {
    ReadFiles dummy = new ReadFiles();

    dummy.read(fileName);

    return new Image(dummy.getWidth(), dummy.getHeight(), dummy.getMaxValue(),
        dummy.getImageArray());
  }

  /**
   * Read an image file in the given format and create a 2D array of Pixels.
   *
   * @param fileName the path of the file.
   */
  public void read(String fileName) {

    File file = new File(fileName);
    BufferedImage img = null;

    try {
      img = ImageIO.read(file);
    } catch (IOException e) {
      // something
    }

    width = img.getWidth();
    height = img.getHeight();
    maxValue = 255;

    imageArray = new Pixel[img.getWidth()][img.getHeight()];

    for (int i = 0; i < img.getHeight(); i++) {
      for (int j = 0; j < img.getWidth(); j++) {
        int pixel = img.getRGB(j, i);

        Color color = new Color(pixel, false);

        imageArray[j][i] = new Pixel(j, i, color.getRed(), color.getGreen(), color.getBlue());
      }
    }
  }

  /**
   * Creates a blank file in the system.
   * @param fileName desired name for the created file.
   */
  public void createFile(String fileName) {
    try {
      File myFile = new File(fileName);
      if (myFile.createNewFile()) {
        System.out.println("File Created!");
      }
      else {
        System.out.println("File already exists.");
      }
    } catch (IOException e) {
      System.out.println("Error while trying to create file.");
      e.printStackTrace();
    }
  }

  /**
   * Write to the file using our buffered image and file location.
   * @param image The image object to write to the file.
   * @param fileType The filetype to be exported (PNG or JPG).
   * @param outputFile The file to be exported.
   */
  public static void writeJPEG(IImage image, String fileType, File outputFile) {

    try {
      BufferedImage bi = convertBufferedImage(image);
      ImageIO.write(bi, fileType, outputFile);
    }
    catch (IOException e) {
      System.out.println("Bad Image");
    }
  }

  // create bufferedimage from what we have

  /**
   * Create a bufferedimage object from the image we have.
   * @param image The image to be converted.
   * @return The converted image (now a bufferedimage).
   */
  private static BufferedImage convertBufferedImage(IImage image) {
    int h = image.getHeight();
    int w = image.getWidth();

    BufferedImage temp = new BufferedImage(w, h, BufferedImage.TYPE_INT_RGB);

    for (int i = 0; i < h; i++) {
      for (int j = 0; j < w; j++) {
        Color tempColor = new Color(image.getImage()[j][i].getRed(),
            image.getImage()[j][i].getGreen(), image.getImage()[j][i].getBlue());
        int rgb = tempColor.getRGB();
        temp.setRGB(j, i, rgb);
      }
    }

    return temp;
  }


  /**
   * Returns the width of the ImageArray.
   *
   * @return The width of the image.
   */
  public int getWidth() {
    int temp = width;
    return temp;
  }

  /**
   * Returns the height of the ImageArray.
   *
   * @return The height of the image.
   */
  public int getHeight() {
    int temp = height;
    return temp;
  }

  /**
   * Gets the maxValue of the colors in an Image.
   *
   * @return the max color value as an int.
   */
  public int getMaxValue() {
    int temp = maxValue;
    return temp;
  }

  /**
   * Gets the 2D array that stores the pixel information of an Image.
   *
   * @return The 2D Pixel array - Pixel[][]
   */
  public Pixel[][] getImageArray() {
    Pixel[][] tempArray = imageArray;
    return tempArray;
  }

}
