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
public class HstackTest {

    // Testing strategy
    // Hstack():
    // getRight():
    // getLeft():
    // toString():
    // equals():
    //  partition on that not an instance of Hstack
    //               that is instance of Hstack with different left or different right
    //               that is instance of Hstack with same left and same right
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
    public void testHstack() {
        Image img1 = new Image("img/black.png");
        Image img2 = new Image("img/white.png");
        Hstack hs = new Hstack(img1,img2);
        assertEquals(hs.getRight(),img2);
        assertEquals(hs.getLeft(),img1);
        assertEquals(hs.toString(),"( img/black.png | img/white.png )");
        Hstack hs2 = new Hstack(img1,img2);
        Hstack hs3 = new Hstack(img2,img1);
        assertEquals(hs,hs2);
        assertEquals(hs.hashCode()==hs2.hashCode(),true);
        assertEquals(hs.equals(hs3),false);
        assertEquals(hs.hashCode(),img1.hashCode()+img2.hashCode());
        assertEquals(hs.getHeight(),30);
        assertEquals(hs.getWidth(),60);
        BufferedImage bi = hs.image();
        assertEquals(bi.getHeight(),30);
        assertEquals(bi.getWidth(),60);
        final int abgrBlackPixel = 0xFF_00_00_00; // alpha=100%, blue=0%, green=0%, red=0%
        final int abgrWhitePixel = 0xFF_FF_FF_FF; // alpha=100%, blue=100%, green=100%, red=100%
        assertEquals(bi.getRGB(15, 15),abgrBlackPixel);
        assertEquals(bi.getRGB(45, 15),abgrWhitePixel);
    }
    
}
