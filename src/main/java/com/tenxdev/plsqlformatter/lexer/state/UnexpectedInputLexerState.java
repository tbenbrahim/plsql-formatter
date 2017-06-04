package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class UnexpectedInputLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		addCharacter(currentCharacter);
		return TokenType.UNEXPECTED_CHARACTER;
	}


}
