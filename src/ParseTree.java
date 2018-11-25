import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ParseTree {
    private List<ParseTree> children;
    private String name;

    public ParseTree(String name, ParseTree... children) {
        this.name = name;
        this.children = Arrays.asList(children);
    }

    public ParseTree(String name){
        this.name = name;
        children = new ArrayList<>();
    }

    private String generateIndent(int depth) {
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            temp.append('\t');
        }
        return temp.toString();
    }

    public void toString(StringBuilder sb){
        toString(sb, 0);
    }

    private void toString(StringBuilder sb, int depth) {
        sb.append(generateIndent(depth)).append(name);
        children.forEach(ch -> {
            sb.append('\n');
            ch.toString(sb, depth + 1);
        });
    }
}
