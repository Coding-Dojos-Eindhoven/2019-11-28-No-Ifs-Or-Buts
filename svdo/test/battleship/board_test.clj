(ns battleship.board-test
  (:require [clojure.test :refer :all]
            [battleship.board :refer [empty-board]]))

(deftest board-test
  (testing "there is a board"
    (is (any? empty-board))))
