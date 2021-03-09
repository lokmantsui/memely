package memely;

import java.awt.image.BufferedImage;

/**AF(filename) = image stored in filename
 * RI: filename consists of a-z, A-Z, 0-9, ., /, -, _ as long as they are not the first letter
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
        throw new RuntimeException("unimplemented");
        }

    public int getHeight(){
        throw new RuntimeException("unimplemented");
        }

    public BufferedImage image(){
        throw new RuntimeException("unimplemented");
        }
}