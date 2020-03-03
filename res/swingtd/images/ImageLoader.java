package swingtd.images;

import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author peixoto
 */
public class ImageLoader {
    public static Image load(String fileName) {
        try {
            return ImageIO.read(ImageLoader.class.getResource(fileName));
        } catch (IOException ex) {
            Logger.getLogger(ImageLoader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
