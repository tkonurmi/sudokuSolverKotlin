package sudoku.board

data class Cell (private val empty: Boolean = true, val value: Int, private var column: Int, var row: Int, var box: Int) {

    fun printCell(){
        if (column %3==1)
            print("|")
        if (!empty)
            print(value.toString())
        if (column %3==0)
            print("|")
        if (column == 9)
            println()

    }

    fun getValue():String{
        return if (!empty)
            value.toString()
        else
            "."
    }
}