package sudoku.board

class Board {
    private var cells = mutableListOf<Cell>()

    init {
        for (row in 1..9){
            for (column in 1..9){
                cells.add(Cell(0,row,column,(1..9).toMutableSet()))
            }
        }
    }

    fun getCell(column: Int, row: Int): Cell{
        return cells.first { it.row == row && it.column == column }
    }

    fun calculateBox(column: Int, row: Int): Int {
        val rowSum = ((row-1)/3)*3
        val colSum = ((column-1)/3)+1

        return (rowSum+colSum)
    }

    fun setCellValue(column: Int, row: Int, value: Int) {
        if (value in 0..9) {
            val cell = Cell(value,row,column,calculateCandidates(column, row))
            cells.removeAll(cells.filter { it.row == row && it.column == column })
            cells.add(cell)
            for (peer in getPeers(row,column)){
                cell.candidates = calculateCandidates(cell.column,cell.row)
            }
        }
    }

    fun checkRowState(row: Int): Boolean{
        return checkState(getRowValues(row).map { it.value })
    }

    private fun getRowValues(row: Int): List<Cell> {
        val listOfValues: MutableList<Cell> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(it, row) }
        return listOfValues
    }

    fun checkColumnState(column: Int): Boolean{
        return checkState(getColumnValues(column).map { it.value })
    }

    private fun getColumnValues(column: Int): List<Cell> {
        val listOfValues: MutableList<Cell> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(column, it) }
        return listOfValues
    }

    fun checkBoxState(box: Int): Boolean{
        return checkState(getBoxValues(box).map { it.value })
    }

    private fun getBoxValues(box: Int): List<Cell> {
        val listOfValues: MutableList<Cell> = mutableListOf()
        for (row in 1..9) {
            for (column in 1..9) {
                if (calculateBox(column, row) == box) {
                    listOfValues.add(getCell(column, row))
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

    fun calculateCandidates(column: Int, row: Int): MutableSet<Int>{
        if (getCell(column, row).value > 0)
            return mutableSetOf(getCell(column, row).value)

        val candidates = (1..9).toMutableSet()
        candidates.removeAll(getBoxValues(calculateBox(column, row)).map { it.value }.toSet())
        candidates.removeAll(getRowValues(row).map { it.value }.toSet())
        candidates.removeAll(getColumnValues(column).map { it.value }.toSet())

        return candidates
    }

    private fun getPeers(row: Int, column: Int): MutableList<Cell>{
        val peers = cells.filter { it.row == row }.toMutableList()
        peers.addAll(cells.filter { it.column == column }.toList())
        peers.addAll(cells.filter { calculateBox(it.column,it.row) == calculateBox(column,row) }.toList())
        return peers
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
        // (Remember all cell numbers set after this point)


        return ""
    }
}