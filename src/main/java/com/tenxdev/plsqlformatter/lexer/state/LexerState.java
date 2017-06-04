package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;

public interface LexerState {
	
	LexerState setNextLexerState(LexerState lexerState);
	
	Token accept(int currentCharacter, PeekableInputStream inputStream) throws IOException;

}
