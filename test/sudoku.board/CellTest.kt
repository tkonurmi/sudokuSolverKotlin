package sudoku.board

import org.junit.Assert.assertEquals
import org.junit.Test

class CellTest {
    @Test
    fun printCell() {
        val cell_1_1 = Cell(false,3,1,1,1)
        val cell_1_2 = Cell(false,3,2,1,1)
        val cell_1_3 = Cell(false,3,3,1,1)
        val cell_1_4 = Cell(false,8,4,1,2)
        val cell_1_5 = Cell(false,5,5,1,2)
        val cell_1_6 = Cell(false,2,6,1,2)
        cell_1_1.printCell()
        cell_1_2.printCell()
        cell_1_3.printCell()
        cell_1_4.printCell()
        cell_1_5.printCell()
        cell_1_6.printCell()
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