package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class LineCommentLexerState extends AbstractLexerState {

	public final int DASH = '-';

	@Override
	protected TokenType process(PeekableInputStream inputStream) throws IOException {
		int c;
		while ((c = inputStream.read()) != -1) {
			if (c == '\n' || c == '\r' && inputStream.nextIs('\n')) {
				return TokenType.LINE_COMMENT;
			}
			addCharacter(c);
		}
		return TokenType.LINE_COMMENT;
	}

	@Override
	public boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		return currentCharacter == DASH && inputStream.nextIs(DASH);
	}

}
