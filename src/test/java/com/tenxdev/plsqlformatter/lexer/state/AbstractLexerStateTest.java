package com.tenxdev.plsqlformatter.lexer.state;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

import static org.junit.Assert.*;

import java.io.IOException;

public class AbstractLexerStateTest {

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNext() {
		BlockLabelLexerState lexerState = new BlockLabelLexerState();
		lexerState.setNextLexerState(lexerState);
	}

	@Test
	public void testNoNext() throws IOException {
		BlockLabelLexerState lexerState = new BlockLabelLexerState();
		try (PeekableInputStream in = new PeekableInputStream("abc")) {
			assertNull(lexerState.accept(in.read(), in));
		}
	}
	@Test
	public void testNext() throws IOException {
		BlockLabelLexerState lexerState = new BlockLabelLexerState();
		lexerState.setNextLexerState(new UnexpectedInputLexerState());
		try (PeekableInputStream in = new PeekableInputStream("abc")) {
			Token token = lexerState.accept(in.read(), in);
			assertEquals(TokenType.UNEXPECTED_CHARACTER, token.getTokenType());
			assertEquals("a", token.getText());
		}
	}

}
