package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class DefaultLexerState extends AbstractLexerState {

	private int initialCharacter;

	private boolean isIdentifierCharacter(int character) {
		return Character.isAlphabetic(character) || character == '$' || character == '_' || character == '#';
	}

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		addCharacter(initialCharacter);
		int c;
		while ((c = inputStream.peek()) != -1) {
			if (isIdentifierCharacter(c) || Character.isDigit(c)) {
				addCharacter(inputStream.read());
			} else {
				break;
			}
		}
		return TokenType.IDENTIFIER;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (isIdentifierCharacter(currentCharacter)) {
			this.initialCharacter = currentCharacter;
			return true;
		}
		return false;
	}

}
