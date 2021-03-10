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
public class TopOverlayTest {

    // Testing strategy
    // TopOverlay():
    // getRight():
    // getLeft():
    // toString():
    // equals():
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
    public void testTopOverlay() {
        Caption cap = new Caption("One does not simply");
        Image img = new Image("img/boromir.jpg");
        TopOverlay to = new TopOverlay(img, cap);
        assertEquals(to.getExpression(),img);
        assertEquals(to.getTop(),cap);
        assertEquals(to.toString(),"( img/boromir.jpg ^ \"One does not simply\" )");
        TopOverlay to2 = new TopOverlay(img, cap);
        Caption cap2 = new Caption("Walk into Mordor");
        TopOverlay to3 = new TopOverlay(img, cap2);
        assertEquals(to,to2);
        assertEquals(to.hashCode()==to2.hashCode(),true);
        assertEquals(to.equals(to3),false);
        assertEquals(to.hashCode(),img.hashCode()+cap.hashCode());
        assertEquals(to.getWidth(),Math.max(img.getWidth(), cap.getWidth()));
        assertEquals(to.getHeight(),Math.max(img.getHeight(), cap.getHeight()));
    }
    
    @Test void testTopOverlayimage() {
        Image black = new Image("img/black.png");
        Image white = new Image("img/white.png");
        Resize top = new Resize(black,50,10);
        TopOverlay to = new TopOverlay(white, top);
        assertEquals(to.getWidth(),50);
        assertEquals(to.getHeight(),30);
        BufferedImage bi = to.image();
        final int abgrBlackPixel = 0xFF_00_00_00; // alpha=100%, blue=0%, green=0%, red=0%
        final int abgrWhitePixel = 0xFF_FF_FF_FF; // alpha=100%, blue=100%, green=100%, red=100%
        assertEquals(bi.getRGB(10, 5),abgrBlackPixel);
        assertEquals(bi.getRGB(25, 15),abgrWhitePixel);
    }
    
}
