package sudoku.board

import org.junit.Assert.*
import org.junit.Test

class BoardTest {
    private var board = Board()

    @Test
    fun getCellReturnsEmptyValue() {
        assertEquals(".",board.getCell(1,1).getValue())
    }

    @Test
    fun setCellValue() {
        board.setCellValue(1,2,5)
        assertEquals("5",board.getCell(1,2).getValue())
    }

    @Test
    fun setCellValueNegative() {
        val oldValue = board.getCell(1,2).getValue()
        board.setCellValue(1,2,-1)
        assertEquals(oldValue,board.getCell(1,2).getValue())
    }

    @Test
    fun setCellEmpty() {
        board.setCellValue(1,2,0)
        assertEquals(".",board.getCell(1,2).getValue())
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
    fun calculateBox1(){
        for (row in 1..3){
            for (column in 1..3) {
                assertEquals(1,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox2(){
        for (row in 1..3){
            for (column in 4..6) {
                assertEquals(2,board.calculateBox(column,row))
            }
        }
    }
    @Test
    fun calculateBox3(){
        for (row in 1..3){
            for (column in 7..9) {
                assertEquals(3,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox4(){
        for (row in 4..6){
            for (column in 1..3) {
                assertEquals(4,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox5(){
        for (row in 4..6){
            for (column in 4..6) {
                assertEquals(5,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox6(){
        for (row in 4..6){
            for (column in 7..9) {
                assertEquals(6,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox7(){
        for (row in 7..9){
            for (column in 1..3) {
                assertEquals(7,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox8(){
        for (row in 7..9){
            for (column in 4..6) {
                assertEquals(8,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox9(){
        for (row in 7..9){
            for (column in 7..9) {
                assertEquals(9,board.calculateBox(column,row))
            }
        }
    }

    @Test
    fun readFromString(){
        val input  = "2.6.....7.......3.5..7...92.9.58.34....4...5...4.93.......3.......6..57.6....5.89"
        val result = input.replace('.','0')
        board.read(input)
        assertEquals(result,board.printout())
    }

    @Test
    fun solveSudoku(){
        val input  = "2.6.....7.......3.5..7...92.9.58.34....4...5...4.93.......3.......6..57.6....5.89"
        val result = "246359817917824635538761492792586341361472958854193726185937264429618573673245189"
        board.read(input)
        //assertEquals(result,board.solve())


        //val input2 = "....42.5945..9...8..............4.8.......7.3...836..23692....17..........5..16.."
        //val input3 = ".........36....92.....1..7.......2.4....365..1...2573..728.93..9......8..4......."
        //val input4 = "6..4...3......1.5.41.8......4.35.2.....7....8..2.6.5..9........73.5...86..4...7.."
        //val input5 = "...3..8.5.6.....3.....5.267..61....2..34.57....4..8...278...9.......1.........6.."
        //val input6 = ".4..8.7...2.......8...3.51....2.89.6.....9.2....5.....1.....3...9.4.6..5....5...."


        //val result7 = "429316578867524193513897246931785624682941735745263981354672819178459362296138457"
        //val result8 = "965418732143267958827953614579384126412695387638172495354721869786539241291846573"
        //val result9 = "125897634674513928398426157482659713769231485531784296243975861956148372817362549"
    }

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

        board.read(input)
        assertEquals(setOf(2),board.calculateCandidates(1,1))
        assertEquals(setOf(1,3,4,8),board.calculateCandidates(2,1))
        assertEquals(setOf(6),board.calculateCandidates(3,1))
        assertEquals(setOf(1,4,6,8),board.calculateCandidates(7,3))
        assertEquals(setOf(1,2,6),board.calculateCandidates(8,7))
        assertEquals(setOf(1,2,4,8,9),board.calculateCandidates(6,8))
    }
}