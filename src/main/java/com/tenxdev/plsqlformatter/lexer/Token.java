package com.tenxdev.plsqlformatter.lexer;

public class Token {

	public enum TokenType {
		UNEXPECTED_EOF, BLOCK_COMMENT, LINE_COMMENT, STRING, WHITESPACE, NEWLINE, PUNCTUATION, IDENTIFIER, OPERATOR, BLOCK_LABEL, UNEXPECTED_CHARACTER, ALIAS, NUMBER
	}

	private final TokenType tokenType;

	private final String text;

	public Token(TokenType tokenType) {
		this.tokenType = tokenType;
		this.text = null;
	}

	public Token(TokenType tokenType, String text) {
		this.tokenType = tokenType;
		this.text = text;
	}

	public String getText() {
		return text;
	}

	public TokenType getTokenType() {
		return tokenType;
	}

	@Override
	public String toString() {
		return text == null || text.isEmpty() ? tokenType.name() : String.format("%s(%s)", tokenType.name(), text);
	}
}
