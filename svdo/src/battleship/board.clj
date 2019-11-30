(ns battleship.board)

(def empty-board {})

(defn- place-single-ship
  [board [ship-type coordinates]]
  (assoc board ship-type coordinates))

(defn- valid-col? [col]
  (some #{col} (range 1 11)))

(defn- valid-row? [row]
  (some #{(int row)} (range (int \a) (int \k))))

(defn- on-grid? [[row col]]
  (and (valid-col? col) (valid-row? row)))

(defn valid-coordinate? [[_ coordinates]]
  (and (seq coordinates)
       (every? on-grid? coordinates)))

;; Technique used: [collection-instead-of-element]
(defn place
  ([board ships]
   (->> ships
        (filter valid-coordinate?)
        (reduce place-single-ship board))))
