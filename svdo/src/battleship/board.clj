(ns battleship.board)

(def empty-board {})

(defn place [board ship coordinates]
  (assoc board ship coordinates))
