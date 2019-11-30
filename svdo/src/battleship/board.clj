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

(defn- valid-coordinate? [[_ coordinates]]
  (and (seq coordinates)
       (every? on-grid? coordinates)))

(defn- existing-coordinates [board]
  (set (apply concat (map second board))))

(defn- unique-coordinates? [board [_ new-coords]]
  (prn board new-coords)
  (let [existing (existing-coordinates board)]
    (every? false?
            (map #(contains? existing %1) new-coords))))

(def truth-to-descriptive
  ;; Technique used: [dictionary-lookup] 
  {true :non-overlapping
   false :overlapping})
(defmulti concat-if-unique
  ;; Technique used: [dynamic-dispatch]
  (fn [ships new-ship]
    (truth-to-descriptive (unique-coordinates? ships new-ship))))

(defmethod concat-if-unique :non-overlapping [ships new-ship]
  (concat ships [new-ship]))
(defmethod concat-if-unique :overlapping [ships _]
  ships)

(defn- non-overlapping [ships]
  (reduce concat-if-unique [] ships))

(defn place
  ;; Technique used: [collection-instead-of-element]
  ([board ships]
   (->> (non-overlapping ships)
        (filter valid-coordinate?)
        (filter (fn [ship] (unique-coordinates? board ship)))
        (reduce place-single-ship board))))
