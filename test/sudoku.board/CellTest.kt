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

    @Test
    fun calculateBox1(){
        for (row in 1..3){
            for (column in 1..3) {
                assertEquals(1,Cell.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox2(){
        for (row in 1..3){
            for (column in 4..6) {
                assertEquals(2,Cell.calculateBox(column,row))
            }
        }
    }
    @Test
    fun calculateBox3(){
        for (row in 1..3){
            for (column in 7..9) {
                assertEquals(3,Cell.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox4(){
        for (row in 4..6){
            for (column in 1..3) {
                assertEquals(4,Cell.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox5(){
        for (row in 4..6){
            for (column in 4..6) {
                assertEquals(5,Cell.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox6(){
        for (row in 4..6){
            for (column in 7..9) {
                assertEquals(6,Cell.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox7(){
        for (row in 7..9){
            for (column in 1..3) {
                assertEquals(7,Cell.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox8(){
        for (row in 7..9){
            for (column in 4..6) {
                assertEquals(8,Cell.calculateBox(column,row))
            }
        }
    }

    @Test
    fun calculateBox9(){
        for (row in 7..9){
            for (column in 7..9) {
                assertEquals(9,Cell.calculateBox(column,row))
            }
        }
    }

}