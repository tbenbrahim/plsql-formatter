package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class NewLineLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		return TokenType.NEWLINE;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		return currentCharacter == '\n' || currentCharacter == '\r' && inputStream.nextIs('\n');
	}

}
