package sudoku.board

import org.junit.Assert.assertEquals
import org.junit.Test

class CellTest {

    @Test
    fun testGetValue(){
        val cell = Cell(3,1,1, (1..9).toMutableSet() )
        assertEquals("3", cell.getValue())
    }

    @Test
    fun testGetValueEmpty(){
        val cell = Cell(0,1,1, (1..9).toMutableSet() )
        assertEquals(".", cell.getValue())
    }

}