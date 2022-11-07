import LabeledExprParser.*

class EvalVisitor : LabeledExprBaseVisitor<Int?>() {
    /** "memory" for our calculator; variable/value pairs go here  */
    var memory: MutableMap<String, Int> = HashMap()

    /** ID '=' expr NEWLINE  */
    override fun visitAssign(ctx: AssignContext): Int {
        val id = ctx.ID().text // id is left-hand side of '='
        val value = visit(ctx.expr())!! // compute value of expression on right
        memory[id] = value // store it in our memory
        return value
    }

    /** expr NEWLINE  */
    override fun visitPrintExpr(ctx: PrintExprContext): Int {
        val value = visit(ctx.expr())!! // evaluate the expr child
        println(value) // print the result
        return 0 // return dummy value
    }

    /** INT  */
    override fun visitInt(ctx: IntContext): Int {
        return Integer.valueOf(ctx.INT().text)
    }

    /** ID  */
    override fun visitId(ctx: IdContext): Int {
        val id = ctx.ID().text
        return if (memory.containsKey(id)) memory[id]!! else 0
    }

    /** expr op=('*'|'/') expr  */
    override fun visitMulDiv(ctx: MulDivContext): Int {
        val left = visit(ctx.expr(0))!! // get value of left subexpression
        val right = visit(ctx.expr(1))!! // get value of right subexpression
        return if (ctx.op.type == MUL) left * right else left / right
        // must be DIV
    }

    /** expr op=('+'|'-') expr  */
    override fun visitAddSub(ctx: AddSubContext): Int {
        val left = visit(ctx.expr(0))!! // get value of left subexpression
        val right = visit(ctx.expr(1))!! // get value of right subexpression
        return if (ctx.op.type == ADD) left + right else left - right
        // must be SUB
    }

    /** '(' expr ')'  */
    override fun visitParens(ctx: ParensContext): Int {
        return visit(ctx.expr())!! // return child expr's value
    }
}