package memely;

import java.awt.image.BufferedImage;

/**
 * AF(expr, top) = Expression with top overlayed on expr
 * RI: True
 * Safety from rep exposure: all fields are private final immutable
 * 
 * @author lt
 *
 */
public class TopOverlay implements Expression {
    private final Expression expr;
    private final Expression top;
    
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
        throw new RuntimeException("unimplemented");
        }
}