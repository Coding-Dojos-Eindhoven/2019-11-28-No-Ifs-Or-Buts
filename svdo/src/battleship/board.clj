(ns battleship.board)

(def empty-board {})

(defn place
  ([board ship coordinates]
   (assoc board ship coordinates))
  ([board ships]
   (reduce (fn [board [ship-type coordinates]] (place board ship-type coordinates)) board ships)))
