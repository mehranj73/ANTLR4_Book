class ShortToUnicodeString : ArrayInitBaseListener() {
    /** Translate { to "  */
    override fun enterInit(ctx: ArrayInitParser.InitContext) {
        print('"')
    }

    /** Translate } to "  */
    override fun exitInit(ctx: ArrayInitParser.InitContext) {
        print('"')
    }

    /** Translate integers to 4-digit hexadecimal strings prefixed with \\u  */
    override fun enterValue(ctx: ArrayInitParser.ValueContext) {
        // Assumes no nested array initializers
        val value = Integer.valueOf(ctx.INT().text)
        System.out.printf("\\u%04x", value)
    }
}