import tree.ParseTree;

public class Main {

    public static void main(String[] args) {
        StringBuilder sb = new StringBuilder();
        new ParseTree("F",
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
        System.out.println(sb.toString());
    }
}
