(ns battleship.board)

(def empty-board {})

(defn- place-single-ship
  ([board ship-type coordinates]
   (assoc board ship-type coordinates))
  ([board [ship-type coordinates]]
   (place-single-ship board ship-type coordinates)))

(defn place
  ([board ships]
   (reduce place-single-ship board ships)))
