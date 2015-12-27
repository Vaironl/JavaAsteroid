package Display;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class LoadImage {

	/**
	 * 
	 * @param filePath
	 *            A string to the path of the image file
	 * @param imageName
	 *            A name of the image to be used as reference in case of an
	 *            error
	 * @return image after being loaded, exits if an error occurs loading the
	 *         image
	 */
	public static BufferedImage loadImage(String filePath, String imageName) {

		BufferedImage image = null;

		try {
			image = ImageIO.read(new File(filePath));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("There was an error opening the" + imageName
					+ " image...");
			e.printStackTrace();
			System.exit(0);
		}

		return image;
	}

}
