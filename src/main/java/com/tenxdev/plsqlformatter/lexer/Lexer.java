package com.tenxdev.plsqlformatter.lexer;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

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

public class Lexer {

	private final List<LexerState> lexerStates = Arrays.asList(new BlockCommentLexerState(),
			new LineCommentLexerState(), new StringLexerState(), new NewLineLexerState(), new WhiteSpaceLexerState(),
			new PunctuationLexerState(), new OperatorLexerState(), new BlockLabelLexerState(), new AliasLexerState(),
			new NumericLexerState(), new KeywordAndIdentifierLexerState());
	
	private UnexpectedInputLexerState unexpectedInputLexerState=new UnexpectedInputLexerState();

	public TokenStream lex(InputStream inputStream) {
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
		int c;
		while ((c = inputStream.read()) != -1) {
			for (final LexerState lexerState : lexerStates) {
				if (lexerState.test(c, inputStream)) {
					return lexerState.enter(inputStream);
				}
				unexpectedInputLexerState.enter(inputStream);
			}
		}
		return null;
	}

}
