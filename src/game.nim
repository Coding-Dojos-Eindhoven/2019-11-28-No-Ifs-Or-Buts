import tables
import sequtils

type
  Coordinate = (int, int)
  ShipType* = enum destroyer, submarine, cruiser, battleship, carrier
  Ship* = seq[(Coordinate, bool)]
  Ships* = Table[ShipType, Ship]

const unplaced = ((0, 0), false)
const unplacedShips*: Ships = {
  destroyer:  @[unplaced, unplaced],
  submarine:  @[unplaced, unplaced, unplaced],
  cruiser:    @[unplaced, unplaced, unplaced],
  battleship: @[unplaced, unplaced, unplaced, unplaced],
  carrier:    @[unplaced, unplaced, unplaced, unplaced, unplaced]
}.toTable

proc isHit*(ship: Ship, position: int): bool =
  ship[position][1]

proc place*(ships: Ships, ship: ShipType, coordinates: seq[Coordinate]): Ships =
  result = ships
  result[ship] = coordinates.mapIt((it, false))

proc coordinates*(ship: Ship): seq[Coordinate] =
  ship.mapIt(it[0])

proc drop*(ships: Ships, row, column: int): bool =
  for ship in low(ShipType)..high(ShipType):
    for coordinate in ships[ship].coordinates:
      let hit = coordinate == (row, column)
      result = result or hit
