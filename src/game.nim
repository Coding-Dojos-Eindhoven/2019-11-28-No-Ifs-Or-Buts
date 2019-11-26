import tables
import sequtils

type
  Coordinate = (int, int)
  ShipType* = enum destroyer, submarine, cruiser, battleship, carrier
  ShipHole = tuple[coordinate: Coordinate, hit: bool]
  Ship* = seq[ShipHole]
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

proc updateHit(hole: ShipHole, drop: Coordinate): ShipHole =
  (hole.coordinate, hole.hit or hole.coordinate == drop)

proc drop*(ships: Ships, row, column: int): Ships =
  result = ships
  for shipType in ships.keys:
    result[shipType].applyIt(updateHit(it, (row, column)))

proc allHaveSunk*(ships: Ships): bool =
  result = true
  for ship in ships.values:
    result = result and ship.allIt(it.hit)
