import unittest
import tables
import game

suite "setup":
  test "a ship can be placed":
    let placed = unplacedShips.place(destroyer, @[(3,1), (4,1)])
    check placed[destroyer].coordinates == @[(3,1), (4,1)]

  test "a destroyer has two empty positions":
    let ship = unplacedShips[destroyer]
    check not isHit(ship, 0)
    check not isHit(ship, 1)

  test "there are five ships of correct length":
    check unplacedShips[destroyer].len == 2
    check unplacedShips[submarine].len == 3
    check unplacedShips[cruiser].len == 3
    check unplacedShips[battleship].len == 4
    check unplacedShips[carrier].len == 5

