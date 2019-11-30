(ns battleship.board)

(def empty-board {})

(defn- place-single-ship
  [board [ship-type coordinates]]
  (assoc board ship-type coordinates))

;; Technique used: [collection-instead-of-element]
(defn place
  ([board ships]
   (reduce place-single-ship board ships)))
