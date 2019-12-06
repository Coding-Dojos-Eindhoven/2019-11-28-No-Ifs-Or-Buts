(ns battleship.defender-test
  (:require [clojure.test :refer [deftest testing is]]
            [battleship.placement :refer [empty-board]]
            [battleship.defender :refer [guess find-ship sunk?]]))

(def destroyer-coordinates #{[\a 3] [\a 4]})
(def cruiser-coordinates #{[\b 4] [\b 5] [\b 6]})

(def board-with-destroyer {:destroyer {:coordinates destroyer-coordinates}})
(def board-with-multiple-ships {:destroyer {:coordinates destroyer-coordinates}
                                :cruiser {:coordinates cruiser-coordinates}})

(deftest defender-test
  (testing "it responds with 'nil' to indicate nothing was hit by default"
    (is (= nil (:result (guess empty-board [\a 1])))))

  (testing "it cannot find a ship on an empty board"
    (is (= nil (find-ship empty-board [\a 1]))))

  (testing "it can find a ship on a non-empty board"
    (is (= :cruiser (first (find-ship board-with-multiple-ships [\b 5])))))

  (testing "given a ship, it can be missed"
    (let [guess-result (guess board-with-destroyer [\b 4])]
      (is (= nil (:result guess-result)))
      (is (= (:destroyer board-with-destroyer) (:destroyer (guess-result :board))))))

  (testing "misses are also recorded"
    (let [guess-result (guess board-with-destroyer [\b 4])]
      (is (= nil (:result guess-result)))
      (is (= (merge board-with-destroyer {nil {:hits #{[\b 4]}}})
             (guess-result :board)))))

  (testing "given a ship, it can be a hit"
    (let [guess-result (guess board-with-destroyer [\a 4])]
      (is (= :destroyer (:result guess-result)))
      (is (= #{[\a 4]} (-> guess-result :board :destroyer :hits)))))

  (testing "given a ship, it can be a hit"
    (let [{:keys [board]} (guess board-with-destroyer [\a 4])
          guess-result (guess board [\a 3])]
      (is (= :destroyer (:result guess-result)))
      (is (= #{[\a 4] [\a 3]} (-> guess-result :board :destroyer :hits)))))

  (testing "it can be hit again in the same location"
    (let [{:keys [board]} (guess board-with-destroyer [\a 4])
          guess-result (guess board [\a 4])]
      (is (= #{[\a 4]} (-> guess-result :board :destroyer :hits)))))

  (testing "it knows whether a ship has sunk"
    (let [{:keys [board]} (guess board-with-destroyer [\a 4])
          guess-result (guess board [\a 3])]
      (is (not (sunk? (:board board) :destroyer)))
      (is (sunk? (:board guess-result) :destroyer)))))
