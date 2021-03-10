package memely;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * AF(left,bottom) = Expression stacking top and bottom side-by-side.
 * RI: True
 * Safety from rep exposure: all fields are private final immutable.
 * 
 * 
 * @author lt
 *
 */
public class Vstack implements Expression {
    private final Expression top;
    private final Expression bottom;
    
    public Vstack(Expression top, Expression bottom) {
        this.top = top;
        this.bottom = bottom;
    }
    
    public Expression getBottom() {
        return bottom;
    }
    
    public Expression getTop() {
        return top;
    }
    
    /**
     * @return a string of the form "( \<top in Strings\> | \<bottom in Strings\> )"
     */
    @Override
    public String toString() {
        return "( "+top.toString()+" --- "+bottom.toString()+" )";
        }

    @Override
    public boolean equals(Object that){
        if (! (that instanceof Vstack)) return false;
        return ((Vstack)that).getTop().equals(top) && ((Vstack)that).getBottom().equals(bottom);
        }
    
    @Override
    public int hashCode(){
        return top.hashCode()+bottom.hashCode();
        }

    public int getWidth(){
        return Math.max(top.getWidth(), bottom.getWidth());
        }

    public int getHeight(){
        return top.getHeight() + bottom.getHeight();
        }

    public BufferedImage image(){
        
        final ImageObserver NO_OBSERVER_NEEDED = null;

        final BufferedImage row1img = top.image();
        final BufferedImage row2img = bottom.image();
        final BufferedImage output = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = output.getGraphics();
        
        final int upperLeftX = 0;
        final int upperLeftY = 0;
        final int widthTop = top.getWidth();
        final int widthBottom = bottom.getWidth();
        final int heightTop = top.getHeight();
        final int heightBottom = bottom.getHeight();
        final int row1UpperLeftX = Math.max(0, (widthBottom-widthTop)/2);
        final int row1UpperLeftY = 0;
        final int row1Width = widthTop;
        final int row1Height = heightTop;
        final int row2UpperLeftX = Math.max(0, (widthTop-widthBottom)/2);
        final int row2UpperLeftY = heightTop;
        final int row2Width = widthBottom;
        final int row2Height = heightBottom;
        
        graphics.drawImage(row1img, 
                row1UpperLeftX, row1UpperLeftY,
                row1Width, row1Height, 
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(row2img, 
                row2UpperLeftX, row2UpperLeftY,
                row2Width, row2Height, 
                           NO_OBSERVER_NEEDED);
        return output;
        }
}
