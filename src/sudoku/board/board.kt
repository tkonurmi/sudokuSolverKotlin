package sudoku.board

fun main(args: Array<String>) {
    Board(3).printBoard()


}

class Board(val clues: Int) {
    //var cells = arrayOf(9,arrayOfNulls<Cell>(9))

    var cells: Array<Array<Cell>>
    init {
        var x = 0
        var y = 0
        cells = Array(9, {
            y += 1
            x = 0
            Array(9, {
                x +=1
                Cell(true,null,null, x, y)
            })
        })
    }

    fun printBoard(){
        for (row in cells){
            println("|")
            for (cell in row){
                if (cell.x%3 == 1)
                    print("|")
                if (!cell.empty)
                    print(cell.value.toString())
                else
                    print(".")
            }
        }
        print("|")
    }


}