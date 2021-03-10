package memely;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

/**
 * AF(expr, top) = Expression with top overlayed on expr
 * RI: True
 * Safety from rep exposure: all fields are private final immutable
 * 
 * @author lt
 *
 */
public class TopOverlay implements Expression {
    protected final Expression expr;
    protected final Expression top;
    
    public TopOverlay(Expression expr, Expression top) {
        this.expr = expr;
        this.top = top;
    }
    
    public Expression getExpression() {
        return expr;
    }
    
    public Expression getTop() {
        return top;
    }
    
    @Override
    public String toString() {
        return "( "+expr.toString()+" ^ "+top.toString()+" )";
        }

    @Override
    public boolean equals(Object that){
        if (!(that instanceof TopOverlay)) return false;
        return ((TopOverlay)that).getExpression().equals(expr) && ((TopOverlay)that).getTop().equals(top);
        }
    
    @Override
    public int hashCode(){
        return expr.hashCode()+top.hashCode();
        }

    public int getWidth(){
        return Math.max(expr.getWidth(), top.getWidth());
        }

    public int getHeight(){
        return Math.max(expr.getHeight(), top.getHeight());
        }

    public BufferedImage image(){
        final ImageObserver NO_OBSERVER_NEEDED = null;

        final BufferedImage exprimg = expr.image();
        final BufferedImage topimg = top.image();
        final BufferedImage output = new BufferedImage(this.getWidth(), this.getHeight(), BufferedImage.TYPE_4BYTE_ABGR);
        final Graphics graphics = output.getGraphics();
        
        final int ExprWidth = expr.getWidth();
        final int ExprHeight = expr.getHeight();
        final int TopWidth = top.getWidth();
        final int TopHeight = top.getHeight();
        final int ExprUpperLeftX = Math.max(0, (TopWidth - ExprWidth)/2);
        final int ExprUpperLeftY = 0;
        final int TopUpperLeftX = Math.max(0, (ExprWidth - TopWidth)/2);
        final int TopUpperLeftY = 0;

        
        graphics.drawImage(exprimg, 
                ExprUpperLeftX, ExprUpperLeftY,
                ExprWidth, ExprHeight, 
                           NO_OBSERVER_NEEDED);
        graphics.drawImage(topimg, 
                TopUpperLeftX, TopUpperLeftY,
                TopWidth, TopHeight, 
                           NO_OBSERVER_NEEDED);
        return output;
        }
}