package sudoku.board

data class Cell (val value: Int, val row: Int, val column: Int, var candidates : MutableSet<Int>) {

    fun getValue():String{
        return if (value != 0)
            value.toString()
        else
            "."
    }
}