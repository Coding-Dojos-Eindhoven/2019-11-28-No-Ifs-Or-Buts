import tables

type
  ShipType* = enum destroyer
  Ship* = seq[bool]

const ships* = {destroyer: @[false, false]}.toTable

proc isHit*(ship: Ship, position: int): bool =
  ship[position]
