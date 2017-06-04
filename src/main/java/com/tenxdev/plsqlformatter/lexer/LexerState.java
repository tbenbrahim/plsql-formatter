package com.tenxdev.plsqlformatter.lexer;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;

public interface LexerState {

	Token enter(PeekableInputStream inputStream) throws IOException;

}
