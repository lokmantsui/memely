package memely;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**AF(filename) = image stored in filename
 * RI: filename consists of a-z, A-Z, 0-9, ., /, -, _ as long as they are not the first letter
 * Safety from rep exposure:
 * all fields are private and immutable except img, a defensive copy is returned by observer image().
 * 
 * @author lt
 *
 */
public class Image implements Expression {
    private final String filename;
    private BufferedImage img;
    private final int w;
    private final int h;
    
    public Image(String filename) {
        this.filename = filename;
        try {
            img = ImageIO.read(new File(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        w = img.getWidth();
        h = img.getHeight();
        checkRep();
    }
    
    private void checkRep() {
        assert filename.matches("[A-Za-z0-9./][A-Za-z0-9./_-]*");
    }
    
    public String getFilename() {
        return filename;
    }
    
    @Override
    public String toString() {
        return filename;
        }

    @Override
    public boolean equals(Object that){
        return (that instanceof Image) && filename.equals(((Image)that).filename);
        }
    
    @Override
    public int hashCode(){
        return filename.hashCode();
        }

    public int getWidth(){
        return w;
        }

    public int getHeight(){
        return h;
        }

    public BufferedImage image(){
        return Expression.deepCopy(img);
        }
}