import unittest
import tables
import game

suite "setup":
  test "a destroyer has two empty positions":
    let ship = ships[destroyer]
    check not isHit(ship, 0)
    check not isHit(ship, 1)

  test "there are five ships of correct length":
    check ships[destroyer].len == 2
    check ships[submarine].len == 3
    check ships[cruiser].len == 3
    check ships[battleship].len == 4
    check ships[carrier].len == 5

