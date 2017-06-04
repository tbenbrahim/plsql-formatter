package com.tenxdev.plsqlformatter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.Lexer;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.TokenStream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		try (FileInputStream input = new FileInputStream("c:\\scripts\\pkg_import.pkb")) {
			final TokenStream tokenStream = new Lexer().lex(input);
			Token token;
			while ((token = tokenStream.next()) != null) {
				System.out.print(token.toString()+" ");
			}
		}
	}
}
