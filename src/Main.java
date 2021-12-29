import controller.IImageController;
import controller.ImageController;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import javax.swing.JFrame;
import view.GuiFeatures;


/**
 * Main class to run the entire program.
 */
public class Main {

  /**
   * Run the program from here. Program functionality allows a set of filters to be applied to any
   * image either generated from the program or a PPM file. Exports the filtered picture as a PPM
   * file.
   *
   * @param args arguments to read
   */
  public static void main(String[] args) {

/*    System.out.println("Please enter filename import or \"manual\" to proceed.");

    // Reading from the user, what do you want?
    BufferedReader reader = new BufferedReader(
        new InputStreamReader(System.in));

    String name = "";

    // Reading data using readLine
    try {
      name += reader.readLine();
    } catch (IOException e) {
      System.out.println("Could not read.");
    }

    if (name.equals("manual")) {
      System.out.println("manual input selected.");
      InputStreamReader readUser = new InputStreamReader(System.in);
      IImageController controller =
          new ImageController(readUser, System.out);
      controller.executeCommands();
    } else {
      try {
        InputStreamReader readFile = new InputStreamReader(new FileInputStream(name));
        System.out.println("file read successful.");
        IImageController controller =
            new ImageController(readFile, System.out);
        controller.executeCommands();
      } catch (IOException e) {
        System.out.println("Could not read file");
      }
    }*/

    GuiFeatures frame = new GuiFeatures();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);

  }
}






