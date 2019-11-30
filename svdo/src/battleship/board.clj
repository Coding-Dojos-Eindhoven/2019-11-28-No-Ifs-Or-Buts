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

(defn existing-coordinates [board]
  (set (apply concat (vals board))))

(defn unique-coordinates? [board [_ new-coords]]
  (prn board new-coords)
  (let [existing (existing-coordinates board)]
    (every? false?
            (map #(contains? existing %1) new-coords))))

(defn place
  ;; Technique used: [collection-instead-of-element]
  ([board ships]
   (->> ships
        (filter valid-coordinate?)
        (filter (fn [ship] (unique-coordinates? board ship)))
        (reduce place-single-ship board))))
