package model.image.files;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import model.image.IImage;
import model.image.Image;
import model.image.Pixel;


/**
 * This class contains utility methods to read a PPM image from file and simply print its contents.
 */
public class ReadImage implements IReadImageData {

  private int width;
  private int height;
  private int maxValue;
  private Pixel[][] imageArray;

  /**
   * Empty constructor to read an image from a PPM file.
   */
  public ReadImage() {
    // empty constructor. Gives us access to see ReadImage elsewhere.
  }

  /**
   * Generates an image object from a given file.
   * @param fileName the name of the file.
   * @return The object generated from the file.
   */
  @Override
  public IImage createImage(String fileName) {
    ReadImage dummy = new ReadImage();

    dummy.read(fileName);

    return new Image(dummy.getWidth(), dummy.getHeight(), dummy.getMaxValue(),
        dummy.getImageArray());
  }

  /**
   * Read an image file in the PPM format and create a 2D array of Pixels.
   *
   * @param filename the path of the file.
   */
  public void read(String filename) {
    Scanner sc;

    try {
      sc = new Scanner(new FileInputStream(filename));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("The file name is invalid!!!");
    }

    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      throw new IllegalArgumentException("Invalid PPM file: plain RAW file should begin with P3");
    }

    this.width = sc.nextInt();
    this.height = sc.nextInt();
    this.maxValue = sc.nextInt();

    imageArray = new Pixel[width][height];

    for (int i = 0; i < height; i++) {
      for (int j = 0; j < width; j++) {
        int r = sc.nextInt();
        int g = sc.nextInt();
        int b = sc.nextInt();
        imageArray[j][i] = new Pixel(j, i, r, g, b);
      }
    }
  }

  @Override
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
   * Writes to the target filename and prints the image in PPM format.
   * @param fileName The name of the file to write to.
   * @param image The image values to write in PPM format.
   */
  public static void writeFile(String fileName, IImage image) {
    try {
      FileWriter myWriter = new FileWriter(fileName);
      myWriter.write("P3\n"
          + image.getWidth() + " "
          + image.getHeight() + "\n"
          + image.getMaxValue());
      for (int i = 0; i < image.getHeight(); i++) {
        for (int j = 0; j < image.getWidth(); j++) {
          myWriter.write("\n"
              + image.getPixelAt(j, i).getRed() + "\n"
              + image.getPixelAt(j, i).getGreen() + "\n"
              + image.getPixelAt(j, i).getBlue());
        }
      }
      myWriter.close();
      System.out.println("Was able to write to the file!");
    } catch (IOException e) {
      System.out.println("An error occurred while trying to write to the file");
      e.printStackTrace();
    }
  }


  /**
   * Returns the width of the ImageArray.
   *
   * @return The width of the image.
   */
  public int getWidth() {
    return width;
  }

  /**
   * Returns the height of the ImageArray.
   *
   * @return The height of the image.
   */
  public int getHeight() {
    return height;
  }

  /**
   * Gets the maxValue of the colors in an Image.
   *
   * @return the max color value as an int.
   */
  public int getMaxValue() {
    return maxValue;
  }

  /**
   * Gets the 2D array that stores the pixel information of an Image.
   *
   * @return The 2D Pixel array - Pixel[][]
   */
  public Pixel[][] getImageArray() {
    return imageArray;
  }
}


