package model.image.layer;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import model.image.files.IReadImageData;
import model.image.files.ReadFiles;
import model.image.files.ReadImage;

/**
 * The image representation for layered images. Also generates the master text file to manage the
 * layers.
 */
public class LayerList {

  private String layerListName;
  private ArrayList<Layer> layerList;
  private ArrayList<String> nameList;


  /**
   * Constructor for a LayerList object.
   *
   * @param nameList      names of all images in the LayerList
   * @param layerListName name of the entire LayerList
   */
  public LayerList(ArrayList<String> nameList, String layerListName) {
    this.layerListName = layerListName;
    this.nameList = nameList;
  }

  /**
   * Constructor for a LayerList object.
   *
   * @param layerList ArrayList of Layer objects.
   */
  public LayerList(ArrayList<Layer> layerList) {
    this.layerList = layerList;
  }


  /**
   * Constructor to organize images when a master file is loaded in.
   *
   * @param masterFileName The name of the master file and the Layer list.
   */
  public LayerList(String masterFileName) {
    this.layerListName = masterFileName;
    this.layerList = createLayerList(masterFileName);
  }

  /**
   * Constructor when organizing a layer list to be exported.
   *
   * @param layerListName The name of the master file and the layer list.
   * @param layerList     The list of images that make up the layers.
   */
  public LayerList(String layerListName, ArrayList<Layer> layerList) {
    this.layerListName = layerListName;
    this.layerList = layerList;
  }

  /**
   * Reads a provided text file to create a LayerList.
   *
   * @param fileName the filename to be read to create the layer list.
   * @return the created layer list.
   */
  private ArrayList<Layer> createLayerList(String fileName) {
    Scanner sc;

    // create the scanner object with access to the file.
    try {
      sc = new Scanner(new FileInputStream(fileName));
    } catch (FileNotFoundException e) {
      throw new IllegalArgumentException("The file name is invalid!!!");
    }

    StringBuilder builder = new StringBuilder();

    // read file line by line and populate a stringbuilder.
    int numberOfImages = -1;
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      builder.append(s + System.lineSeparator());
      numberOfImages++;
    }

    layerList = new ArrayList<Layer>();

    // read from the stringbuilder with the scanner.
    sc = new Scanner(builder.toString());

    // token is temporary value from the string
    String token;
    token = sc.next();

    int counter = 0;

    // iterate through
    while (sc.hasNextLine()) {
      String s = sc.nextLine();

      if (s.charAt(s.length() - 1) == 'm') {
        IReadImageData dummy = new ReadImage();
        layerList.add(new Layer(dummy.createImage(s), true));
      } else {
        IReadImageData dummy = new ReadFiles();
        layerList.add(new Layer(dummy.createImage(s), true));
      }

      counter++;

    }

    return layerList;
  }


  /**
   * Get our current layerlist - deep copied for security.
   *
   * @return IImage[]
   */
  public ArrayList<Layer> getLayerList() {
    ArrayList<Layer> temp = new ArrayList<Layer>();
    for (int i = 0; i < layerList.size(); i++) {
      temp.add(layerList.get(i));
    }

    return temp;
  }

  /**
   * Exports a LayerList to a folder that contains the Images and master text file.
   */
  public void exportToSystem() {
    File out = new File(layerListName + "DIR");
    if (out.mkdirs()) {
      System.out.println("Directory Created!");
    } else {
      System.out.println("Directory already exists.");
    }

    String temp = layerListName + "MASTER.txt";
    try {
      File myFile = new File(out, temp);
      if (myFile.createNewFile()) {
        System.out.println("Master File Created!");
        writeToMasterFile(myFile);
        for (String s : nameList) {
          Path source = Paths.get(s);
          Path destination = Paths.get(out.toString() + "\\" + s);
          Files.move(source, destination);
        }

      } else {
        System.out.println("Master File already exists.");
      }
    } catch (IOException e) {
      System.out.println("Error while trying to create the master file.");
      e.printStackTrace();
    }
  }


  /**
   * Writing fileNames of LayerLists to the master text file.
   *
   * @param fileName fileName of the each Layer
   */
  public void writeToMasterFile(File fileName) {
    try {
      FileWriter myWriter = new FileWriter(fileName);

      for (String s : nameList) {
        myWriter.write(s + "\n");
      }
      myWriter.close();
    } catch (IOException e) {
      System.out.println("An error occurred while trying to write to the file");
      e.printStackTrace();
    }
  }


}
