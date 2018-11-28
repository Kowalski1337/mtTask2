import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) {

        try (PrintWriter writer = new PrintWriter(new FileWriter("tree1.dot"))){
            new Visualizer(writer).visualize(new Parser(Files.newInputStream(Paths.get("src/tests/test1.in"))).parse());
        } catch (IOException e){
            System.err.println("Problems was occurred while opening inputStream");
        } catch (ParseException e){
            System.err.println("Lexer or parse error\n" + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("tree2.dot"))){
            new Visualizer(writer).visualize(new Parser(Files.newInputStream(Paths.get("src/tests/test2.in"))).parse());
        } catch (IOException e){
            System.err.println("Problems was occurred while opening inputStream");
        } catch (ParseException e){
            System.err.println("Lexer or parse error\n" + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("tree3.dot"))){
            new Visualizer(writer).visualize(new Parser(Files.newInputStream(Paths.get("src/tests/test3.in"))).parse());
        } catch (IOException e){
            System.err.println("Problems was occurred while opening inputStream");
        } catch (ParseException e){
            System.err.println("Lexer or parse error\n" + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("tree4.dot"))){
            new Visualizer(writer).visualize(new Parser(Files.newInputStream(Paths.get("src/tests/test4.in"))).parse());
        } catch (IOException e){
            System.err.println("Problems was occurred while opening inputStream");
        } catch (ParseException e){
            System.err.println("Lexer or parse error\n" + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("inCorTree1.dot"))){
            new Visualizer(writer).visualize(new Parser(Files.newInputStream(Paths.get("src/tests/unCorTest1.in"))).parse());
        } catch (IOException e){
            System.err.println("Problems was occurred while opening inputStream");
        } catch (ParseException e){
            System.err.println("Lexer or parse error\n" + e.getMessage());
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter("inCorTree2.dot"))){
            new Visualizer(writer).visualize(new Parser(Files.newInputStream(Paths.get("src/tests/unCorTest2.in"))).parse());
        } catch (IOException e){
            System.err.println("Problems was occurred while opening inputStream");
        } catch (ParseException e){
            System.err.println("Lexer or parse error\n" + e.getMessage());
        }
    }
}
