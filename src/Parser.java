import java.io.InputStream;

public class Parser {
    private Lexer lex;

    public Parser(InputStream is) throws ParseException {
        lex = new Lexer(is);
    }

    public ParseTree parse() throws ParseException {
        lex.nextToken();
        return parseFoo();
    }

    private ParseTree parseFoo() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()) {
            case WORD:
                ParseTree fooType = parseType();
                ParseTree extra = parseExtra();
                ParseTree nameFoo = parseName();
                if (lex.getCurToken() != Token.LPAREN) {
                    throw new ParseException("\"(\" expected at position: " + lex.getCurPos() + ", but " + lex.showToken() + " found\n");
                }
                ParseTree open = new ParseTree(lex.showToken());
                lex.nextToken();
                ParseTree args = parseArgs();
                ParseTree close = new ParseTree(lex.showToken());
                lex.nextToken();
                if (lex.getCurToken() != Token.SEMI) {
                    throw new ParseException("\";\" expected at position: " + lex.getCurPos() + ", but " + lex.showToken() + " found\n");
                }
                ParseTree semi = new ParseTree(lex.showToken());
                lex.nextToken();
                if (lex.getCurToken() != Token.END) {
                    throw new ParseException("End of input expected at position: " + lex.getCurPos() + " but " + lex.showToken() + " found\n");
                }
                result = new ParseTree("FOO", fooType, extra, nameFoo, open, args, close, semi);
                break;
            default:
                throw new ParseException("Wrong first for FOO at position " + lex.getCurPos() + "\n");
        }
        return result;
    }

    private ParseTree parseExtra() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()){
            case POINTER:
                ParseTree pointer = parsePointer();
                result = new ParseTree("EXTRA", pointer);
                break;
            case AMPER:
                ParseTree amper = parseAmper();
                result = new ParseTree("EXTRA", amper);
                break;
            case WORD:
                result = new ParseTree("EXTRA", new ParseTree("ε"));
                break;
                default:
                    throw new ParseException("Wrong first for EXTRA at position: " + lex.getCurPos() + "\n");
        }
        if (lex.getCurToken() != Token.WORD){
            throw new ParseException("Wrong follow for EXTRA at position: " + lex.getCurPos() + "\n");
        }
        return result;
    }

    private ParseTree parsePointer() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()) {
            case POINTER:
                lex.nextToken();
                ParseTree otherPointers = parsePointer();
                result = new ParseTree("POINTER", new ParseTree("*"), otherPointers);
                break;
            case WORD:
                result = new ParseTree("POINTER", new ParseTree("ε"));
                break;
            default:
                throw new ParseException("Wrong first for POINTER at position: " + lex.getCurPos() + "\n");
        }

        if (lex.getCurToken() != Token.WORD) {
            throw new ParseException("Wrong follow for POINTER at position: " + lex.getCurPos() + "\n");
        }

        return result;
    }

    private ParseTree parseAmper() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()) {
            case AMPER:
                lex.nextToken();
                ParseTree otherAmpers = parseAmper();
                result = new ParseTree("AMPER", new ParseTree("&"), otherAmpers);
                break;
            case WORD:
                result = new ParseTree("AMPER", new ParseTree("ε"));
                break;
            default:
                throw new ParseException("Wrong first for AMPER at position: " + lex.getCurPos() + "\n");
        }

        if (lex.getCurToken() != Token.WORD) {
            throw new ParseException("Wrong follow for AMPER at position: " + lex.getCurPos() + "\n");
        }

        return result;
    }

    private ParseTree parseArgs() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()) {
            case WORD:
                ParseTree argType = parseType();
                ParseTree extra = parseExtra();
                ParseTree argName = parseName();
                ParseTree otherArgs = parseOtherArgs();
                result = new ParseTree("ARGS", argType, extra, argName, otherArgs);
                break;
            case RPAREN:
                result = new ParseTree("ARGS", new ParseTree("ε"));
                break;
            default:
                throw new ParseException("Wrong first for ARGS at position: " + lex.getCurPos() + "\n");
        }
        if (lex.getCurToken() != Token.RPAREN) {
            throw new ParseException("Wrong follow for ARGS at position: " + lex.getCurPos() + "\n");
        }
        return result;

    }

    private ParseTree parseOtherArgs() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()) {
            case COMMA:
                ParseTree comma = new ParseTree(lex.showToken());
                lex.nextToken();
                ParseTree argType = parseType();
                ParseTree extra = parseExtra();
                ParseTree argName = parseName();
                ParseTree otherArgs = parseOtherArgs();
                result = new ParseTree("ARGS\'", comma, argType, extra, argName, otherArgs);
                break;
            case RPAREN:
                result = new ParseTree("ARGS\'", new ParseTree("ε"));
                break;
            default:
                throw new ParseException("Wrong first for ARGS\' at position: " + lex.getCurPos() + "\n");
        }
        if (lex.getCurToken() != Token.RPAREN) {
            throw new ParseException("Wrong follow for ARGS\' at position: " + lex.getCurPos() + "\n");
        }
        return result;
    }

    private ParseTree parseType() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()) {
            case WORD:
                result = new ParseTree("TYPE", new ParseTree(lex.getName()));
                lex.nextToken();
                break;
                default:
                    throw new ParseException("Wrong first for TYPE at position: " + lex.getCurPos() + "\n");
        }
        if (lex.getCurToken() != Token.WORD && lex.getCurToken() != Token.POINTER && lex.getCurToken() != Token.AMPER){
            throw new ParseException("Wrong follow for TYPE at position: " + lex.getCurPos() + "\n");
        }
        return result;
    }

    private ParseTree parseName() throws ParseException {
        ParseTree result;
        switch (lex.getCurToken()) {
            case WORD:
                result = new ParseTree("NAME", new ParseTree(lex.getName()));
                lex.nextToken();
                break;
            default:
                throw new ParseException("Wrong first for NAME at position: " + lex.getCurPos() + "\n");
        }
        if (lex.getCurToken() != Token.LPAREN && lex.getCurToken() != Token.COMMA && lex.getCurToken() != Token.RPAREN){
            throw new ParseException("Wrong follow for NAME at position: " + lex.getCurPos() + "\n");
        }
        return result;
    }
}
