import tables
import sequtils

type
  Coordinate = (int, int)
  ShipType* = enum destroyer, submarine, cruiser, battleship, carrier
  Ship* = seq[tuple[coordinate: Coordinate, hit: bool]]
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
  ship[position].hit

proc place*(ships: Ships, ship: ShipType, coordinates: seq[Coordinate]): Ships =
  result = ships
  result[ship] = coordinates.mapIt((it, false))

proc coordinates*(ship: Ship): seq[Coordinate] =
  ship.mapIt(it.coordinate)

proc drop*(ships: Ships, row, column: int): Ships =
  result = ships
  for ship in low(ShipType)..high(ShipType):
    result[ship].applyIt(
      (it.coordinate, it.hit or it.coordinate == (row, column))
    )

proc allHaveSunk*(ships: Ships): bool =
  result = true
  for ship in low(ShipType)..high(ShipType):
    for position in ships[ship]:
      result = result and position.hit
