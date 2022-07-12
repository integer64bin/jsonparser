package jsonparser;

import jsonparser.ast.*;
import jsonparser.token.Token;
import jsonparser.token.TokenType;
import option.Options;

import java.util.List;

import static jsonparser.token.TokenType.*;

public class JSONParser {

    private static final Token EOE = new Token(TokenType.EOF,"");

    private final List<Token> tokens;
    private final int size;
    private int pos = 0;

    public JSONParser(List<Token> tokens) {
        this.tokens = tokens;
        this.size = tokens.size();
    }

    public Options parse(){
        consume(LBRACE);
        BlockValue values = new BlockValue();
        do {
            values.add(key());
        } while (match(COMMA));
        return values.getOptions();
    }

    private Key key() {
        if(get(0).getType() == TEXT) {
            String key = consume(TEXT).getText();
            consume(COLON);
            return new Key(key, value());
        }
        return null;
    }

    private Value value() {
        if(get(0).getType() == TEXT){
            return new TextValue(consume(TEXT).getText());
        }
        if(get(0).getType() == INT_NUMBER){
            return new IntValue(consume(INT_NUMBER).getText());
        }
        if(get(0).getType() == FLOAT_NUMBER){
            return new FloatValue(consume(FLOAT_NUMBER).getText());
        }
        if(match(LBRACE)) {
            return block();
        }
        if(match(LBRACKET)){
            return array();
        }
        if(get(0).getType() == BOOL){
            return new BoolValue(consume(BOOL).getText());
        }
        if(match(NULL))
            return new NullValue();
        return null;
    }

    private Value block() {
        BlockValue values = new BlockValue();
        do {
            values.add(key());
        } while (match(COMMA));
        consume(RBRACE);
        return values;
    }

    private Value array() {
        ArrayValue values = new ArrayValue();
        do {
            values.add(value());
        } while (match(COMMA));
        consume(RBRACKET);
        return values;
    }

    private Token consume(TokenType type) {
        Token current = get(0);
        if(current.getType() != type){
            throw new RuntimeException("Error");
        }
        pos++;
        return current;
    }

    private boolean match(TokenType type) {
        if(get(0).getType() == type){
            pos++;
            return true;
        }
        return false;
    }

    private Token get(int relativePosition){
        if(pos+relativePosition >= size){ return EOE;};
        return tokens.get(pos+relativePosition);
    }
}
