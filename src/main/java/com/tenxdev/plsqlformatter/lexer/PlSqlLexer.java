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
package com.tenxdev.plsqlformatter.lexer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import com.tenxdev.plsqlformatter.lexer.state.AliasLexerState;
import com.tenxdev.plsqlformatter.lexer.state.BlockCommentLexerState;
import com.tenxdev.plsqlformatter.lexer.state.BlockLabelLexerState;
import com.tenxdev.plsqlformatter.lexer.state.KeywordAndIdentifierLexerState;
import com.tenxdev.plsqlformatter.lexer.state.LexerState;
import com.tenxdev.plsqlformatter.lexer.state.LineCommentLexerState;
import com.tenxdev.plsqlformatter.lexer.state.NewLineLexerState;
import com.tenxdev.plsqlformatter.lexer.state.NumericLexerState;
import com.tenxdev.plsqlformatter.lexer.state.OperatorLexerState;
import com.tenxdev.plsqlformatter.lexer.state.PunctuationLexerState;
import com.tenxdev.plsqlformatter.lexer.state.StringLexerState;
import com.tenxdev.plsqlformatter.lexer.state.UnexpectedInputLexerState;
import com.tenxdev.plsqlformatter.lexer.state.WhiteSpaceLexerState;

public class PlSqlLexer {

	private LexerState lexerStateChain;

	public TokenStream lex(InputStream inputStream) {
		lexerStateChain = new BlockCommentLexerState();
		lexerStateChain.setNextLexerState(new LineCommentLexerState()).setNextLexerState(new StringLexerState())
				.setNextLexerState(new NewLineLexerState()).setNextLexerState(new WhiteSpaceLexerState())
				.setNextLexerState(new PunctuationLexerState()).setNextLexerState(new OperatorLexerState())
				.setNextLexerState(new BlockLabelLexerState()).setNextLexerState(new AliasLexerState())
				.setNextLexerState(new NumericLexerState()).setNextLexerState(new KeywordAndIdentifierLexerState())
				.setNextLexerState(new UnexpectedInputLexerState());
		final PeekableInputStream peekableInputStream = new PeekableInputStream(inputStream);
		return () -> next(peekableInputStream);

	}

	public TokenStream lex(String input) {
		return lex(input, StandardCharsets.UTF_8);
	}

	public TokenStream lex(String input, Charset charset) {
		return lex(new ByteArrayInputStream(input.getBytes(charset)));
	}

	private Token next(PeekableInputStream inputStream) throws IOException {
		int currentCharacter;
		while ((currentCharacter = inputStream.read()) != -1) {
			return lexerStateChain.accept(currentCharacter, inputStream);
		}
		return null;
	}

}
