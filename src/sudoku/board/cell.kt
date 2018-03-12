package sudoku.board

data class Cell (val value: Int) {

    fun getValue():String{
        return if (value != 0)
            value.toString()
        else
            "."
    }
}