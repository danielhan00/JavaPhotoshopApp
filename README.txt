How to run this application!

1. In the res directory, find and download the jar file. (imageHanKugler.jar)
2. Now open a command-prompt/terminal and navigate to the folder where you downloaded imageHanKugler.jar. Now type java -jar imageHanKugler.jar and press ENTER.









--------------------- FUNCTIONALITIES --------------------- 

The Model:
    1. Directory image - the directory for classes/interfaces representing an Image. 
        1. Directory ppm - subdirectory leading to how to read data from a ppm file. 
            1. Class ReadImage - processes a PPM file and returns an array of Pixels of that PPM file.  
        2. Directory programmatic - subdirectory relating to create Images programmatically 
        3. Interface IImage - the interface to represent all Image types. 
        4. Class Image - the class to represent an image as defined by this assignment 
        5. Class Pixel - a datatype of a Pixel that is used to build images.  

    2. Directory filter - the directory for classes/interfaces representing a Filter.  
        1. Interface IFilter - the interface to represent all the filter types.  
        2. Directory colorFilter - subdirectory relating to color transformations. 
            1. Class AbstractColorFilter - abstract class to represent a color filter transformation on an image.  
            2. Class Greyscale - applies a greyscale color transformation on the image.  
            3. Class Sepia - applies a sepia color transformation on the image.  
        3. Directory kernelFilter - subdirectory relating to kernel based transformations. 
            1. Class AbstractKernelFilter - abstract class to represent a kernel filter transformation on an image. 
            2. Class Blur - applies a blur kernel filter transformation on the image.  
            3. Class Sharpen - applies a sharpen kernel filter transformation on the image.  

    3. Class Main - creates and writes files of Images in PPM format.  

Image Sources:
    1. Red/Blue checkerboard image (checkerboard.ppm) - I, Matt Kugler, own this image and am authorizing its use in the project.  
    2. Baby Koala (koalaCUTE.ppm) - Animal Hearted Apparel. (2015, August 8). Meet Archer: The Tiniest Koala [Photograph]. AnimalHearted.Com. https://www.animalhearted.com/blogs/animal-blog/55472387-meet-archer-the-tiniest-koala   

README - Daniel Han, Matt Kugler

Updates: 
    1. Full support for conventional file formats (PPM, JPG, PNG). Includes import/modification/export features. 
    2. Implemented representations of "Layers" and "LayerLists" for images.
	1. A Layer is an Image and an "isVisible" boolean to denote when the image is visible to the user. 
	2. A LayerList is a list of Layer objects. Users can import, manipulate, and export LayerLists. 
	3. Each Layer is able to be turned on/off by the user. Filtering operations can be applied directly to each layer.  
	4. A multi-layered image can be saved simply as a collection of files in a specified directory: one for each layer (as regular images), and one (text) file that stores the locations of all the layer files.
	5. All outlined features involving Layers are supported. 
	6. Example of a Master Text File of a LayerList (The text file and image files are found in the same directory):
		KoalaGrey.ppm
		KoalaSharp.png
		KoalaBlur.jpg
    3. Implemented ability to manually provide Layer/Image modification commands, as well as load and execute a set of commands stored in a text file. These commands are handled by an Image Controller connected to an Image View. 
	1. The following commands are supported:
		Create - adds a new empty layer to the current temporary list of layers being used by the application.
		Current - Select the layer to be worked on.
    		Load - Verify the file type of a provided image file, verify that the size of the file is the same as previous layers, add the image to the list of layers that can be selected and manipulated.
		     - We allow the user to load images of different sizes and will complete the desired functions, however the user will be notified by the program that an invalid input was provided. 
   		Draw - Create and set our current image to a programmatically generated checkerboard.
    		Blur/Sepia/Greyscale/Sharpen - Apply the desired filter to our current image.
    		Save - Save the current file to the system, add the image to the layer list, add the image name to the list of names, add the image name to the building master file name.
    		Invisible - Swap the invisible boolean for a layer. A layer is composed of an IImage object and a boolean to indicate if it is visible to the user.
	2. Functionality includes feedback from application to the user indicating status of the program. 
    4. Design Update: Functionality to import and export data from files was previously handled in our Main class. We have now abstracted this functionality to its own interface/classes. This was done to ensure that main does not handle 
	any heavy lifting and that future import/export functions can be added via extension rather than modification. 

		
	
