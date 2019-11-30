(ns battleship.board-test
  (:require [clojure.test :refer [deftest testing is]]
            [battleship.board :refer [empty-board place]]))

(def destroyer-coordinates [[\a 3] [\a 4]])
(def cruiser-coordinates [[\b 4] [\b 5] [\b 6]])
(def battleship-coordinates [[\a 2] [\b 2] [\c 2] [\d 2]])

(deftest board-test
  (testing "a destroyer is placed on given coordinates"
    (let [updated (place empty-board {:destroyer destroyer-coordinates})]
      (is (= (:destroyer updated) destroyer-coordinates))))

  (testing "another ship type can also be placed"
    (let [updated (place empty-board {:cruiser cruiser-coordinates})]
      (is (= (:cruiser updated) cruiser-coordinates))))

  (testing "multiple ships can be placed at once"
    (let [updated (-> empty-board
                      (place {:destroyer destroyer-coordinates
                              :battleship battleship-coordinates}))]
      (is (and (some? (:destroyer updated))
               (some? (:battleship updated))))))

  (testing "ships with empty coordinates are ignored"
    (let [updated (-> empty-board
                      (place {:destroyer []}))]
      (is (= empty-board updated)))))
