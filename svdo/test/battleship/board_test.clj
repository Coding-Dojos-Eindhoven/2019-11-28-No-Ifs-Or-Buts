(ns battleship.board-test
  (:require [clojure.test :refer [deftest testing is]]
            [battleship.board :refer [empty-board place]]))

(deftest board-test
  (testing "a ship is placed on given coordinates"
    (let [coordinates [[\a 3] [\a 4]]
          updated (place empty-board :destroyer coordinates)]
      (is (= (:destroyer updated) coordinates)))))
