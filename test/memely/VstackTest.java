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
public class VstackTest {

    // Testing strategy
    // Vstack():
    // getTop():
    // getBottom():
    // toString():
    // equals():
    //  partition on that not an instance of Vstack
    //               that is instance of Vstack with different top or different bottom
    //               that is instance of Vstack with same top and same bottom
    // hashCode():
    // getWidth():
    // getHeight():
    // image():

    
    @Test
    public void testAssertionsEnabled() {
        assertThrows(AssertionError.class, () -> { assert false; },
                "make sure assertions are enabled with VM argument '-ea'");
    }
    
    @Test
    public void testParserLibVersion() {
        assertThat("parserlib.jar needs to be version 3.2.x",
                Parser.VERSION, startsWith("3.2"));
    }
    

    @Test
    public void testVstack() {
        Image img1 = new Image("img/black.png");
        Image img2 = new Image("img/white.png");
        Vstack vs = new Vstack(img1,img2);
        assertEquals(vs.getBottom(),img2);
        assertEquals(vs.getTop(),img1);
        assertEquals(vs.toString(),"( img/black.png --- img/white.png )");
        Vstack vs2 = new Vstack(img1,img2);
        Vstack vs3 = new Vstack(img2,img1);
        assertEquals(vs,vs2);
        assertEquals(vs.hashCode()==vs2.hashCode(),true);
        assertEquals(vs.equals(vs3),false);
        assertEquals(vs.hashCode(),img1.hashCode()+img2.hashCode());
        assertEquals(vs.getHeight(),60);
        assertEquals(vs.getWidth(),30);
        BufferedImage bi = vs.image();
        assertEquals(bi.getHeight(),60);
        assertEquals(bi.getWidth(),30);
        final int abgrBlackPixel = 0xFF_00_00_00; // alpha=100%, blue=0%, green=0%, red=0%
        final int abgrWhitePixel = 0xFF_FF_FF_FF; // alpha=100%, blue=100%, green=100%, red=100%
        assertEquals(bi.getRGB(15, 15),abgrBlackPixel);
        assertEquals(bi.getRGB(15, 45),abgrWhitePixel);
    }
    
}
