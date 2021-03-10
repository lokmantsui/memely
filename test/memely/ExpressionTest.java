/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.mit.eecs.parserlib.Parser;
import java.util.List;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    // parse(): 
    //   partition on Expression: Image, Caption, Hstack, Resize, TopOverlay
    
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
    
    // parse(): covered Image
    @Test
    public void testtoStringParseImage() {
        Image img1 = new Image("img/black.png");
        List<Expression> l = List.of(img1);
        for (Expression e: l) {
            assertEquals(e,Expression.parse(e.toString()),"invalid parse for"+e.toString());
        }
    }
    
     //parse(): covered Caption
    @Test
    public void testtoStringParseCaption() {
        Caption caption = new Caption("Hello there");
        List<Expression> l = List.of(caption);
        for (Expression e: l) {
            assertEquals(e,Expression.parse(e.toString()),"invalid parse for"+e.toString());
        }
    }
    
    // parse(): covered Hstack
    @Test
    public void testtoStringParseHstack() {
        Image img1 = new Image("img/black.png");
        Image img2 = new Image("img/white.png");
        Image img3 = new Image("img/red.png");
        Hstack hs = new Hstack(img1,img2);
        Hstack hs123 = new Hstack(hs,img3);
        Hstack hs312 = new Hstack(img3, hs);
        List<Expression> l = List.of(hs,hs123,hs312);
        for (Expression e: l) {
            assertEquals(e,Expression.parse(e.toString()),"invalid parse for"+e.toString());
        }
    }
    
    // parse(): covered Resize
    @Test
    public void testtoStringParseResize() {
        Image img1 = new Image("img/black.png");
        Resize rz = new Resize(img1,100,200);
        Resize rz2 = new Resize(img1,100,null);
        Resize rz3 = new Resize(img1,null,200);
        List<Expression> l = List.of(rz,rz2,rz3);
        for (Expression e: l) {
            assertEquals(e,Expression.parse(e.toString()),"invalid parse for"+e.toString());
        }
    }
    
    // parse(): covered TopOverlay
    @Test
    public void testtoStringParseTopOverlay() {
        Caption cap = new Caption("One does not simply");
        Image img = new Image("img/boromir.jpg");
        TopOverlay to = new TopOverlay(img, cap);
        List<Expression> l = List.of(to);
        for (Expression e: l) {
            assertEquals(e,Expression.parse(e.toString()),"invalid parse for"+e.toString());
        }
    }
    
}
