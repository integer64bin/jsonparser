package jsonparser.token;

public enum TokenType {

    //Key
    TEXT,

    //Value
    INT_NUMBER,
    FLOAT_NUMBER,

    //Token
    LBRACE,
    RBRACE,
    LBRACKET,
    RBRACKET,
    COLON,
    COMMA,

    //keywords
    BOOL,
    NULL,

    EOF;

}
