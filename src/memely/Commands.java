/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * String-based commands provided by the meme generation system.
 * 
 * <p>PS3 instructions: this is a required class.
 * You MUST NOT change its name or package or the names or type signatures of existing methods.
 * You MUST NOT add fields, constructors, or instance methods.
 * You may, however, add additional static methods, or strengthen the specs of existing methods.
 */
public class Commands {
    
    /**
     * Compute the size of an image expression.
     *
     * @param expression the meme expression to use
     * @return "WxH" (in the same syntax as the corresponding part of the @ resize operator)
     *         representing the width W and height H of the image generated by the expression, 
     *         according to the language definition in the PS3 handout
     * @throws IOException if any of the filenames used in the expression can't be read as images
     */
    public static String size(Expression expression) throws IOException {
        return expression.getWidth() + "x" + expression.getHeight();
    }
    
    /**
     * Generate the meme of an expression.
     *
     * @param expression the expression to generate
     * @return the image generated by the expression, according to the language definition in the PS3 handout
     * @throws IOException if any of the filenames used in the expression can't be read as images
     */
    public static BufferedImage image(Expression expression) throws IOException {
        return expression.image();
    }
    
}
