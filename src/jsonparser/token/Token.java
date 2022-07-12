package jsonparser.token;

public class Token {

    private final String text;
    private final TokenType type;

    public Token(TokenType type, String text) {
        this.type = type;
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public TokenType getType() {
        return type;
    }

    @Override
    public String toString() {
        return String.format("Type: %s Text: %s",type,text);
    }
}
