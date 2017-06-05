/*
   Copyright 2017 Tony BenBrahim

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.tenxdev.plsqlformatter.lexer.state;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

import static org.junit.Assert.*;

import java.io.IOException;

public class AbstractLexerStateTest {

	@Test(expected = IllegalArgumentException.class)
	public void testInvalidNext() {
		BlockLabelLexerState lexerState = new BlockLabelLexerState();
		lexerState.setNextLexerState(lexerState);
	}

	@Test
	public void testNoNext() throws IOException {
		BlockLabelLexerState lexerState = new BlockLabelLexerState();
		try (PeekableInputStream in = new PeekableInputStream("abc")) {
			assertNull(lexerState.accept(in.read(), in));
		}
	}
	@Test
	public void testNext() throws IOException {
		BlockLabelLexerState lexerState = new BlockLabelLexerState();
		lexerState.setNextLexerState(new UnexpectedInputLexerState());
		try (PeekableInputStream in = new PeekableInputStream("abc")) {
			Token token = lexerState.accept(in.read(), in);
			assertEquals(TokenType.UNEXPECTED_CHARACTER, token.getTokenType());
			assertEquals("a", token.getText());
		}
	}

}
