package memely;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * AF(left,right) = Expression stacking left and right side-by-side.
 * RI: True
 * Safety from rep exposure: all fields are private final immutable.
 * 
 * 
 * @author lt
 *
 */
public class Hstack implements Expression {
    private final Expression left;
    private final Expression right;
    
    public Hstack(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }
    
    public Expression getRight() {
        return right;
    }
    
    public Expression getLeft() {
        return left;
    }
    
    /**
     * @return a string of the form "( \<left in Strings\> | \<right in Strings\> )"
     */
    @Override
    public String toString() {
        return "( "+left.toString()+" | "+right.toString()+" )";
        }

    @Override
    public boolean equals(Object that){
        if (! (that instanceof Hstack)) return false;
        return ((Hstack)that).left.equals(left) && ((Hstack)that).right.equals(right);
        }
    
    @Override
    public int hashCode(){
        return left.hashCode()+right.hashCode();
        }

    public int getWidth(){
        return left.getWidth() + right.getWidth();
        }

    public int getHeight(){
        return Math.max(left.getHeight(), right.getHeight());
        }

    public BufferedImage image(){
        
        final ImageObserver NO_OBSERVER_NEEDED = null;

        final BufferedImage column1img = left.image();
        final BufferedImage column2img = right.image();
        final BufferedImage output = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = output.getGraphics();
        
        final int upperLeftX = 0;
        final int upperLeftY = 0;
        final int widthLeft = left.getWidth();
        final int widthRight = right.getWidth();
        final int heightLeft = left.getHeight();
        final int heightRight = right.getHeight();
        final int column1UpperLeftX = 0;
        final int column1UpperLeftY = Math.max(0, (heightRight-heightLeft)/2);
        final int column1Width = widthLeft;
        final int column1Height = heightLeft;
        final int column2UpperLeftX = widthLeft;
        final int column2UpperLeftY = Math.max(0, (heightLeft-heightRight)/2);
        final int column2Width = widthRight;
        final int column2Height = heightRight;
        
        graphics.drawImage(column1img, 
                column1UpperLeftX, column1UpperLeftY,
                column1Width, column1Height, 
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(column2img, 
                column2UpperLeftX, column2UpperLeftY,
                column2Width, column2Height, 
                           NO_OBSERVER_NEEDED);
        return output;
        }
}
