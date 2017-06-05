package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class AliasLexerStateTest {

	private final AliasLexerState aliasLexerState = new AliasLexerState();

	@Test
	public void test() throws IOException {
		final String input = "\"test\"a";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = aliasLexerState.accept(in.read(), in);
			assertEquals(TokenType.ALIAS, token.getTokenType());
			assertEquals("test", token.getText());
			assertEquals('a', in.peek());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "\"test";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = aliasLexerState.accept(in.read(), in);
			assertEquals(TokenType.UNEXPECTED_EOF, token.getTokenType());
		}
	}

	@Test
	public void testNotAlias() throws IOException {
		final String input = "'test'";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(aliasLexerState.accept(in.read(), in));
		}
	}

}
