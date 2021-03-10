/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.image.BufferedImage;
import java.util.Objects;

import org.junit.jupiter.api.Test;

import edu.mit.eecs.parserlib.Parser;

/**
 * Tests for the Expression abstract data type.
 */
public class ResizeTest {

    // Testing strategy
    // Resize():
    // getExpression():
    // toString():
    // equals():
    //   partition on that equals this, that not equals this
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
    
    // Resize(): covered
    // getExpression(): covered
    // toString(): covered
    // equals(): covered that equals this, that not equals this
    // hashCode(): covered
    // getWidth(): covered
    // getHeight(): covered
    // image(): covered
    @Test
    public void testResize() {
        Image img = new Image("img/black.png");
        Resize rz = new Resize(img,100,200);
        assertEquals(rz.getExpression(),img);
        assertEquals(rz.toString(),"img/black.png @ 100x200");
        Image img2 = new Image("img/black.png");
        Resize rz2 = new Resize(img2,100,200);
        Resize rz3 = new Resize(img2,200,100);
        assertEquals(rz,rz2);
        assertEquals(rz.hashCode()==rz2.hashCode(),true);
        assertEquals(rz.equals(rz3),false);
        String str = "img/black.png";
        assertEquals(rz.hashCode(),str.hashCode()+Objects.hash(100,200));
        assertEquals(rz.getWidth(),100);
        assertEquals(rz.getHeight(),200);
        BufferedImage bi = rz.image();
        assertEquals(bi.getWidth(),100);
        assertEquals(bi.getHeight(),200);
    }
    
}
