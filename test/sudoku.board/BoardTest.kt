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
        board.setCellValue(1,1,9)
        board.setCellValue(2,1,8)
        board.setCellValue(3,1,7)
        board.setCellValue(4,1,5)
        board.setCellValue(5,1,5)
        board.setCellValue(6,1,4)
        board.setCellValue(7,1,3)
        board.setCellValue(8,1,2)
        board.setCellValue(9,1,1)
        board.printRow(1)
        assert(!board.checkRowState(1))
    }

    @Test
    fun testCheckRowStateTrue(){
        board.setCellValue(1,1,9)
        board.setCellValue(2,1,8)
        board.setCellValue(3,1,7)
        board.setCellValue(4,1,6)
        board.setCellValue(5,1,5)
        board.setCellValue(6,1,4)
        board.setCellValue(7,1,3)
        board.setCellValue(8,1,2)
        board.setCellValue(9,1,1)
        board.printRow(1)
        assert(board.checkRowState(1))
    }

    @Test
    fun testCheckColumnStateFalse(){
        board.setCellValue(1,1,9)
        board.setCellValue(1,2,8)
        board.setCellValue(1,3,7)
        board.setCellValue(1,4,5)
        board.setCellValue(1,5,5)
        board.setCellValue(1,6,4)
        board.setCellValue(1,7,3)
        board.setCellValue(1,8,2)
        board.setCellValue(1,9,1)
        assert(!board.checkColumnState(1))
    }

    @Test
    fun testCheckColumnStateTrue() {
        board.setCellValue(1,1,9)
        board.setCellValue(1,2,8)
        board.setCellValue(1,3,7)
        board.setCellValue(1,4,6)
        board.setCellValue(1,5,5)
        board.setCellValue(1,6,4)
        board.setCellValue(1,7,3)
        board.setCellValue(1,8,2)
        board.setCellValue(1,9,1)

        assert(board.checkColumnState(1))
    }

    @Test
    fun testCheckBoxStateFalse(){
        board.setCellValue(1,1,9)
        board.setCellValue(2,1,8)
        board.setCellValue(3,1,7)
        board.setCellValue(1,2,5)
        board.setCellValue(2,2,5)
        board.setCellValue(3,2,4)
        board.setCellValue(1,3,3)
        board.setCellValue(2,3,2)
        board.setCellValue(3,3,1)
        assertFalse(board.checkBoxState(1))
    }

    @Test
    fun testCheckBoxStateTrue() {
        board.setCellValue(1,1,9)
        board.setCellValue(1,2,8)
        board.setCellValue(1,3,7)
        board.setCellValue(2,1,6)
        board.setCellValue(2,2,5)
        board.setCellValue(2,3,4)
        board.setCellValue(3,1,3)
        board.setCellValue(3,2,2)
        board.setCellValue(3,3,1)
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
    fun calculateBox_1(){
        assertEquals(1,board.calculateBox(1,1))
        assertEquals(1,board.calculateBox(2,1))
        assertEquals(1,board.calculateBox(3,1))
        assertEquals(1,board.calculateBox(1,2))
        assertEquals(1,board.calculateBox(2,2))
        assertEquals(1,board.calculateBox(3,2))
        assertEquals(1,board.calculateBox(1,3))
        assertEquals(1,board.calculateBox(2,3))
        assertEquals(1,board.calculateBox(3,3))
    }

    @Test
    fun calculateBox_2(){
        assertEquals(2,board.calculateBox(4,1))
        assertEquals(2,board.calculateBox(5,1))
        assertEquals(2,board.calculateBox(6,1))
        assertEquals(2,board.calculateBox(4,2))
        assertEquals(2,board.calculateBox(5,2))
        assertEquals(2,board.calculateBox(6,2))
        assertEquals(2,board.calculateBox(4,3))
        assertEquals(2,board.calculateBox(5,3))
        assertEquals(2,board.calculateBox(6,3))
    }
    @Test
    fun calculateBox_3(){
        assertEquals(3,board.calculateBox(7,1))
        assertEquals(3,board.calculateBox(8,1))
        assertEquals(3,board.calculateBox(9,1))
        assertEquals(3,board.calculateBox(7,2))
        assertEquals(3,board.calculateBox(8,2))
        assertEquals(3,board.calculateBox(9,2))
        assertEquals(3,board.calculateBox(7,3))
        assertEquals(3,board.calculateBox(8,3))
        assertEquals(3,board.calculateBox(9,3))
    }

    @Test
    fun calculateBox_4(){
        assertEquals(4,board.calculateBox(1,4))
        assertEquals(4,board.calculateBox(2,4))
        assertEquals(4,board.calculateBox(3,4))
        assertEquals(4,board.calculateBox(1,5))
        assertEquals(4,board.calculateBox(2,5))
        assertEquals(4,board.calculateBox(3,5))
        assertEquals(4,board.calculateBox(1,6))
        assertEquals(4,board.calculateBox(2,6))
        assertEquals(4,board.calculateBox(3,6))
    }
}