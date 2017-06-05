package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class LineCommentLexerState extends AbstractLexerState {

	public final int DASH = '-';

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (currentCharacter == DASH && inputStream.nextIs(DASH)){
			int c;
			while ((c = inputStream.peek()) != -1) {
				if (c == '\n' || c == '\r' ) {
					return TokenType.LINE_COMMENT;
				}
				addCharacter(inputStream.read());
			}
			return TokenType.LINE_COMMENT;
		}
		return null;
	}

}
