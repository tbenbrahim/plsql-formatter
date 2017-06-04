package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class PunctuationLexerState extends AbstractLexerState {

	private String input;

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		addString(input);
		return TokenType.PUNCTUATION;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		switch (currentCharacter) {
		case '.':
		case ';':
		case ',':
		case '%':
		case '(':
		case ')':
			input = Character.toString((char) currentCharacter);
			return true;
		case '=':
			if (inputStream.nextIs('>')) {
				input = "=>";
				return true;
			}
			return false;
		}
		return false;
	}

}
