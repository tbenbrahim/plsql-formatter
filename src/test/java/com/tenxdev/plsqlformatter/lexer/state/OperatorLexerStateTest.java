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
import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class OperatorLexerStateTest {

	private final OperatorLexerState operatorLexerState = new OperatorLexerState();

	private List<String> operatorInputs = Arrays.asList("+", "-", "*", "=", "/", "<>", "!=", "^=", "<", "<=", ">", ">=",
			"||", ":=");
	private List<String> notOperatorInputs = Arrays.asList("--", "/*", "|", "^", "=>","<<",":","x");

	@Test
	public void testOperator() throws IOException {
		for (String expected : operatorInputs) {
			final String input = expected+"abc";
			try (PeekableInputStream in = new PeekableInputStream(input)) {
				final Token token = operatorLexerState.accept(in.read(), in);
				assertEquals(TokenType.OPERATOR, token.getTokenType());
				assertEquals(expected, token.getText());
				assertEquals('a', in.peek());
			}
		}
	}

	@Test
	public void testNotOperator() throws IOException {
		for (String expected : notOperatorInputs) {
			final String input = expected+"abc";
			try (PeekableInputStream in = new PeekableInputStream(input)) {
				final Token token = operatorLexerState.accept(in.read(), in);
				assertNull("for input "+input, token);
				assertEquals(input.charAt(1), in.peek());
			}
		}
	}

}
