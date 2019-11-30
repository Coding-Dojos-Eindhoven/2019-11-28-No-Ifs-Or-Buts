(ns battleship.board)

(def empty-board {})

(defn- place-single-ship
  [board [ship-type coordinates]]
  (assoc board ship-type coordinates))

(defn valid-coordinate? [[_ coordinates]]
  (seq coordinates))

;; Technique used: [collection-instead-of-element]
(defn place
  ([board ships]
   (->> ships
        (filter valid-coordinate?)
        (reduce place-single-ship board))))
