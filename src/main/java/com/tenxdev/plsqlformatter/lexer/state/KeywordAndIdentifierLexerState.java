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

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import com.tenxdev.plsqlformatter.lexer.PeekableInputStream;
import com.tenxdev.plsqlformatter.lexer.Token.TokenType;

public class KeywordAndIdentifierLexerState extends AbstractLexerState {

    private final Set<String> keywords = new TreeSet<>(Arrays.asList("ABORT", "ACCEPT", "ACCESS", "ADD", "ADMIN",
	    "AFTER", "ALL", "ALLOCATE", "ALTER", "ANALYZE", "AND", "ANY", "ARCHIVE", "ARCHIVELOG", "ARRAY", "ARRAYLEN",
	    "AS", "ASC", "ASSERT", "ASSIGN", "AT", "AUDIT", "AUTHORIZATION", "AVG", "BACKUP", "BASE_TABLE", "BECOME",
	    "BEFORE", "BEGIN", "BETWEEN", "BINARY_INTEGER", "BLOCK", "BODY", "BOOLEAN", "BY", "CACHE", "CANCEL",
	    "CASCADE", "CASE", "CHANGE", "CHAR", "CHAR_BASE", "CHARACTER", "CHECK", "CHECKPOINT", "CLOSE", "CLUSTER",
	    "CLUSTERS", "COBOL", "COLAUTH", "COLUMN", "COLUMNS", "COMMENT", "COMMIT", "COMPILE", "COMPRESS", "CONNECT",
	    "CONSTANT", "CONSTRAINT", "CONSTRAINTS", "CONTENTS", "CONTINUE", "CONTROLFILE", "COUNT", "CRASH", "CREATE",
	    "CURRENT", "CURRVAL", "CURSOR", "CYCLE", "DATA_BASE", "DATABASE", "DATAFILE", "DATE", "DBA", "DEBUGOFF",
	    "DEBUGON", "DEC", "DECIMAL", "DECLARE", "DEFAULT", "DEFINITION", "DELAY", "DELETE", "DELTA", "DESC",
	    "DIGITS", "DISABLE", "DISMOUNT", "DISPOSE", "DISTINCT", "DO", "DOUBLE", "DROP", "DUMP", "EACH", "ELSE",
	    "ELSIF", "ENABLE", "END", "ENTRY", "ESCAPE", "EVENTS", "EXCEPT", "EXCEPTION", "EXCEPTION_INIT",
	    "EXCEPTIONS", "EXCLUSIVE", "EXEC", "EXECUTE", "EXISTS", "EXIT", "EXPLAIN", "EXTENT", "EXTERNALLY", "FALSE",
	    "FETCH", "FILE", "FLOAT", "FLUSH", "FOR", "FORCE", "FOREIGN", "FORM", "FORTRAN", "FOUND", "FREELIST",
	    "FREELISTS", "FROM", "FUNCTION", "GENERIC", "GO", "GOTO", "GRANT", "GROUP", "GROUPS", "HAVING",
	    "IDENTIFIED", "IF", "IMMEDIATE", "IN", "INCLUDING", "INCREMENT", "INDEX", "INDEXES", "INDICATOR", "INITIAL",
	    "INITRANS", "INSERT", "INSTANCE", "INT", "INTEGER", "INTERSECT", "INTO", "IS", "KEY", "LANGUAGE", "LAYER",
	    "LEVEL", "LIKE", "LIMITED", "LINK", "LISTS", "LOCK", "LOGFILE", "LONG", "LOOP", "MANAGE", "MANUAL", "MAX",
	    "MAXDATAFILES", "MAXEXTENTS", "MAXINSTANCES", "MAXLOGFILES", "MAXLOGHISTORY", "MAXLOGMEMBERS", "MAXTRANS",
	    "MAXVALUE", "MIN", "MINEXTENTS", "MINUS", "MINVALUE", "MLSLABEL", "MOD", "MODE", "MODIFY", "MODULE",
	    "MOUNT", "NATURAL", "NEW", "NEXT", "NEXTVAL", "NOARCHIVELOG", "NOAUDIT", "NOCACHE", "NOCOMPRESS", "NOCYCLE",
	    "NOMAXVALUE", "NOMINVALUE", "NONE", "NOORDER", "NORESETLOGS", "NORMAL", "NOSORT", "NOT", "NOTFOUND",
	    "NOWAIT", "NULL", "NUMBER", "NUMBER_BASE", "NUMERIC", "OF", "OFF", "OFFLINE", "OLD", "ON", "ONLINE", "ONLY",
	    "OPEN", "OPTIMAL", "OPTION", "OR", "ORDER", "OTHERS", "OUT", "OWN", "PACKAGE", "PARALLEL", "PARTITION",
	    "PCTFREE", "PCTINCREASE", "PCTUSED", "PLAN", "PLI", "POSITIVE", "PRAGMA", "PRECISION", "PRIMARY", "PRIOR",
	    "PRIVATE", "PRIVILEGES", "PROCEDURE", "PROFILE", "PUBLIC", "QUOTA", "RAISE", "RANGE", "RAW", "READ", "REAL",
	    "RECORD", "RECOVER", "REFERENCES", "REFERENCING", "RELEASE", "REMR", "RENAME", "REPLACE", "RESETLOGS",
	    "RESOURCE", "RESTRICTED", "RETURN", "REUSE", "REVERSE", "REVOKE", "ROLE", "ROLES", "ROLLBACK", "ROW",
	    "ROWID", "ROWLABEL", "ROWNUM", "ROWS", "ROWTYPE", "RUN", "SAVEPOINT", "SCHEMA", "SCN", "SECTION", "SEGMENT",
	    "SELECT", "SEPARATE", "SEQUENCE", "SESSION", "SET", "SHARE", "SHARED", "SIZE", "SMALLINT", "SNAPSHOT",
	    "SOME", "SORT", "SPACE", "SQL", "SQLBUF", "SQLCODE", "SQLERRM", "SQLERROR", "SQLSTATE", "START",
	    "STATEMENT", "STATEMENT_ID", "STATISTICS", "STDDEV", "STOP", "STORAGE", "SUBTYPE", "SUCCESSFUL", "SUM",
	    "SWITCH", "SYNONYM", "SYSDATE", "SYSTEM", "TABAUTH", "TABLE", "TABLES", "TABLESPACE", "TASK", "TEMPORARY",
	    "TERMINATE", "THEN", "THREAD", "TIME", "TO", "TRACING", "TRANSACTION", "TRIGGER", "TRIGGERS", "TRUE",
	    "TRUNCATE", "TYPE", "UID", "UNDER", "UNION", "UNIQUE", "UNLIMITED", "UNTIL", "UPDATE", "USE", "USER",
	    "USING", "VALIDATE", "VALUES", "VARCHAR", "VARCHAR2", "VARIANCE", "VIEW", "VIEWS", "WHEN", "WHENEVER",
	    "WHERE", "WHILE", "WITH", "WORK", "WRITE", "XOR"));

