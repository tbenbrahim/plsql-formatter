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

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.io.IOException;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class BlockLabelLexerStateTest {

	private final BlockLabelLexerState blockLabelLexerState = new BlockLabelLexerState();

	@Test
	public void test() throws IOException {
		final String input = "<<test>>123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = blockLabelLexerState.accept(in.read(), in);
			assertEquals(TokenType.BLOCK_LABEL, token.getTokenType());
			assertEquals("test", token.getText());
		}
	}

	@Test
	public void testEof() throws IOException {
		final String input = "<<test>123";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			final Token token = blockLabelLexerState.accept(in.read(), in);
			assertEquals(TokenType.UNEXPECTED_EOF, token.getTokenType());
		}
	}

	@Test
	public void testNotBlockLabel() throws IOException {
		final String input = "<test>";
		try (PeekableInputStream in = new PeekableInputStream(input)) {
			assertNull(blockLabelLexerState.accept(in.read(), in));
		}
	}

}
