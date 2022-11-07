
import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree
import org.antlr.v4.runtime.tree.ParseTreeWalker


fun main(args: Array<String>) {

    val text = "{4, 5}"
    val input = CharStreams.fromString(text)

    val lexer = ArrayInitLexer(input)

    val tokens = CommonTokenStream(lexer)

    val parser = ArrayInitParser(tokens)
    val tree: ParseTree = parser.init() // begin parsing at init rule
    val walker = ParseTreeWalker()

    walker.walk(ShortToUnicodeString(), tree)
    println() // print a \n after translation


}