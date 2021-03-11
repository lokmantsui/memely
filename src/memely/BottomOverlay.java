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
    protected int getExprY(int ExprHeight, int TopHeight) {
        return Math.max(0, TopHeight - ExprHeight);
    }
    
    @Override
    protected int getTopY(int ExprHeight, int TopHeight) {
        return Math.max(0, ExprHeight - TopHeight);
    }
}