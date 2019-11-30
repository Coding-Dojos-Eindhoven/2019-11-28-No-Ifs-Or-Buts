(ns battleship.board-test
  (:require [clojure.test :refer [deftest testing is]]
            [battleship.board :refer [empty-board place]]))

(def coordinates [[\a 3] [\a 4]])

(deftest board-test
  (testing "a destroyer is placed on given coordinates"
    (let [updated (place empty-board {:destroyer coordinates})]
      (is (= (:destroyer updated) coordinates))))

  (testing "another ship type can also be placed"
    (let [updated (place empty-board {:cruiser coordinates})]
      (is (= (:cruiser updated) coordinates))))

  (testing "multiple ships can be placed at once"
    (let [updated (-> empty-board
                      (place {:destroyer []
                              :battleship []}))]
      (is (and (some? (:destroyer updated))
               (some? (:battleship updated)))))))
