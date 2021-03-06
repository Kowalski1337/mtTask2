import java.io.IOException;
import java.io.InputStream;

public class Lexer {
    private InputStream is;
    private int curChar;
    private String name;
    private Token curToken;
    private int curPos;

    Lexer(InputStream is) throws ParseException {
        curPos = 0;
        this.is = is;
        nextChar();
    }

    private void nextChar() throws ParseException {
        curPos++;
        try {
            curChar = is.read();
        } catch (IOException e) {
            throw new ParseException("Problem was occurred while reading", curPos);
        }
    }

    private boolean isLetter(int c) {
        return c >= 'a' && c <= 'z' || c >= 'A' && c <= 'Z' || c == '_';
    }

    private boolean isNumber(int c) {
        return c >= '0' && c <= '9';
    }

    private boolean isLetterExtended(int c) {
        return isLetter(c) || isNumber(c);
    }

    public void nextToken() throws ParseException {
        while (Character.isWhitespace(curChar)) {
            nextChar();
        }
        if (isLetter(curChar)) {
            StringBuilder sb = new StringBuilder();
            sb.append((char)curChar);
            nextChar();
            while (isLetterExtended(curChar)) {
                sb.append((char)curChar);
                nextChar();
            }
            name = sb.toString();
            curToken = Token.WORD;
        } else {
            switch (curChar) {
                case '(':
                    curToken = Token.LPAREN;
                    nextChar();
                    break;
                case ')':
                    curToken = Token.RPAREN;
                    nextChar();
                    break;
                case '*':
                    curToken = Token.POINTER;
                    nextChar();
                    break;
                case ',':
                    curToken = Token.COMMA;
                    nextChar();
                    break;
                case ';':
                    curToken = Token.SEMI;
                    nextChar();
                    break;
                case  '&':
                    curToken = Token.AMPER;
                    nextChar();
                    break;
                case -1:
                    curToken = Token.END;
                    break;
                default:
                    throw new ParseException("Illegal symbol " + (char) curChar, curPos);
            }
        }
    }

    public String getName() {
        return name;
    }

    public Token getCurToken() {
        return curToken;
    }

    public int getCurPos(){
        return curPos;
    }

    public String showToken(){
        switch (curToken){
            case WORD:
                return "word: " + name;
            case POINTER:
                return "\'*\'";
            case END:
                return "end of input";
            case SEMI:
                return "\';\'";
            case LPAREN:
                return "\'(\'";
            case COMMA:
                return "\',\'";
            case RPAREN:
                return "\')\'";
                default:
                    return "";
        }
    }
}
