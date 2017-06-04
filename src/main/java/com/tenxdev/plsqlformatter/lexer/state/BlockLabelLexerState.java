package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

/**
 * consumes input until closing \/*
 */
public class BlockLabelLexerState extends AbstractLexerState {

	@Override
	protected TokenType process(int currentCharacter, PeekableInputStream inputStream) throws IOException {
		if (currentCharacter == '<' && inputStream.nextIs('<')){
			int c;
			while ((c = inputStream.read()) != -1) {
				if (c == '>' && inputStream.nextIs('>')) {
					return TokenType.BLOCK_LABEL;
				}
				addCharacter(c);
			}
			return TokenType.UNEXPECTED_EOF;
		}
		return null;
	}

}
