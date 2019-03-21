package sudoku.board

import org.junit.Assert.*
import org.junit.Test
import java.io.File

class BoardTest {
    private var board = Board()
    private var sudokuSolver = SudokuSolver()

    @Test
    fun getCellReturnsEmptyValue() {
        assertEquals(".",board.getCell(1, 1).getValue())
    }

    @Test
    fun setCellValue() {
        board.setCellValue(1,2,5)
        assertEquals("5",board.getCell(2, 1).getValue())
    }

    @Test
    fun setCellValueNegative() {
        val oldValue = board.getCell(2, 1).getValue()
        board.setCellValue(1,2,-1)
        assertEquals(oldValue,board.getCell(2, 1).getValue())
    }

    @Test
    fun setCellEmpty() {
        board.setCellValue(1,2,0)
        assertEquals(".",board.getCell(2, 1).getValue())
    }

    @Test
    fun testCheckRowStateFalse(){
        for (column in 1..9){
            board.setCellValue(column, 1, 10-column)
        }
        board.setCellValue(4, 1, 5)
        assertFalse(board.checkRowState(1))
    }

    @Test
    fun testCheckRowStateTrue(){
        for (column in 1..9){
            board.setCellValue(column, 1, 10-column)
        }
        assert(board.checkRowState(1))
    }

    @Test
    fun testCheckColumnStateFalse(){
        for (row in 1..9){
            board.setCellValue(1,row, 10-row)
        }

        board.setCellValue(1,4, 5)
        assertFalse(board.checkColumnState(1))
    }

    @Test
    fun testCheckColumnStateTrue() {
        for (row in 1..9){
            board.setCellValue(1,row, 10-row)
        }

        assert(board.checkColumnState(1))
    }

    @Test
    fun testCheckBoxStateFalse(){
        for (row in 1..3){
            for (column in 1..3) {
                board.setCellValue(column, row, column + row)
            }
        }

        assertFalse(board.checkBoxState(1))
    }

    @Test
    fun testCheckBoxStateTrue() {
        var value = 1
        for (row in 1..3){
            for (column in 1..3) {
                board.setCellValue(column, row, value++)
            }
        }
        assert(board.checkBoxState(1))
    }

    @Test
    fun testCheckStateTrue() {
        val values: List<Int> = listOf(1,2,3,4,5,6,7,8,9)
        assertTrue(board.checkState(values))
    }

    @Test
    fun testCheckStateFalse() {
        val values: List<Int> = listOf(1,2,3,5,5,6,7,8,9)
        assertFalse(board.checkState(values))
    }

    @Test
    fun readFromString(){
        val input  = "2.6.....7.......3.5..7...92.9.58.34....4...5...4.93.......3.......6..57.6....5.89"
        val result = input.replace('.','0')
        sudokuSolver.readPuzzleIn(input)
        assertEquals(result,sudokuSolver.board().returnCurrentValues())
    }

    @Test
    fun testGetColumnValues(){
        val input  = "2.6.....7.......3.5..7...92.9.58.34....4...5...4.93.......3.......6..57.6....5.89"
        sudokuSolver.readPuzzleIn(input)
        for (column in 1..9)
            assertEquals(sudokuSolver.board().getColumnCells(column).size,9)
    }

    @Test
    fun testGetBoxValues(){
        val input  = "2.6.....7.......3.5..7...92.9.58.34....4...5...4.93.......3.......6..57.6....5.89"
        sudokuSolver.readPuzzleIn(input)
        for (box in 1..9)
            assertEquals(sudokuSolver.board().getBoxCells(box).size,9)
    }

    @Test
    fun testGetRowValues(){
        val input  = "2.6.....7.......3.5..7...92.9.58.34....4...5...4.93.......3.......6..57.6....5.89"
        sudokuSolver.readPuzzleIn(input)
        for (row in 1..9)
            assertEquals(sudokuSolver.board().getRowCells(row).size,9)
    }

