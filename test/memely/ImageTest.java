/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import java.awt.image.BufferedImage;
import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.mit.eecs.parserlib.Parser;

/**
 * Tests for the Expression abstract data type.
 */
public class ImageTest {

    // Testing strategy
    // Image():
    // getFilename():
    // toString():
    // equals():
    //  partition on that not an instance of Image
    //               that is instance of Image with different filename
    //               that is instance of Image with same filename
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
    
    // Image(): covered
    // getFilename(): covered
    // toString(): covered
    // equals(): covered that not an instance of Image
    //                   that is instance of Image with different filename
    //                   that is instance of Image with same filename
    // hashCode(): covered
    // getWidth(): covered
    // getHeight(): covered
    // image(): covered
    @Test
    public void testImage() {
        Image img = new Image("img/black.png");
        Image img2 = new Image("img/white.png");
        Image img3 = new Image("img/black.png");
        assertEquals(img.getFilename(),"img/black.png");
        assertEquals(img.toString(),"img/black.png");
        assertEquals(img,img3);
        assertEquals(img.hashCode()==img3.hashCode(),true);
        assertEquals(img.equals(img2),false);
        assertEquals(img.equals("img/black.png"),false);
        String str = "img/black.png";
        assertEquals(img.hashCode(),str.hashCode());
        assertEquals(img.getHeight(),30);
        assertEquals(img.getWidth(),30);
        BufferedImage bi = img.image();
        assertEquals(bi.getHeight(),30);
        assertEquals(bi.getWidth(),30);
        final int abgrBlackPixel = 0xFF_00_00_00; 
        assertEquals(bi.getRGB(15, 15),abgrBlackPixel);
    }
        
}
