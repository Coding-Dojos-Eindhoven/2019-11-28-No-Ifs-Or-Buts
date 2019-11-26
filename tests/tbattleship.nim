import unittest
import battleship

suite "board":

  var board: Board

  setup:
    board = newBoard()

  test "it is empty initially":
    for row in board:
      for cell in row:
        check cell == empty

  test "it has 10 rows":
    check board.len == 10

  test "each row has 10 cells":
    for row in board:
      check row.len == 10
