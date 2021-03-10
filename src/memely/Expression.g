/* Copyright (c) 2017-2020 MIT 6.031 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */

@skip whitespace {
    expression ::= resize ('|' resize)*;
    resize ::= primitive ('@' (number 'x' number | unknown 'x' number | number 'x' unknown) )*;
    primitive ::= filename | '(' expression ')';
}
topToBottomOperator ::= '---' '-'*;
filename ::= [A-Za-z0-9./][A-Za-z0-9./_-]*;
number ::= [0-9]+;
unknown ::= '?';
whitespace ::= [ \t\r\n]+;
