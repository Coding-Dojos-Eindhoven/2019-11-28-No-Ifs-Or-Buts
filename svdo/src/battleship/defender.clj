(ns battleship.defender)

(defn- ship-contains-coordinate [ship coordinate]
  ; (prn ship)
  (as-> (second ship) $
    (:coordinates $)
    (some (fn [c] (= c coordinate)) $)))

(defn find-ship [board coordinate]
  (first (seq (filter #(ship-contains-coordinate %1 coordinate) board))))

(defn guess [board coordinate]
  ;; Technique used: [clever-api-usage]:
  ;; a) When `found-ship` is `nil`, the `update-in` will add a new key `nil` to
  ;;    the board map. We can filter that out if we don't want to have it, but
  ;;    in this case we might as well keep it: it tells us where the player has
  ;;    guessed wrong.
  ;; b) The `update-in` function specifies that whenever a key does not exist,
  ;;    it will be created for us. No conditionals needed.
  ;; c) The function passed to `update-in`, the one that updates the board,
  ;;    needs to either add a new coordinate to the set, or create a set if it
  ;;    didn't exist yet. This is done automatically by `conj`.
  (let [found-ship  (find-ship board coordinate)
        ship-type first]
    {:result (-> found-ship ship-type)
     :board (as-> found-ship $
              (update-in board [(ship-type $) :hits]
                         (fn [old] (set (conj old coordinate)))))}))

(defn sunk? [board ship]
  (= (set (-> board ship :coordinates))
     (-> board ship :hits)))

(comment
  (def destroyer-coordinates [[\a 3] [\a 4]])
  (def board-with-destroyer {:destroyer {:coordinates destroyer-coordinates}})

  (def guessed-coordinate [\a 4])
  (find-ship board-with-destroyer guessed-coordinate)
  ;; => [:destroyer {:coordinates [[\a 3] [\a 4]]}]

  (guess board-with-destroyer guessed-coordinate)
  ;; => {:result :destroyer, :board {:destroyer {:coordinates [[\a 3] [\a 4]], :hits #{[\a 4]}}}}

  (guess board-with-destroyer [\j 10])
  ;; => {:result nil, :board {:destroyer {:coordinates [[\a 3] [\a 4]]}, nil {:hits #{[\j 10]}}}}
  )
