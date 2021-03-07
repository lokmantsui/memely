/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package memely;

import java.io.File;
import java.io.IOException;

import edu.mit.eecs.parserlib.ParseTree;
import edu.mit.eecs.parserlib.Parser;
import edu.mit.eecs.parserlib.UnableToParseException;
import edu.mit.eecs.parserlib.Visualizer;

public class ExpressionParser {
    
    /**
     * Main method. Parses and then reprints an example expression.
     * 
     * @param args command line arguments, not used
     * @throws UnableToParseException if example expression can't be parsed
     */
    public static void main(final String[] args) throws UnableToParseException {
        final String input = "foo_bar.png|baz-qux.jpg";
        System.out.println(input);
        final Expression expression = ExpressionParser.parse(input);
        System.out.println(expression);
    }
    
    // the nonterminals of the grammar
    private static enum ExpressionGrammar {
        EXPRESSION, RESIZE, PRIMITIVE, TOPTOBOTTOMOPERATOR, FILENAME, NUMBER, WHITESPACE,
    }

    private static Parser<ExpressionGrammar> parser = makeParser();
    
    /**
     * Compile the grammar into a parser.
     * 
     * @return parser for the grammar in Expression.g
     * @throws RuntimeException if grammar file can't be read or has syntax errors
     */
    private static Parser<ExpressionGrammar> makeParser() {
        try {
            // read the grammar as a file, relative to the project root.
            final File grammarFile = new File("src/memely/Expression.g");
            return Parser.compile(grammarFile, ExpressionGrammar.EXPRESSION);
            
        // Parser.compile() throws two checked exceptions.
        // Translate these checked exceptions into unchecked RuntimeExceptions,
        // because these failures indicate internal bugs rather than client errors
        } catch (IOException e) {
            throw new RuntimeException("can't read the grammar file", e);
        } catch (UnableToParseException e) {
            throw new RuntimeException("the grammar has a syntax error", e);
        }
    }

    /**
     * Parse a string into an expression.
     * 
     * @param string string to parse
     * @return Expression parsed from the entire string
     * @throws UnableToParseException if the string doesn't match the Expression grammar
     */
    public static Expression parse(final String string) throws UnableToParseException {
        // parse the example into a parse tree
        final ParseTree<ExpressionGrammar> parseTree = parser.parse(string);

        // display the parse tree in various ways, for debugging only
        // System.out.println("parse tree " + parseTree);
        // Visualizer.showInBrowser(parseTree);

        // make an AST from the parse tree
        final Expression expression = makeAbstractSyntaxTree(parseTree);
        // System.out.println("AST " + expression);
        
        return expression;
    }
    
    /**
     * Convert a parse tree into an abstract syntax tree.
     * 
     * @param parseTree constructed according to the grammar in Expression.g
     * @return abstract syntax tree corresponding to the parseTree
     */
    private static Expression makeAbstractSyntaxTree(final ParseTree<ExpressionGrammar> parseTree) {
        switch (parseTree.name()) {
        case EXPRESSION: // expression ::= resize ('|' resize)*;
            {
                // TODO
                throw new RuntimeException("not implemented");
            }

        case RESIZE: // resize ::= primitive ('@' number 'x' number)*;
            {
                // TODO
                throw new RuntimeException("not implemented");
            }
            
        case PRIMITIVE: // primitive ::= filename | '(' expression ')';
            {
                // TODO
                throw new RuntimeException("not implemented");
            }
            
        case FILENAME: // filename ::= [A-Za-z0-9./][A-Za-z0-9./_-]*;
            {
                // TODO
                throw new RuntimeException("not implemented");
            }
            
        // ...
        // TODO more rules
        //
        
        default:
            throw new AssertionError("should never get here");
        }

    }

}
