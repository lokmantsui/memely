/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.mit.eecs.parserlib.Parser;
import java.awt.image.BufferedImage;
/**
 * Tests for the Expression abstract data type.
 */
public class CaptionTest {

    // Testing strategy
    // Caption():
    // getCaption():
    // toString():
    // equals():
    //  partition on same Caption
    //               different Caption
    // hashCode():
    // getWidth():
    // getHeight():
    // image():
    
    @Test
    public void testCaption() {
        Caption caption = new Caption("Hello there");
        assertEquals(caption.getCaption(),"Hello there");
        assertEquals(caption.toString(),"\"Hello there\"");
        Caption caption2 = new Caption("Hello there");
        assertEquals(caption,caption2);
        assertEquals(caption.hashCode()==caption2.hashCode(),true);
        Caption caption3 = new Caption("General Kenobi");
        assertEquals(caption.equals(caption3),false);
        assertEquals(caption.hashCode(),"Hello there".hashCode());
        BufferedImage bi = Examples.convertStringToImage("Hello there");
        assertEquals(caption.getHeight(),bi.getHeight());
        assertEquals(caption.getWidth(),bi.getWidth());
    }
        
}
