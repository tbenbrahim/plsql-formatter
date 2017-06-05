package com.tenxdev.plsqlformatter.lexer.state;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStreamTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ BlockCommentLexerStateTest.class, StringLexerStateTest.class, LineCommentLexerStateTest.class,
		BlockLabelLexerStateTest.class, WhiteSpaceLexerStateTest.class, NewLineLexerStateTest.class,
		NumericLexerStateTest.class, AliasLexerStateTest.class, KeywordAndIdentifierLexerStateTest.class,
		UnexpectedInputLexerStateTest.class, OperatorLexerStateTest.class, PunctuationLexerStateTest.class,
		AbstractLexerStateTest.class})
public class LexerStateTestSuite {

}
