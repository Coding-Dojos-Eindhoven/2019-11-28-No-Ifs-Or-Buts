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

suite "dropping bombs":

  var ships: Ships

  setup:
    ships = unplacedShips
      .place(destroyer, @[(7,1), (7,2)])
      .place(submarine, @[(7,8), (6,8), (5,8)])
      .place(cruiser, @[(1,1), (1,2), (1,3)])
      .place(battleship, @[(3,2), (3,3), (3,4), (3,5)])
      .place(carrier, @[(4,7), (3,7), (2,7), (1,7), (0,7)])

  test "a bomb can hit":
    let updated = ships.drop(7, 1)
    check isHit(updated[destroyer], 0) == true

  test "a bomb can miss":
    let updated = ships.drop(7, 0)
    check isHit(updated[destroyer], 0) == false
