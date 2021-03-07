/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

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
    //   TODO
    
    /**
     * Parse an expression.
     * @param input expression to parse, as defined in the PS3 handout
     * @return expression AST for the input
     * @throws IllegalArgumentException if the expression is syntactically invalid.
     */
    public static Expression parse(String input) {
        throw new RuntimeException("unimplemented");
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
    
    // TODO more instance methods, e.g. you might consider:
    //
    // public Dimension getSize(); or public int getWidth()/getHeight();
    // public BufferedImage image(); 
    
}
