type
  Cell* = enum
    empty
  Board* = array[10, array[10, Cell]]

proc newBoard*: Board =
  discard
