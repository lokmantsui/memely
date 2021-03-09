package memely;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

/**AF(filename) = image stored in filename
 * RI: valid image file
 * Safety from rep exposure:
 * all fields are private and immutable
 * 
 * @author lt
 *
 */
public class Image implements Expression {
    private final String filename; 
    
    public Image(String filename) {
        this.filename = filename;
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
        throw new RuntimeException("unimplemented");
        }

    public int getHeight(){
        throw new RuntimeException("unimplemented");
        }

    public BufferedImage image(){
        throw new RuntimeException("unimplemented");
        }
}