import org.antlr.v4.runtime.CharStreams
import org.antlr.v4.runtime.CommonTokenStream
import org.antlr.v4.runtime.tree.ParseTree



fun main(args: Array<String>) {

    val text = """
        4+5
        x = 2
        y=5
        x+y
        
    """.trimIndent()
    val input = CharStreams.fromString(text)

    val lexer = LabeledExprLexer(input)
    val tokens = CommonTokenStream(lexer)
    val parser = LabeledExprParser(tokens)
    val tree: ParseTree = parser.prog() // parse


    val eval = EvalVisitor()
    eval.visit(tree)


}