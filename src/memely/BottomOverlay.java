package memely;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

public class BottomOverlay extends TopOverlay {
    public BottomOverlay(Expression expr, Expression top) {
        super(expr,top);
    }
    
    @Override
    public String toString() {
        return "( "+expr.toString()+" _ "+top.toString()+" )";
        }
    
    @Override
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
        final int ExprUpperLeftY = Math.max(0, TopHeight - ExprHeight);
        final int TopUpperLeftX = Math.max(0, (ExprWidth - TopWidth)/2);
        final int TopUpperLeftY = Math.max(0, ExprHeight - TopHeight);

        
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