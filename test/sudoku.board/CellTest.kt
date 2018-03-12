package sudoku.board

import org.junit.Assert.assertEquals
import org.junit.Test

class CellTest {

    @Test
    fun testGetValue(){
        val cell = Cell(3)
        assertEquals("3", cell.getValue())
    }

    @Test
    fun testGetValueEmpty(){
        val cell = Cell(0)
        assertEquals(".", cell.getValue())
    }

}