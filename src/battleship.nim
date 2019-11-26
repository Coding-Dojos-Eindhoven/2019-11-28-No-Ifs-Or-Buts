import tables

type Ship* = enum destroyer

const ships* = {destroyer: [false, false]}.toTable
