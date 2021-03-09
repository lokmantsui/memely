/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.mit.eecs.parserlib.Parser;

/**
 * Tests for the Expression abstract data type.
 */
public class ExpressionTest {

    // Testing strategy
    // parse:
    
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
    
    // parse: covered
    @Test
    public void testtoStringParse() {
        Expression img1 = new Image("img/black.png");
        assertEquals(img1,Expression.parse(img1.toString()));
        Expression img2 = new Image("img/white.png");
        Hstack hs = new Hstack(img1,img2);
        assertEquals(hs,Expression.parse(hs.toString()));
        Resize rz = new Resize(hs,100,200);
        assertEquals(rz,Expression.parse(rz.toString()));
    }
    
}
