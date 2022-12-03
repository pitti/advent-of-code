(ns advent-of-code.2022.day3
  (:require
   [advent-of-code.utils :as utils]
   [clojure.set :as set]))

(defn prio [c]
  (let [i (int c)]
    (cond
      (> i 97) (- i 96) ; a-z -> 1 - 26
      (> i 65) (- i 38)))) ; A - Z -> 27 - 52

(defn duplicates [xs]
  (->> xs
       (map set)
       (reduce set/intersection)
       (into [])))

(defn compute [f]
  (->> (utils/input->lines "2022/day3.txt")
       f
       (map duplicates)
       (flatten)
       (map prio)
       (reduce +)))

; first star
(compute (fn [s] (map #(split-at (/ (count %) 2) %) s)))

; 2nd star
(compute #(partition 3 %))
