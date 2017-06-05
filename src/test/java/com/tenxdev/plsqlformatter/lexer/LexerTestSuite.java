package com.tenxdev.plsqlformatter.lexer;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStreamTest;
import com.tenxdev.plsqlformatter.lexer.state.AliasLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.BlockCommentLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.BlockLabelLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.KeywordAndIdentifierLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.LexerStateTestSuite;
import com.tenxdev.plsqlformatter.lexer.state.LineCommentLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.NewLineLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.NumericLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.OperatorLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.PunctuationLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.StringLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.UnexpectedInputLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.WhiteSpaceLexerStateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PeekableInputStreamTest.class, TokenTest.class,PlSqlLexerTest.class, LexerStateTestSuite.class})
public class LexerTestSuite {

}
