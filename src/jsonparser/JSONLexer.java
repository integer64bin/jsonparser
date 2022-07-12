package jsonparser;


import jsonparser.token.Token;
import jsonparser.token.TokenType;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jsonparser.token.TokenType.*;

public class JSONLexer {

    private static final String OPERATOR_CHARS = "{}[]:,";

    private static final Map<String,TokenType> OPERATORS = new HashMap<>();

    static {
        OPERATORS.put("{",LBRACE);
        OPERATORS.put("}",RBRACE);
        OPERATORS.put("[",LBRACKET);
        OPERATORS.put("]",RBRACKET);
        OPERATORS.put(":",COLON);
        OPERATORS.put(",",COMMA);
    }

    private String text;

    private int pos;

    private final int length;

    private List<Token> tokens;

    public JSONLexer(File path) throws IOException {
        tokens = new ArrayList<>();
        this.text = new String(Files.readAllBytes(path.toPath()));
        this.length = text.length();
    }

    public JSONLexer(String text) {
        tokens = new ArrayList<>();
        this.text = text;
        this.length = text.length();
    }

    public List<Token> tokenize(){
        while (pos <= length){
            if(OPERATOR_CHARS.indexOf(current()) != -1){
                addToken(OPERATORS.get(String.valueOf(current())));
            }
            if(current() == '"'){
                tokenizeText();
            }
            if(Character.isDigit(current()) || current() == '-'){
                tokenizeNumber();
                continue;
            }
            if(Character.isLetter(current())){
                tokenizeWord();
                continue;
            }
            if(current() == '/') {
                if (peek(1) == '*') {
                    tokenizeMultilineComments();
                }
                tokenizeComments();
            }
            next();
        }
        return tokens;
    }

    private void tokenizeWord() {
        final StringBuilder buffer = new StringBuilder();
        while (Character.isLetter(current())){
            buffer.append(current());
            next();
        }
        switch (buffer.toString()){
            case "false":addToken(BOOL,"false");break;
            case "true":addToken(BOOL,"true");break;
            case "null":addToken(NULL,"null");break;
        }
    }

    private void tokenizeMultilineComments() {
        while (true){
            if(peek(0) == '*' && peek(1) == '/') {
                next();
                next();
                break;
            }
            next();
        }
    }

    private void tokenizeComments() {
        while (current()!='\n')next();
    }

    private void tokenizeNumber() {
        final StringBuilder buffer = new StringBuilder();
        boolean isFloat = false;
        boolean isExponent = false;
        while (Character.isDigit(current()) || current() == '.' || current() == '-'){
            if(current() == '.')
                isFloat = true;
            buffer.append(current());
            next();
        }
        if(isFloat)
            addToken(FLOAT_NUMBER,buffer.toString());
        else
            addToken(INT_NUMBER, buffer.toString());
    }


    private void tokenizeText() {
        final StringBuilder buffer = new StringBuilder();
        next();
        while (current() != '"'){
            buffer.append(current());
            next();
        }
        addToken(TEXT,buffer.toString());
    }

    private char next(){
        pos++;
        return peek(0);
    }

    private char peek(int relativePosition) {
        if (pos+relativePosition >= length) return '\0';
        return text.charAt(pos+relativePosition);
    }

    private char current(){
        if (pos >= length) return '\0';
        return text.charAt(pos);
    }

    private void addToken(TokenType type, String text){
        tokens.add(new Token(type,text));
    }

    private void addToken(TokenType type){
        addToken(type,"");
    }

}
