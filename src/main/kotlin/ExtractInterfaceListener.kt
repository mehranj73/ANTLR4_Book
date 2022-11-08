import JavaParser.*
import org.antlr.v4.runtime.TokenStream

class ExtractInterfaceListener(parser: JavaParser) : JavaBaseListener() {
    var parser: JavaParser

    init {
        this.parser = parser
    }

    /** Listen to matches of classDeclaration  */
    override fun enterClassDeclaration(ctx: ClassDeclarationContext) {
        System.out.println("interface I" + ctx.Identifier() + " {")
    }

    override fun exitClassDeclaration(ctx: ClassDeclarationContext?) {
        println("}")
    }

    /** Listen to matches of methodDeclaration  */
    override fun enterMethodDeclaration(
        ctx: MethodDeclarationContext
    ) {
        // need parser to get tokens
        val tokens: TokenStream = parser.tokenStream
        var type = "void"
        if (ctx.type() != null) {
            type = tokens.getText(ctx.type())
        }
        val args: String = tokens.getText(ctx.formalParameters())
        println("\t" + type + " " + ctx.Identifier() + args + ";")
    }
}