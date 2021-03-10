package memely;

import java.awt.image.BufferedImage;

/**AF(str) = caption corresponding to str
 * RI: str does not have newline or double quote
 * Safety from rep exposure:
 * all fields are private and immutable except img, so a defensive copy is returned in image().
 * 
 * @author lt
 *
 */
public class Caption implements Expression {
    private final String capt;
    private final BufferedImage img;
    
    public Caption(String capt) {
        this.capt = capt;
        img = Examples.convertStringToImage(capt);
        checkRep();
    }
    
    private void checkRep() {
        assert capt.matches("[^\"\\n]*");
    }
    
    public String getCaption() {
        return capt;
    }
    
    @Override
    public String toString() {
        return "\""+capt+"\"";
        }

    @Override
    public boolean equals(Object that){
        return (that instanceof Caption) && capt.equals(((Caption)that).getCaption());
        }
    
    @Override
    public int hashCode(){
        return capt.hashCode();
        }

    public int getWidth(){
        return img.getWidth();
        }

    public int getHeight(){
        return img.getHeight();
        }

    public BufferedImage image(){
        return Expression.deepCopy(img);
        }
}