package memely;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.util.Objects;

/**
 * AF(expr,w,h) = image expr resized to new size with width w and height h
 * RI: w > 0 and h > 0
 * Safety from rep exposure: all fields are private final immutable
 * @author lt
 *
 */
public class Resize implements Expression {
    private final int h;
    private final int w;
    private final Expression expr;
    
    public Resize(Expression expr, int w, int h) {
        this.expr = expr;
        this.w = w;
        this.h = h;
        checkRep();
    }
    
    public static Resize ResizeLeftUnknown(Expression expr, int h) {
        int w = expr.getWidth()*h/expr.getHeight();
        return new Resize(expr, w, h);
    }
    
    public static Resize ResizeRightUnknown(Expression expr, int w) {
        int h = expr.getHeight()*w/expr.getWidth();
        return new Resize(expr, w, h);
    }
    
    private void checkRep() {
        assert h>0 : "invalid height";
        assert w>0 : "invalid width";
    }
    
    public Expression getExpression() {
        return expr;
    }
    
    /**
     * @return a string of the form "\<expr in Strings\> @ \<w\>x\<h\>"
     */
    @Override
    public String toString() {
        return expr.toString()+" @ "+w+"x"+h;
        }

    @Override
    public boolean equals(Object that){
        if (!(that instanceof Resize)) return false;
        return ((Resize)that).getExpression().equals(expr) && ((Resize)that).getHeight()==h && ((Resize)that).getWidth()==w;
        }
    
    @Override
    public int hashCode(){
        return expr.hashCode()+Objects.hash(w,h);
        }

    public int getWidth(){
        return w;
        }

    public int getHeight(){
        return h;
        }

    public BufferedImage image(){
        
        final ImageObserver NO_OBSERVER_NEEDED = null;
        
        final int upperLeftX = 0;
        final int upperLeftY = 0;

        final BufferedImage input = expr.image();
        final BufferedImage output = new BufferedImage(w, h, BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = output.getGraphics();
        
        graphics.drawImage(input, 
                           upperLeftX, upperLeftY,
                           w, h, 
                           NO_OBSERVER_NEEDED);
        return output;
        }
    
}