(ns battleship.defender-test
  (:require [clojure.test :refer [deftest testing is]]
            [battleship.placement :refer [empty-board]]
            [battleship.defender :refer [guess]]))

(deftest defender-test
  (testing "it responds with 'miss' by default"
    (is (= :miss (:result (guess empty-board [\a 1]))))))
