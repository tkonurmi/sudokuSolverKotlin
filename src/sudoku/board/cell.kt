package sudoku.board

data class Cell (val value: Int, val row: Int, val column: Int, var candidates : MutableSet<Int>) {

    val box: Int by lazy { calculateBox(column, row) }

    fun getValue():String{
        return if (value != 0)
            value.toString()
        else
            "."
    }

    companion object {
        fun calculateBox(column: Int, row: Int): Int {
            val rowSum = ((row-1)/3)*3
            val colSum = ((column-1)/3)+1

            return (rowSum+colSum)
        }
    }
}