package com.tenxdev.plsqlformatter.lexer;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

import static org.junit.Assert.*;

public class PlSqlLexerTest {

	@Test
	public void test() throws IOException {
		String input = "/*\na simple test\n*/create function foo(n in varchar2) is--a test function\nbegin\n\treturn n+1;\nend foo;";
		PlSqlLexer lexer = new PlSqlLexer();
		TokenStream tokenStream = lexer.lex(input);
		assertEquals(new Token(TokenType.BLOCK_COMMENT,"\na simple test\n"), tokenStream.next());
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
		assertEquals(new Token(TokenType.KEYWORD, "IS"), tokenStream.next());
		assertEquals(new Token(TokenType.LINE_COMMENT, "a test function"), tokenStream.next());
		assertEquals(new Token(TokenType.NEWLINE, "\n"), tokenStream.next());
		assertEquals(new Token(TokenType.KEYWORD, "BEGIN"), tokenStream.next());
		assertEquals(new Token(TokenType.NEWLINE, "\n"), tokenStream.next());
		assertEquals(new Token(TokenType.WHITESPACE, "\t"), tokenStream.next());
		assertEquals(new Token(TokenType.KEYWORD, "RETURN"), tokenStream.next());
		assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
		assertEquals(new Token(TokenType.IDENTIFIER, "N"), tokenStream.next());
		assertEquals(new Token(TokenType.OPERATOR, "+"), tokenStream.next());
		assertEquals(new Token(TokenType.NUMBER, "1"), tokenStream.next());
		assertEquals(new Token(TokenType.PUNCTUATION, ";"), tokenStream.next());
		assertEquals(new Token(TokenType.NEWLINE, "\n"), tokenStream.next());
		assertEquals(new Token(TokenType.KEYWORD, "END"), tokenStream.next());
		assertEquals(new Token(TokenType.WHITESPACE, " "), tokenStream.next());
		assertEquals(new Token(TokenType.IDENTIFIER, "FOO"), tokenStream.next());
		assertEquals(new Token(TokenType.PUNCTUATION, ";"), tokenStream.next());
		assertNull(tokenStream.next());
		assertNull(tokenStream.next());
		assertNull(tokenStream.next());
	}
}
