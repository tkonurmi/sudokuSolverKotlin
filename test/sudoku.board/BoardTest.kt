package sudoku.board

import org.junit.Assert.*
import org.junit.Test

class BoardTest {
    private var board = Board()

    @Test
    fun testInitializingWholeBoard(){
        var row = 1
        var column = 1
        while (row <= 9){
            while (column <= 9){
                board.setCellValue(column,row, (((row*column)%9)+1))
                column +=1
            }
            row += 1
            column =1
        }
        board.printBoard()
    }

    @Test
    fun getCellReturnsEmptyValue() {
        assertEquals(".",board.getCell(1,1).getValue())
    }

    @Test
    fun setCellValue() {
        board.setCellValue(1,2,5)
        assertEquals("5",board.getCell(1,2).getValue())
        board.printBoard()
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
}