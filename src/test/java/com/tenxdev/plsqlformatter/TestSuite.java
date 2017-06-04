package com.tenxdev.plsqlformatter;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStreamTest;
import com.tenxdev.plsqlformatter.lexer.state.BlockCommentLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.LineCommentLexerStateTest;
import com.tenxdev.plsqlformatter.lexer.state.StringLexerStateTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({ PeekableInputStreamTest.class, BlockCommentLexerStateTest.class, StringLexerStateTest.class,
		LineCommentLexerStateTest.class })
public class TestSuite {

}