    private final Map<String, TokenType> specialKeyWordTypes = new HashMap<>();

    public KeywordAndIdentifierLexerState() {
	specialKeyWordTypes.put("IS", TokenType.KEYWORD_AS_IS);
	specialKeyWordTypes.put("AS", TokenType.KEYWORD_AS_IS);
	specialKeyWordTypes.put("BEGIN", TokenType.KEYWORD_BEGIN);
	specialKeyWordTypes.put("END", TokenType.KEYWORD_END);
    }

    private boolean isNonNumericIdentifierCharacter(final int character) {
	return Character.isAlphabetic(character) || character == '$' || character == '_' || character == '#';
    }

    @Override
    protected TokenType process(final int currentCharacter, final PeekableInputStream inputStream) throws IOException {
	if (isNonNumericIdentifierCharacter(currentCharacter)) {
	    addCharacter(currentCharacter);
	    while (isNonNumericIdentifierCharacter(inputStream.peek()) || Character.isDigit(inputStream.peek())) {
		addCharacter(inputStream.read());
	    }
	    final String identifier = getText().toUpperCase();
	    final TokenType tokenType = specialKeyWordTypes.get(identifier);
	    if (tokenType != null) {
		if (tokenType == TokenType.KEYWORD_END) {
		    while (inputStream.peek() != ';' && inputStream.peek() != -1) {
			addCharacter(inputStream.read());
		    }
		}
		return tokenType;
	    } else {
		return keywords.contains(identifier) ? TokenType.KEYWORD : TokenType.IDENTIFIER;
	    }
	}
	return null;
    }

}
