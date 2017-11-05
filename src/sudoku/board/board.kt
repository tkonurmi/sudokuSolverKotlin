package sudoku.board

class Board {
    private var cells: Array<Array<Cell>>
    init {
        var row = 0
        cells = Array(9, {
            row += 1
            var column = 0
            Array(9, {
                column +=1
                Cell(true, 0, column, row,calculateBox(column,row))
            })
        })
    }

    fun getCell(column: Int, row: Int): Cell{
        return cells[row -1][column -1]
    }

    fun printBoard(){
        var row =1
        while (row<=9){
            printRow(row)
            row +=1
        }
    }

    fun printRow(row: Int){
        var column = 1
        while (column <= 9) {
            cells[row-1][column-1].printCell()
            column +=1
        }
        if (row %3==0)
           println("---------------")
    }

    fun calculateBox(column: Int, row: Int): Int {
        val rowSum = ((row-1)/3)*3
        val colSum = ((column-1)/3)+1

        return (rowSum+colSum)
    }

    fun setCellValue(column: Int, row: Int, value: Int) {
        val box = calculateBox(column, row)
        if (value in 1..9) {
            val cell = Cell(false, value, column, row, box)
            cells[row -1][column -1] = cell
        }
        if (value == 0){
            val cell = Cell(true,0, column, row, box)
            cells[row -1][column -1] =cell
        }
    }

    fun checkRowState(row: Int): Boolean{
        val listOfValues : MutableList<Int> = mutableListOf()
        var column = 1
        while (column <=9){
            listOfValues.add(getCell(column,row).value)
            column++
        }
        return checkState(listOfValues)
    }
    fun checkColumnState(column: Int): Boolean{
        val listOfValues : MutableList<Int> = mutableListOf()
        var row = 1
        while (row <=9){
            listOfValues.add(getCell(column,row).value)
            row++
        }
        return checkState(listOfValues)
    }

    fun checkBoxState(box: Int): Boolean{
        val listOfValues: MutableList<Int> = mutableListOf()
        var row =1
        var column = 1
        while (row <=9){
            while (column <= 9){
                if (calculateBox(column,row) == box) {
                    listOfValues.add(getCell(column, row).value)
                }
                column++
            }
            row++
            column = 1
        }
        println(listOfValues.toString())
        return checkState(listOfValues)
    }

    fun checkState(values: List<Int>): Boolean{
        val uniqueValues : MutableSet<Int> = mutableSetOf()
        uniqueValues.addAll(values)

        return  (uniqueValues.size == values.size)
    }

}