package memely;

import java.awt.image.BufferedImage;

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
        throw new RuntimeException("unimplemented");
        }
}
