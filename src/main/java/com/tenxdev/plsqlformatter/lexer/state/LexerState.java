package com.tenxdev.plsqlformatter.lexer.state;

import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;

public interface LexerState {

	Token enter(PeekableInputStream inputStream) throws IOException;

	/**
	 * test if the lexer state should be entered. If this returns true, the
	 * lexer state MUST be entered as the test coould have consumed characters
	 * from the input stream
	 * 
	 * @param currentCharacter
	 *            the current character
	 * @param inputStream
	 *            the input stream
	 * @return true if the state should be entered, false otherwise
	 * @throws IOException
	 */
	boolean test(int currentCharacter, PeekableInputStream inputStream) throws IOException;

}
