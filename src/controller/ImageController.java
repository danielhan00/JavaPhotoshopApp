package controller;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;
import model.filter.IFilter;
import model.filter.colorfilter.Greyscale;
import model.filter.colorfilter.Sepia;
import model.filter.kernelfilter.Blur;
import model.filter.kernelfilter.Sharpen;
import model.image.IImage;
import model.image.Image;
import model.image.files.ReadFiles;
import model.image.files.ReadImage;
import model.image.layer.Layer;
import model.image.layer.LayerList;
import model.image.programmatic.Checkerboard;
import view.IImageView;
import view.ImageView;

/**
 * Represents our controller for IImage operations in our program. This class implements
 * the IImage controller interface and uses objects to manipulate images and read/write with files
 * on a user's computer. There is support for manual entering of image filtering/saving commands
 * as well as the ability to provide a document of commands.
 */
public class ImageController implements IImageController {

  private final Readable in;
  private final IImageView view;

  /**
   * Control the program by creating an ImageController object. The readable takes in
   * information from the user indicated source and the appendable provides information
   * back to the user about the state of the program and its operations.
   * @param rd The readable object to provide information to the program.
   * @param ap The appendable object so information can be shown to the user as the program
   *           progresses.
   * @throws IllegalArgumentException If an incorrect command is provided or some parameter is
   *         invalid. These exceptions are caught and handled by the program.
   */
  public ImageController(Readable rd, Appendable ap)
      throws IllegalArgumentException {
    if (rd == null || ap == null) {
      throw new IllegalArgumentException("Readable and Appendable cannot be null");
    }

    final Appendable appendable;

    this.in = rd;
    appendable = ap;

    // create a imageview object that connects to the appendable
    // and can output operations to the user.
    this.view = new ImageView(ap);

  }

  /**
   * helper method to communicate with view and render the provided message. Throws an
   * illegalstateexception if cannot write to appendable.
   *
   * @param output Output string to go to the user.
   */
  private void print(String output) {
    Objects.requireNonNull(output);
    try {
      view.render(output);
    } catch (IOException e) {
      throw new IllegalStateException("Could not write to appendable.");
    }
  }

  /**
   * Control the main operations and functions of the application based on provided user commands.
   * Scanner object is used to read user input, and information about the operations requested by
   * the user will be provided to the user via the appendable object provided previously.
   * Supports the following operations:
   * Create - adds a new empty layer to the current temporary list of layers being
   * used by the application.
   * Current - Select the layer to be worked on.
   * Load - verify the file type of a provided image file, verify that the size
   * of the file is the same as previous layers, add the image to the list of layers
   * that can be selected and manipulated.
   * Draw - Create and set our current image to a programmatically generated checkerboard.
   * Blur/Sepia/Greyscale/Sharpen - Apply the desired filter to our current image.
   * Save - Save the current file to the system, add the image to the layer list,
   * add the image name to the list of names, add the image name to the building master file name.
   * Invisible - Swap the invisible boolean for a layer. A layer is composed of an IImage object
   * and a boolean to indicate if it is visible to the user.
   */
  public void executeCommands() {

    // need these objects to read the files.
    ReadImage dummyReadImage = new ReadImage();
    ReadFiles dummyReadFile = new ReadFiles();

    // store the list of provided layers and also the name of the files provided.
    ArrayList<Layer> betweenList = new ArrayList<Layer>();
    ArrayList<String> nameList = new ArrayList<String>();

    // what index do you want (usually used to find a spot in betweenlist).
    int index = 1;

    // stores the current image being worked on by the application.
    IImage currentImage = new Image();

    // take in and parse user input.
    Scanner scanner = new Scanner(this.in);

    // String variable to help parse input.
    String val = scanner.next();

    // the buildable name of the master file to store the layer locations.
    String masterFileName = "";

    int counter = 0;
    int acceptableHeight = 0;
    int acceptableWidth = 0;

    // iterate through the user input and look for the end "done" command.
    while (!(val.equals("done"))) {

      try {

        switch (val) {

          case "create":
            betweenList.add(new Layer(null, false));
            break;

          case "current":
            val = scanner.next();
            index = Integer.parseInt(val);
            break;

          case "load":
            val = scanner.next();

            if (val.charAt(val.length() - 1) == 'm') {
              currentImage = dummyReadImage.createImage(val);
            }
            else {
              currentImage = dummyReadFile.createImage(val);
            }

            if (counter == 0) {
              acceptableHeight = currentImage.getHeight();
              acceptableWidth = currentImage.getWidth();
              counter++;
            } else {
              if (currentImage.getHeight() != acceptableHeight
                  || currentImage.getWidth() != acceptableWidth) {
                throw new IllegalArgumentException(
                    "Dimensions of loaded image do not align with other images.");
              }
              counter++;
            }

            break;

          case "draw":
            val = scanner.next();
            if (val.equals("checkerboard")) {
              int sizeSquareTile = Integer.parseInt(scanner.next());
              int numTiles = Integer.parseInt(scanner.next());

              Color color1 = Color.RED;
              Color color2 = Color.BLUE;

              currentImage = new Checkerboard(sizeSquareTile,
                  numTiles, color1, color2);
            }
            break;

          case "blur":
            IFilter blur = new Blur();
            currentImage = blur.apply(currentImage);
            break;

          case "sepia":
            IFilter sepia = new Sepia();
            currentImage = sepia.apply(currentImage);
            break;

          case "greyscale":
            IFilter greyscale = new Greyscale();
            currentImage = greyscale.apply(currentImage);
            break;

          case "sharpen":
            IFilter sharpen = new Sharpen();
            currentImage = sharpen.apply(currentImage);
            break;

          case "save":
            val = scanner.next();

            if (val.charAt(val.length() - 1) == 'm') {
              dummyReadImage.createFile(val);
              dummyReadImage.writeFile(val, currentImage);
            } else {
              dummyReadFile.createFile(val);
              dummyReadFile.writeJPEG(currentImage, val.substring(val.length() - 3, val.length()),
                  new File(val));
            }

            betweenList.set(index - 1, new Layer(currentImage, true));
            nameList.add("" + val);
            masterFileName = masterFileName + val;

            break;

          case "invisible":
            val = scanner.next();
            index = Integer.parseInt(val);
            IImage invisImage = betweenList.get(index - 1).getImage();
            boolean currentInvis = betweenList.get(index - 1).getIsVisible();
            betweenList.set(index - 1, new Layer(invisImage, !currentInvis));
            break;

          default:
            throw new IllegalArgumentException("bad");

        }

        // in the case of an invalid command above, kindly ask the user to try again.
      } catch (IllegalArgumentException e) {
        this.print("Invalid command, please enter line again\n");


      }

      // continue iterating.
      scanner.nextLine();

      if (scanner.hasNext()) {
        val = scanner.next();
      }
    }

    LayerList finalLayerList = new LayerList(nameList,
        masterFileName);

    // export the master file and the created list of layers.
    finalLayerList.exportToSystem();


  }


}
