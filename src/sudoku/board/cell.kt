package sudoku.board

data class Cell (val empty: Boolean=false, val value: Int?, var options: List<Int>?, var x: Int, var y: Int) {

}