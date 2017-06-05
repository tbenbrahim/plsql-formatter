package com.tenxdev.plsqlformatter.lexer;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

import static org.junit.Assert.*;

public class TokenTest {

	@Test
	public void test() {
		for (TokenType tokenType : TokenType.values()) {
			String input = "abc";
			Token token = new Token(tokenType, input);
			assertEquals(tokenType, token.getTokenType());
			assertEquals(input, token.getText());
		}
	}

	@Test
	public void testToString() {
		for (TokenType tokenType : TokenType.values()) {
			String input = "abc";
			Token token = new Token(tokenType, input);
			String expected = String.format("%s(%s)", tokenType.name(), input);
			assertEquals(expected, token.toString());
		}
	}

	@Test
	public void testEquals() {
		for (TokenType tokenType : TokenType.values()) {
			if (tokenType.isCasePreserved()) {
				assertNotEquals(new Token(tokenType, "abc"), new Token(tokenType, "ABC"));
			} else {
				assertEquals(new Token(tokenType, "abc"), new Token(tokenType, "ABC"));
			}
			assertEquals(new Token(tokenType, "abc"), new Token(tokenType, "abc"));
			assertNotEquals(new Token(tokenType, "abc"), new Token(tokenType, "abcd"));
		}
		assertNotEquals(new Token(TokenType.ALIAS, "abc"), "abc");
		assertNotEquals(new Token(TokenType.ALIAS, "abc"), new Token(TokenType.BLOCK_COMMENT, "abc"));
		assertEquals(new Token(TokenType.WHITESPACE, ""), new Token(TokenType.WHITESPACE, null));
	}

	@Test
	public void testHashcode() {
		for (TokenType tokenType : TokenType.values()) {
			if (!tokenType.isCasePreserved()) {
				assertEquals(new Token(tokenType, "abc").hashCode(), new Token(tokenType, "ABC").hashCode());
			}
			assertEquals(new Token(tokenType, "abc").hashCode(), new Token(tokenType, "abc").hashCode());
		}
	}

}
