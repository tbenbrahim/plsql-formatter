package com.tenxdev.plsqlformatter.lexer.state;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class BlockLabelLexerStateTest {

	private final BlockLabelLexerState blockLabelLexerState = new BlockLabelLexerState();

	@Test
	public void test() throws IOException {
		final String input = "<<test>>123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = blockLabelLexerState.accept(in.read(), in);
			assertEquals(TokenType.BLOCK_LABEL, token.getTokenType());
			assertEquals("test", token.getText());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "<<test>123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = blockLabelLexerState.accept(in.read(), in);
			assertEquals(TokenType.UNEXPECTED_EOF, token.getTokenType());
		}
	}

	@Test
	public void testNotBlockLabel() throws IOException {
		final String input = "<test>";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(blockLabelLexerState.accept(in.read(), in));
		}
	}

}
