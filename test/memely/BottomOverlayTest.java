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
public class BottomOverlayTest {

    // Testing strategy
    // BottomOverlay():
    // getExpression():
    // getTop():
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
    
    // TopOverlay(): covered
    // getExpression():
    // getTop():
    // toString(): covered
    // equals(): covered
    // hashCode(): covered
    // getWidth(): covered
    // getHeight(): covered
    @Test
    public void testBottomOverlay() {
        Caption cap = new Caption("Walk into Mordor");
        Image img = new Image("img/boromir.jpg");
        BottomOverlay bo = new BottomOverlay(img, cap);
        assertEquals(bo.getExpression(),img);
        assertEquals(bo.getTop(),cap);
        assertEquals(bo.toString(),"( img/boromir.jpg _ \"Walk into Mordor\" )");
        BottomOverlay bo2 = new BottomOverlay(img, cap);
        Caption cap2 = new Caption("One does not simply");
        BottomOverlay bo3 = new BottomOverlay(img, cap2);
        assertEquals(bo,bo2);
        assertEquals(bo.hashCode()==bo2.hashCode(),true);
        assertEquals(bo.equals(bo3),false);
        assertEquals(bo.hashCode(),img.hashCode()+cap.hashCode());
        assertEquals(bo.getWidth(),Math.max(img.getWidth(), cap.getWidth()));
        assertEquals(bo.getHeight(),Math.max(img.getHeight(), cap.getHeight()));
    }
    // getWidth(): covered
    // getHeight(): covered
    // image(): covered
    @Test void testTopOverlayimage() {
        Image black = new Image("img/black.png");
        Image white = new Image("img/white.png");
        Resize bottom = new Resize(black,50,10);
        BottomOverlay bo = new BottomOverlay(white, bottom);
        assertEquals(bo.getWidth(),50);
        assertEquals(bo.getHeight(),30);
        BufferedImage bi = bo.image();
        final int abgrBlackPixel = 0xFF_00_00_00; // alpha=100%, blue=0%, green=0%, red=0%
        final int abgrWhitePixel = 0xFF_FF_FF_FF; // alpha=100%, blue=100%, green=100%, red=100%
        assertEquals(bi.getRGB(15, 5),abgrWhitePixel);
        assertEquals(bi.getRGB(25, 25),abgrBlackPixel);
    }
    
}
