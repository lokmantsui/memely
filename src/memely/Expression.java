/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;
import edu.mit.eecs.parserlib.UnableToParseException;

/**
 * An immutable data type representing an image expression, as defined
 * in the PS3 handout.
 * 
 * <p>PS3 instructions: this is a required ADT interface.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You may, however, add additional methods, or strengthen the specs of existing methods.
 * Declare concrete variants of Expression in their own Java source files.
 */
public interface Expression {
    
    // Datatype definition
    //   Expression = Image(filename:String)
    //                + Caption(capt:String)
    //                + Hstack(left:Expression, right:Expression)
    //                + Vstack(top:Expression, bottom:Expression)
    //                + Resize(expr:Expression, w:int, h:int)
    //                + TopOverlay(expr:Expression)
    //                + BottomOverlay(expr:Expression)
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is syntactically invalid.
     */
    public static Expression parse(String input){
        try {
            return ExpressionParser.parse(input);
        } catch (UnableToParseException e) {
            throw new RuntimeException("the expression has a syntax error", e);
        }
    }
    
    public static BufferedImage deepCopy(BufferedImage bi) {
        ColorModel cm = bi.getColorModel();
        boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
        WritableRaster raster = bi.copyData(null);
        return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
       }
    
    /**
     * @return a parsable representation of this expression, such that
     *         for all e:Expression, e.equals(Expression.parse(e.toString()))
     */
    @Override 
    public String toString();

    /**
     * @param that any object
     * @return true if and only if this and that are structurally-equal
     *         Expressions, as defined in the PS3 handout
     */
    @Override
    public boolean equals(Object that);
    
    /**
     * @return hash code value consistent with the equals() definition of structural
     *         equality, such that for all e1,e2:Expression,
     *             e1.equals(e2) implies e1.hashCode() == e2.hashCode()
     */
    @Override
    public int hashCode();
    
    // more instance methods, e.g. you might consider:
    //
    // public Dimension getSize(); or public int getWidth()/getHeight();
    // public BufferedImage image(); 
    
    /** 
     * @return the image width of the Expression, i.e. the number of pixels in the horizontal direction
     */
    public int getWidth();
    
    /** 
     * @return the height of the Expression, i.e. the number of pixels in the vertical direction
     */
    public int getHeight();
    
    /** 
     * @return the image of the Expression
     */
    public BufferedImage image();
    
}
