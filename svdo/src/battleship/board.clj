(ns battleship.board)

(def empty-board {})

(defn- place-single-ship
  [board [ship-type coordinates]]
  (assoc board ship-type coordinates))

(defn place
  ([board ships]
   (reduce place-single-ship board ships)))
