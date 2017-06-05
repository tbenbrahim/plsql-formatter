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
package com.tenxdev.plsqlformatter;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.tenxdev.plsqlformatter.lexer.PlSqlLexer;
import com.tenxdev.plsqlformatter.lexer.Token;
import com.tenxdev.plsqlformatter.lexer.TokenStream;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		for (int i = 1; i <= 7; ++i) {
			try (FileInputStream input = new FileInputStream(String.format("c:\\scripts\\samples\\%d.sql", i))) {
				final TokenStream tokenStream = new PlSqlLexer().lex(input);
				Token token;
				while ((token = tokenStream.next()) != null) {
					System.out.print(token.toString() + " ");
				}
			}
		}
	}
}
