(ns battleship.board-test
  (:require [clojure.test :refer :all]
            [battleship.board :refer [empty-board]]))

(deftest board-test
  (testing "the board is an empty map"
    (is (map? empty-board))))
