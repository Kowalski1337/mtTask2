import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {
        /*new ParseTree("F",
                new ParseTree("int"),
                new ParseTree("S",
                        new ParseTree("*"),
                        new ParseTree("S",
                                new ParseTree("*"),
                                new ParseTree("S",
                                        new ParseTree("Eps")))),
                new ParseTree("foo"),
                new ParseTree("("),
                new ParseTree("int"),
                new ParseTree("S",
                        new ParseTree("*"),
                        new ParseTree("S",
                                new ParseTree("Eps"))),
                new ParseTree("a"),
                new ParseTree(","),
                new ParseTree("bool"),
                new ParseTree("S",
                        new ParseTree("Eps")),
                new ParseTree("b"),
                new ParseTree(")"),
                new ParseTree(";")
        ).toString(sb);
        System.out.println(sb.toString());*/
        try {
            StringBuilder sb = new StringBuilder();
            new Parser(Files.newInputStream(Paths.get("input.txt"))).parse().toString(sb);
            System.out.print(sb.toString());
        } catch (IOException e){
            System.err.println("Problems was occurred while opening inputStream");
        } catch (ParseException e){
            System.err.println("Lexer or parse error\n" + e.getMessage());
        }
    }
}
