(ns battleship.board-test
  (:require [clojure.test :refer [deftest testing is]]
            [battleship.board :refer [empty-board place]]))

(deftest board-test
  (testing "the board is an empty map"
    (is (map? empty-board)))

  (testing "a destroyer can be placed on the board"
    (let [updated (place empty-board :destroyer [])]
      (is (some? (:destroyer updated)))))

  (testing "a ship is placed on given coordinates"
    (let [coordinates [[\a 3] [\a 4]]
          updated (place empty-board :destroyer coordinates)]
      (is (= (:destroyer updated) coordinates)))))