    @Test
    fun testGetPeers() {
        val input  = "010003008000500903000029000080000609070156030406000070000270000302001000600300090"
        sudokuSolver.readPuzzleIn(input)
        board = sudokuSolver.board()
        for (row in 1..9) {
            for (column in 1..9) {
                assert(board.getPeers(row, column).size == 20)
                assert(board.getPeers(row,column).containsAll(board.getRowCells(row).filter { it.column != column }))
                assert(!board.getPeers(row,column).contains(board.getCell(row, column)))
                assert(board.getPeers(row,column).containsAll(board.getColumnCells(column).filter { it.row != row }))
                assert(!board.getPeers(row,column).contains(board.getCell(row, column)))
                assert(board.getPeers(row,column).containsAll(board.getBoxCells(board.getCell(row, column).box).filter { it.column != column || it.row != row}))
                assert(!board.getPeers(row, column).contains(board.getCell(row, column)))
            }
        }
    }

    @Test
    fun testNakedSingles() {
        val input  = "010003008000500903000029000080000609070156030406000070000270000302001000600300090"
        sudokuSolver.readPuzzleIn(input)
        assert(sudokuSolver.board().tryNakedSingles())
    }

    @Test
    fun testNakedSinglesFalse() {
        val input  = "010003008000500903000029000080000609279156834406000070000270000302001000600300090"
        sudokuSolver.readPuzzleIn(input)
        assert(!sudokuSolver.board().tryNakedSingles())
    }

    private fun solveOneSudoku(input:String, output:String):Boolean{
        sudokuSolver.readPuzzleIn(input)
        if (output == sudokuSolver.solvePuzzle())
            return true

        println("Input:  $input")
        println("Solved: "+sudokuSolver.board().returnCurrentValues())
        println("Output: $output")
        println()
        return false
    }

    data class SudokuTest(val input: String, val output: String)

    @Test
    fun testSolveSudokuWithMultipleInputs(){
        val sudokuTests = mutableListOf<SudokuTest>()
        File("sudoku_puzzles.txt").readLines().map { line: String -> sudokuTests.add(parseSudokuFromFile(line)) }

        var solveCount=0
        var failCount=0
        for (sudokuTest in sudokuTests){
            if (solveOneSudoku(sudokuTest.input,sudokuTest.output))
                solveCount++
            else
                failCount++
        }
        println("Solved $solveCount out of " + (solveCount+failCount) + " (" + (100*(solveCount)/(solveCount+failCount))+"%)")


    }

    private fun parseSudokuFromFile(line: String) = SudokuTest(line.split(",").first(), line.split(",").last())

    @Test
    fun calculateCandidatesIfCellSolved(){
        val input  =
                        "2.6.....7" +
                        ".......3." +
                        "5..7...92" +
                        ".9.58.34." +
                        "...4...5." +
                        "..4.93..." +
                        "....3...." +
                        "...6..57." +
                        "6....5.89"

        sudokuSolver.readPuzzleIn(input)
        board = sudokuSolver.board()
        assertEquals(mutableSetOf<Int>(),board.calculateCandidates(1,1))
        assertEquals(setOf(1,3,4,8),board.calculateCandidates(2,1))
        assertEquals(mutableSetOf<Int>(),board.calculateCandidates(3,1))
        assertEquals(setOf(1,4,6,8),board.calculateCandidates(7,3))
        assertEquals(setOf(1,2,6),board.calculateCandidates(8,7))
        assertEquals(setOf(1,2,4,8,9),board.calculateCandidates(6,8))
    }

    @Test
    fun solvesudoku(){
        val  input = "080001090030000248500300010650000020010020000400700509000000000000604800001039000"
        //val  input = "..27...8..5..3......6..59...7..9.2545....8..3....7.........64..43......8..5.2...."
        //val  input = "206000007000000030500700092090580340000400050004093000000030000000600570600005089"
        sudokuSolver.readPuzzleIn(input)
        println(sudokuSolver.board().returnCurrentValues())
        sudokuSolver.solvePuzzle()
        println(sudokuSolver.board().returnCurrentValues())
    }
}