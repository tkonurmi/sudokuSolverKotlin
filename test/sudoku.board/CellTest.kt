package sudoku.board

import org.junit.Assert.assertEquals
import org.junit.Test

class CellTest {
    @Test
    fun printCell() {
        val cell11 = Cell(false,3,1,1,1)
        val cell12 = Cell(false,3,2,1,1)
        val cell13 = Cell(false,3,3,1,1)
        val cell14 = Cell(false,8,4,1,2)
        val cell15 = Cell(false,5,5,1,2)
        val cell16 = Cell(false,2,6,1,2)
        cell11.printCell()
        cell12.printCell()
        cell13.printCell()
        cell14.printCell()
        cell15.printCell()
        cell16.printCell()
    }

    @Test
    fun testGetValue(){
        val cell = Cell(false, 3, 1,1,1)
        assertEquals("3", cell.getValue())
    }

    @Test
    fun testGetValueEmpty(){
        val cell = Cell(true, 0, 1,1,1)
        assertEquals(".", cell.getValue())
    }

}