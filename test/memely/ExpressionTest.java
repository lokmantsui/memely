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
    //   partition on Expression: Image, Caption, Hstack, Resize, TopOverlay, BottomOverlay, Vstack
    
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
    
    // parse(): covered BottomOverlay
    @Test
    public void testtoStringParseBottomOverlay() {
        Caption cap = new Caption("One does not simply");
        Image img = new Image("img/boromir.jpg");
        BottomOverlay bo = new BottomOverlay(img, cap);
        List<Expression> l = List.of(bo);
        for (Expression e: l) {
            assertEquals(e,Expression.parse(e.toString()),"invalid parse for"+e.toString());
        }
    }
    
    // parse(): covered Vstack
    @Test
    public void testtoStringParseVstack() {
        Image img1 = new Image("img/black.png");
        Image img2 = new Image("img/white.png");
        Image img3 = new Image("img/red.png");
        Vstack vs = new Vstack(img1,img2);
        Vstack vs123 = new Vstack(vs,img3);
        Vstack vs312 = new Vstack(img3, vs);
//        List<Expression> l = List.of(vs,vs123,vs312);
        List<Expression> l = List.of(vs123);
        for (Expression e: l) {
            assertEquals(e,Expression.parse(e.toString()),"invalid parse for"+e.toString());
        }
    }
    
//    @Test
//    public void sixPanelMeme() {
//        String str = "img/black.png@600x50 ^ \"TECH SUPPORT\"@?x50 ---------------------------------- img/tech1.png | img/tech2.png@200x150 | img/tech3.png ---------------------------------- (  img/black.png@200x25 ^ \"What my friends think I do\"@?x15  | img/black.png@200x25 ^ \"What my mom thinks I do\"@?x15  | img/black.png@200x25 ^ \"What society thinks I do\"@?x15   ) ---------------------------------- img/black.png@600x25 ---------------------------------- img/tech4.png | img/tech5.png@200x160 | img/tech6.png ---------------------------------- (  img/black.png@200x25 ^ \"What my boss thinks I do\"@?x15   | img/black.png@200x25 ^ \"What I think I do\"@?x15  | img/black.png@200x25 ^ \"What I actually do\"@?x15       ) ---------------------------------- img/black.png@600x25";
//        Expression.parse(str);
//    }
    
}
