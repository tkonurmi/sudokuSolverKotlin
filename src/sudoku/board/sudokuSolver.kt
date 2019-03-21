package sudoku.board

class SudokuSolver {
    private var board = Board()

    fun readPuzzleIn(input: String){

        for (row in 1..9) {
            for (column in 1..9) {
                val currentChar = input[((row-1)*9+column)-1]
                if (currentChar.isDigit())
                    board.setCellValue(column,row,currentChar.toString().toInt())
                else
                    board.setCellValue(column,row,0)
            }
        }
    }

    fun board():Board {
        return board
    }

    fun solvePuzzle():String {
        val original = board.returnCurrentValues()
        while (true) {
            board = solveClearCells(board)
            if (board.isSolved())
                return board.returnCurrentValues()

            if (!board.guessOne()){
                readPuzzleIn(original)
            }
        }
    }

    private fun solveClearCells(board: Board): Board {
        var changed = true
        while (changed) {
            changed = board.tryNakedSingles()
            if (changed)
                continue
            changed = board.tryHiddenSingles()
            if (changed)
                continue
        }
        return board
    }
}