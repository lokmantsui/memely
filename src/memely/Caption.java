package memely;

import java.awt.image.BufferedImage;

/**AF(str) = caption corresponding to str
 * RI: str does not have newline or double quote
 * Safety from rep exposure:
 * all fields are private and immutable
 * 
 * @author lt
 *
 */
public class Caption implements Expression {
    private final String capt; 
    
    public Caption(String capt) {
        this.capt = capt;
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
        throw new RuntimeException("unimplemented");
        }

    public int getHeight(){
        throw new RuntimeException("unimplemented");
        }

    public BufferedImage image(){
        throw new RuntimeException("unimplemented");
        }
}