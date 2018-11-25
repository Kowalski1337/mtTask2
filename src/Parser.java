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
        switch (lex.getCurToken()) {
            case WORD:
                ParseTree fooType = new ParseTree("NAME", new ParseTree(lex.getName()));
                lex.nextToken();
                ParseTree starsNameFoo = parseStars();
                ParseTree nameFoo = new ParseTree("NAME", new ParseTree(lex.getName()));
                lex.nextToken();
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
                return new ParseTree("FOO", fooType, starsNameFoo, nameFoo, open, args, close, semi);
            default:
                throw new ParseException("Wrong first for FOO at position " + lex.getCurPos() + "\n");
        }
    }

    private ParseTree parseStars() throws ParseException {
        switch (lex.getCurToken()) {
            case STAR:
                lex.nextToken();
                return new ParseTree("STARS", new ParseTree("*"), parseStars());
            case WORD:
                break;
            default:
                throw new ParseException("Wrong first for STARS at position: " + lex.getCurPos() + "\n");

        }

        if (lex.getCurToken() != Token.WORD) {
            throw new ParseException("Wrong follow for STARS at position: " + lex.getCurPos() + "\n");
        }

        return new ParseTree("STARS", new ParseTree("ε"));
    }

    private ParseTree parseArgs() throws ParseException {
        switch (lex.getCurToken()) {
            case WORD:
                ParseTree argType = new ParseTree("NAME", new ParseTree(lex.getName()));
                lex.nextToken();
                ParseTree starsNameArg = parseStars();
                ParseTree argName = new ParseTree("NAME", new ParseTree(lex.getName()));
                lex.nextToken();
                ParseTree otherArgs = parseOtherArgs();
                return new ParseTree("ARGS", argType, starsNameArg, argName, otherArgs);
            case RPAREN:
                break;
            default:
                throw new ParseException("Wrong first for ARGS at position: " + lex.getCurPos() + "\n");
        }
        if (lex.getCurToken() != Token.RPAREN) {
            throw new ParseException("Wrong follow for ARGS at position: " + lex.getCurPos() + "\n");
        }
        return new ParseTree("ARGS", new ParseTree("ε"));

    }

    private ParseTree parseOtherArgs() throws ParseException {
        switch (lex.getCurToken()) {
            case COMMA:
                ParseTree comma = new ParseTree(lex.showToken());
                lex.nextToken();
                if (lex.getCurToken() != Token.WORD) {
                    throw new ParseException("Name of type expected at position: " + lex.getCurPos() + " but " + lex.showToken() + " found\n");
                }
                ParseTree argType = new ParseTree("NAME", new ParseTree(lex.getName()));
                lex.nextToken();
                ParseTree starsNameArg = parseStars();
                ParseTree argName = new ParseTree("NAME", new ParseTree(lex.getName()));
                lex.nextToken();
                ParseTree otherAgs = parseOtherArgs();
                return new ParseTree("ARGS\'", comma, argType, starsNameArg, argName, otherAgs);
            case RPAREN:
                break;
            default:
                throw new ParseException("Wrong first for ARGS\' at position: " + lex.getCurPos() + "\n");
        }
        if (lex.getCurToken() != Token.RPAREN) {
            throw new ParseException("Wrong follow for ARGS\' at position: " + lex.getCurPos() + "\n");
        }
        return new ParseTree("ARGS\'", new ParseTree("ε"));
    }
}
