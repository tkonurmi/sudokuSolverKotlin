package sudoku.board

class Board {
    private var cells: Array<Array<Cell>>
    init {
        var row = 0
        cells = Array(9, {
            row += 1
            var column = 0
            Array(9, {
                column +=1
                Cell(0)
            })
        })
    }

    fun getCell(column: Int, row: Int): Cell{
        return cells[row -1][column -1]
    }

    fun calculateBox(column: Int, row: Int): Int {
        val rowSum = ((row-1)/3)*3
        val colSum = ((column-1)/3)+1

        return (rowSum+colSum)
    }

    fun setCellValue(column: Int, row: Int, value: Int) {
        if (value in 1..9) {
            val cell = Cell(value)
            cells[row -1][column -1] = cell
        }
        if (value == 0){
            val cell = Cell(0)
            cells[row -1][column -1] =cell
        }
    }

    fun checkRowState(row: Int): Boolean{
        return checkState(getRowValues(row))
    }

    private fun getRowValues(row: Int): List<Int> {
        val listOfValues: MutableList<Int> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(it, row).value }
        return listOfValues
    }

    fun checkColumnState(column: Int): Boolean{
        return checkState(getColumnValues(column))
    }

    private fun getColumnValues(column: Int): List<Int> {
        val listOfValues: MutableList<Int> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(column, it).value }
        return listOfValues
    }

    fun checkBoxState(box: Int): Boolean{
        return checkState(getBoxValues(box))
    }

    private fun getBoxValues(box: Int): List<Int> {
        val listOfValues: MutableList<Int> = mutableListOf()
        for (row in 1..9) {
            for (column in 1..9) {
                if (calculateBox(column, row) == box) {
                    listOfValues.add(getCell(column, row).value)
                }
            }
        }
        return listOfValues
    }

    fun checkState(values: List<Int>): Boolean{
        val uniqueValues : MutableSet<Int> = mutableSetOf()
        uniqueValues.addAll(values)

        return  (uniqueValues.size == values.size)
    }

    fun read(input: String){

        for (row in 1..9) {
            for (column in 1..9) {
                val currentChar = input[((row-1)*9+column)-1]
                if (currentChar.isDigit())
                    setCellValue(column,row,currentChar.toString().toInt())
                else
                    setCellValue(column,row,0)
            }
        }
    }

    fun printout():String{
        var result = ""
        for (row in 1..9 )
            for (column in 1..9)
                result += getCell(column, row).value
        return result
    }

    fun calculateCandidates(column: Int, row: Int): Set<Int>{
        if (getCell(column, row).value > 0)
            return setOf(getCell(column, row).value)

        val candidates = (1..9).toMutableSet()
        candidates.removeAll(getBoxValues(calculateBox(column, row)).toSet())
        candidates.removeAll(getRowValues(row).toSet())
        candidates.removeAll(getColumnValues(column).toSet())

        return candidates
    }

    fun solve():String{
        // TODO Loop until nothing changed
        // TODO Naked Singles
        // Calculate all candidates and set if cell has only one candidate

        // TODO Hidden singles
        // Calculate every row/column/box what number is missing and
        // if only one cell could deliver that set it
        // End loop

        // TODO Guess one cell and play it through
        // (Remember all cell nubers set after this point)


        return ""
    }
}