Battleship In Clojure
=====================

Techniques Used
---------------

* **[collection-instead-of-element]** Pass fn input as collection instead of
  element; you can then `filter` the collection and use operations that have no
  effect when the collection is empty. Example: placing a ship on the board with
  invalid coordinates. Instead of placing ships one by one, an array of ships
  can be placed.
