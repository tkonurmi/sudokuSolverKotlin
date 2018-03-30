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

    fun setCellValue(column: Int, row: Int, value: Int) {
        if (value in 0..9) {
            val cell = Cell(value,row,column, mutableSetOf<Int>())
            cells.removeAll(cells.filter { it.row == row && it.column == column })
            cells.add(cell)
            for (peer in getPeers(row,column)){
                peer.candidates = calculateCandidates(peer.column,peer.row)
            }
        }
    }

    private fun setNewCellValue(cell: Cell, value: Int){
        setCellValue(cell.column,cell.row,value)
    }

    fun checkRowState(row: Int): Boolean{
        return checkState(getRowValues(row).map { it.value })
    }

    fun getRowValues(row: Int): List<Cell> {
        val listOfValues: MutableList<Cell> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(it, row) }
        return listOfValues
    }

    fun checkColumnState(column: Int): Boolean{
        return checkState(getColumnValues(column).map { it.value })
    }

    fun getColumnValues(column: Int): List<Cell> {
        val listOfValues: MutableList<Cell> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(column, it) }
        return listOfValues
    }

    fun checkBoxState(box: Int): Boolean{
        return checkState(getBoxValues(box).map { it.value })
    }

    fun getBoxValues(box: Int): List<Cell> {
        return cells.filter { it.box == box }.toList()
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

    fun printCells(){
        println(cells.map { ""+it.row+":"+it.column })
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
            return mutableSetOf<Int>()

        val candidates = (1..9).toMutableSet()
        candidates.removeAll(getBoxValues(getCell(column, row).box).map { it.value }.toSet())
        candidates.removeAll(getRowValues(row).map { it.value }.toSet())
        candidates.removeAll(getColumnValues(column).map { it.value }.toSet())

        return candidates
    }

    fun getPeers(row: Int, column: Int): MutableSet<Cell>{
        val peers = cells.filter { it.row == row }.toMutableSet()
        peers.addAll(cells.filter { it.column == column }.toList())
        peers.addAll(cells.filter { getCell(it.column,it.row).box == getCell(column,row).box }.toList())
        peers.remove(getCell(column, row))
        return peers
    }

    fun tryNakedSingles(): Boolean{
        var candidateList = cells.filter { it.candidates.size == 1}
        if (candidateList.isEmpty())
            return false
        else {
            val cell = candidateList.first()
            if (cell != null) {
                setNewCellValue(cell, cell.candidates.first())
                return true
            }
        }
        return false
    }

    private fun tryHiddenSingles(): Boolean {
        return false
    }

    fun solve():String {
        var changed = true

        while (changed){
            println(printout())
            changed = tryNakedSingles()
            if (changed)
                continue
            changed = tryHiddenSingles()
            if (changed)
                continue
        }
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