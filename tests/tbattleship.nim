import unittest
import tables
import battleship

suite "setup":
  test "it has a destroyer":
    check ships.hasKey(destroyer)

  test "a destroyer has two empty positions":
    let ship = ships[destroyer]
    check ship.len == 2
    check not isHit(ship, 0)
    check not isHit(ship, 1)
