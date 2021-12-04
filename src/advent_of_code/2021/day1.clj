(ns advent-of-code.2021.day1

  (:require [advent-of-code.utils :as utils]))

(defn num-increases [ns]
  (->> ns
       (filter #(< (first %) (second %)))
       count))

(def input
  (->> (utils/input->lines "day1.txt")
       (map read-string)
       (into [])))

(def testinput
  [199
   200
   208
   210
   200
   207
   240
   269
   260
   263])

(defn day1-star1 [input]
  (->> input
       (partition 2 1)
       num-increases))

(defn day1-star2 [input]
  (->> input
       (partition 3 1)
       (map #(reduce + %))
       day1-star1))

(day1-star1 input)

(day1-star2 input)
