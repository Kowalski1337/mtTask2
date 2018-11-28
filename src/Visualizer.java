import java.io.PrintWriter;

public class Visualizer {
    private int counter;
    private PrintWriter writer;

    Visualizer(PrintWriter writer) {
        this.writer = writer;
    }

    public void visualize(ParseTree tree) {
        counter = 1;
        writer.println("digraph {");
        rec(tree);
        writer.print("}");
    }

    private int rec(ParseTree tree) {
        writer.print(counter);
        writer.println("[label = \"" + tree.getName() + "\"]");
        int temp = counter;
        counter++;
        for (ParseTree child : tree.getChildren()){
            int childNum = rec(child);
            writer.print(temp);
            writer.print(" -> ");
            writer.println(childNum);
        }
        return temp;
    }
}
