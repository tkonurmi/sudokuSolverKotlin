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

    fun getCell(row: Int, column: Int): Cell{
        return cells.first { it.row == row && it.column == column }
    }

    fun setCellValue(column: Int, row: Int, value: Int) {
        if (value in 0..9) {
            val cell = Cell(value,row,column, mutableSetOf())
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
        return checkState(getRowCells(row).map { it.value })
    }

    fun getRowCells(row: Int): List<Cell> {
        val listOfValues: MutableList<Cell> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(row, it) }
        return listOfValues
    }

    fun checkColumnState(column: Int): Boolean{
        return checkState(getColumnCells(column).map { it.value })
    }

    fun getColumnCells(column: Int): List<Cell> {
        val listOfValues: MutableList<Cell> = mutableListOf()
        (1..9).mapTo(listOfValues) { getCell(it, column) }
        return listOfValues
    }

    fun checkBoxState(box: Int): Boolean{
        return checkState(getBoxCells(box).map { it.value })
    }

    fun getBoxCells(box: Int): List<Cell> {
        return cells.filter { it.box == box }.toList()
    }

    fun checkState(values: List<Int>): Boolean{
        val uniqueValues : MutableSet<Int> = mutableSetOf()
        uniqueValues.addAll(values)

        return  (uniqueValues.size == values.size)
    }

    fun readPuzzleIn(input: String){

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

    fun returnCurrentValues():String{
        var result = ""
        for (row in 1..9 )
            for (column in 1..9)
                result += getCell(row, column).value
        return result
    }

    fun calculateCandidates(column: Int, row: Int): MutableSet<Int>{
        if (getCell(row, column).value > 0)
            return mutableSetOf()

        val candidates = (1..9).toMutableSet()
        candidates.removeAll(getBoxCells(getCell(row, column).box).map { it.value }.toSet())
        candidates.removeAll(getRowCells(row).map { it.value }.toSet())
        candidates.removeAll(getColumnCells(column).map { it.value }.toSet())

        return candidates
    }

    fun getPeers(row: Int, column: Int): MutableSet<Cell>{
        val peers = cells.filter { it.row == row }.toMutableSet()
        peers.addAll(cells.filter { it.column == column }.toList())
        peers.addAll(cells.filter { getCell(it.row, it.column).box == getCell(row, column).box }.toList())
        peers.remove(getCell(row, column))
        return peers
    }

    fun tryNakedSingles(): Boolean{
        val candidateList = cells.filter { it.candidates.size == 1}
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
        for (value in 1..9) {
            if (checkCellsForHiddenSingles(getRowCells(value), value)) return true
            if (checkCellsForHiddenSingles(getColumnCells(value), value)) return true
            if (checkCellsForHiddenSingles(getBoxCells(value), value)) return true
        }
        return false
    }

    private fun checkCellsForHiddenSingles(checkedCells: List<Cell>, row: Int): Boolean {
        val hiddenSingle = checkedCells
                .map { it.candidates }
                .flatten()
                .groupBy { it }
                .filter { it.value.size == 1 }.values.flatten()
        if (hiddenSingle.isNotEmpty()) {
            val cell = checkedCells.find { it.candidates.contains(hiddenSingle.first()) }
            if (cell != null) {
                setNewCellValue(cell, hiddenSingle.first())
                return true
            }
        }
        return false
    }

    fun solvePuzzle():String {
        var changed = true

        while (changed){
            changed = tryNakedSingles()
            if (changed)
                continue
            changed = tryHiddenSingles()
            if (changed)
                continue
        }

        // TODO Guess one cell and play it through
        // (Remember all cell numbers set after this point)
        return returnCurrentValues()
    }
}