import tables

type
  ShipType* = enum destroyer, submarine, cruiser, battleship, carrier
  Ship* = seq[bool]

const ships* = {
  destroyer: @[false, false],
  submarine: @[false, false, false],
  cruiser:   @[false, false, false],
  carrier:   @[false, false, false, false, false]
}.toTable

proc isHit*(ship: Ship, position: int): bool =
  ship[position]
