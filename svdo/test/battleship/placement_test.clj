(ns battleship.placement-test
  (:require [clojure.test :refer [deftest testing is]]
            [battleship.placement :refer [empty-board place]]))

(def destroyer-coordinates [[\a 3] [\a 4]])
(def cruiser-coordinates [[\b 4] [\b 5] [\b 6]])
(def battleship-coordinates [[\a 2] [\b 2] [\c 2] [\d 2]])

(def board-with-destroyer {:destroyer {:coordinates [[\b 1] [\b 2]]}})

(deftest placement-test
  (testing "a destroyer is placed on given coordinates"
    (let [updated (place empty-board {:destroyer destroyer-coordinates})]
      (is (= (-> updated :destroyer :coordinates) destroyer-coordinates))))

  (testing "another ship type can also be placed"
    (let [updated (place empty-board {:cruiser cruiser-coordinates})]
      (is (= (-> updated :cruiser :coordinates) cruiser-coordinates))))

  (testing "multiple ships can be placed at once"
    (let [updated (-> empty-board
                      (place {:destroyer destroyer-coordinates
                              :battleship battleship-coordinates}))]
      (is (and (some? (:destroyer updated))
               (some? (:battleship updated))))))

  (testing "ships with empty coordinates are ignored"
    (let [updated (-> empty-board
                      (place {:destroyer []}))]
      (is (= empty-board updated))))

  (testing "ships outside grid are ignored"
    (let [updated (-> empty-board
                      (place {:destroyer [[\a 0] [\a 1]]
                              :cruiser [[\b 2] [\b 1] [\b 0]]
                              :submarine [[\c 9] [\c 10] [\c 11]]
                              :battleship [[\k 5] [\j 5] [\i 5] [\h 5]]}))]
      (is (= empty-board updated))))

  (testing "a ships that overlaps an existing one is ignored"
    (let [updated (-> board-with-destroyer
                      (place {:cruiser [[\c 2] [\b 2] [\a 2]]}))]
      (is (= board-with-destroyer updated))))

  (testing "new ships are also checked for overlap"
    (let [updated (-> empty-board
                      (place {:destroyer [[\b 1] [\b 2]]
                              :cruiser [[\c 2] [\b 2] [\a 2]]}))]
      (is (= board-with-destroyer updated)))))
