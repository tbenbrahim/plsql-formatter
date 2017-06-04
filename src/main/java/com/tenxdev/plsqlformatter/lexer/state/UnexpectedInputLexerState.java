package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class UnexpectedInputLexerState extends AbstractLexerState {

	private int character;

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		addCharacter(character);
		return TokenType.UNEXPECTED_CHARACTER;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		this.character = currentCharacter;
		return true;
	}

}
