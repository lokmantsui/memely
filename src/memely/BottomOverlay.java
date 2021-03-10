package memely;

import java.awt.image.BufferedImage;

public class BottomOverlay extends TopOverlay {
    public BottomOverlay(Expression expr, Expression bottom) {
        super(expr,bottom);
    }
    
    @Override
    public String toString() {
        throw new RuntimeException("unimplemented");
        }
    
    @Override
    public BufferedImage image(){
        throw new RuntimeException("unimplemented");
    }
}