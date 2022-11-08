import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker
import java.io.FileInputStream


fun main(args: Array<String>) {

    val text = """
     import java.util.List;
import java.util.Map;
public class Demo {
	void f(int x, String y) { }
	int[ ] g(/*no args*/) { return null; }
	List<Map<String, Integer>>[] h() { return null; }
}

    """.trimIndent()
    val input = CharStreams.fromString(text)



    val lexer = JavaLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = JavaParser(tokens)
    val tree: ParseTree = parser.compilationUnit() // parse


    val walker = ParseTreeWalker() // create standard walker

    val extractor = ExtractInterfaceListener(parser)
    walker.walk(extractor, tree) // initiate walk of tree with listener


}

