import unittest
import tables
import battleship

suite "setup":
  test "it has a destroyer":
    check ships.hasKey(destroyer)

  test "a destroyer has two empty positions":
    let ship = ships[destroyer]
    check ship.len == 2
    check ship[0] == false
    check ship[1] == false
