(ns battleship.placement)

(def empty-board {})

(defn- place-single-ship
  [board [ship-type coordinates]]
  (assoc board ship-type {:coordinates coordinates}))

(defn- valid-col? [col]
  (some #{col} (range 1 11)))

(defn- valid-row? [row]
  (some #{(int row)} (range (int \a) (int \k))))

(defn- on-grid? [[row col]]
  (and (valid-col? col) (valid-row? row)))

(defn- valid-coordinate? [[_ coordinates]]
  (and (seq coordinates)
       (every? on-grid? coordinates)))

(defmulti
  extract-coordinates
  "Extracts coordinates from a data structure. This data structure can be a board
   (represented using a map) or a list of ships (represented as a sequence of
   ship type -> coordinates pairs)."
  (fn [board-or-ships] (map? board-or-ships)))
(defmethod extract-coordinates true [board]
  (map :coordinates (vals board)))
(defmethod extract-coordinates false [ships]
  (map second ships))

(defn- existing-coordinates [board]
  (set (apply concat (extract-coordinates board))))

(defn- unique-coordinates? [existing [_ new-coords]]
  (let [return-value (every? false?
                             (map #(contains? existing %1) new-coords))]
    (prn "unique-coordinates?" existing new-coords "=>" return-value)
    return-value))

(def truth-to-descriptive
  ;; Technique used: [dictionary-lookup] 
  {true :non-overlapping
   false :overlapping})

(defmulti concat-if-unique
  ;; Technique used: [dynamic-dispatch]
  (fn [ships new-ship]
    (truth-to-descriptive (unique-coordinates? (existing-coordinates ships) new-ship))))

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
        (filter (fn [ship] (unique-coordinates? (existing-coordinates board) ship)))
        (reduce place-single-ship board))))

(comment
  (def destroyer-coordinates #{[\a 3] [\a 4]})
  (def cruiser-coordinates #{[\b 4] [\b 5] [\b 6]})
  (def battleship-coordinates #{[\a 2] [\b 2] [\c 2] [\d 2]})
  (def board {:destroyer {:coordinates destroyer-coordinates}
              :battleship {:coordinates battleship-coordinates}})

  (existing-coordinates board)
  ;; => #{[\a 2] [\b 2] [\a 3] [\a 4] [\c 2] [\d 2]}

  (unique-coordinates? (existing-coordinates board) [[\a 2]])
  ;; => true

  (unique-coordinates? (existing-coordinates board) [[\j 10]])
  ;; => true

  (place board {:cruiser cruiser-coordinates})
  ;; => {:destroyer {:coordinates #{[\a 3] [\a 4]}},
  ;;     :battleship {:coordinates #{[\a 2] [\b 2] [\c 2] [\d 2]}},
  ;;     :cruiser {:coordinates #{[\b 4] [\b 6] [\b 5]}}}
  )
