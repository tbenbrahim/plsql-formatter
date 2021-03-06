/*
   Copyright 2017 Tony BenBrahim

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.tenxdev.plsqlformatter.lexer;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

import static org.junit.Assert.*;

public class PlSqlLexerTest {

    @Test
    public void test() throws IOException {
	final String input = "/*\na simple test\n*/create function foo(n in varchar2) is--a test function\nbegin\n\treturn n+1;\nend foo;";
	final PlSqlLexer lexer = new PlSqlLexer();
	final TokenStream tokenStream = lexer.lex(input);
	assertEquals(new Token(TokenType.BLOCK_COMMENT, "\na simple test\n"), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD, "CREATE"), tokenStream.next());
	assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD, "FUNCTION"), tokenStream.next());
	assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
	assertEquals(new Token(TokenType.IDENTIFIER, "FOO"), tokenStream.next());
	assertEquals(new Token(TokenType.PUNCTUATION, "("), tokenStream.next());
	assertEquals(new Token(TokenType.IDENTIFIER, "N"), tokenStream.next());
	assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD, "IN"), tokenStream.next());
	assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD, "VARCHAR2"), tokenStream.next());
	assertEquals(new Token(TokenType.PUNCTUATION, ")"), tokenStream.next());
	assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD_AS_IS, "IS"), tokenStream.next());
	assertEquals(new Token(TokenType.LINE_COMMENT, "a test function"), tokenStream.next());
	assertEquals(new Token(TokenType.NEWLINE, "\n"), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD_BEGIN, "BEGIN"), tokenStream.next());
	assertEquals(new Token(TokenType.NEWLINE, "\n"), tokenStream.next());
	assertEquals(new Token(TokenType.WHITESPACE, "\t"), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD, "RETURN"), tokenStream.next());
	assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
	assertEquals(new Token(TokenType.IDENTIFIER, "N"), tokenStream.next());
	assertEquals(new Token(TokenType.OPERATOR, "+"), tokenStream.next());
	assertEquals(new Token(TokenType.NUMBER, "1"), tokenStream.next());
	assertEquals(new Token(TokenType.PUNCTUATION, ";"), tokenStream.next());
	assertEquals(new Token(TokenType.NEWLINE, "\n"), tokenStream.next());
	assertEquals(new Token(TokenType.KEYWORD_END, "end foo"), tokenStream.next());
	assertEquals(new Token(TokenType.PUNCTUATION, ";"), tokenStream.next());
	assertNull(tokenStream.next());
	assertNull(tokenStream.next());
	assertNull(tokenStream.next());
    }
}
