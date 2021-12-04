(ns advent-of-code.day1
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn measurement-increases [[a b]]
  ({false 0 true 1} (> b a)))

(def input
  (->> "day1.txt"
       io/resource
       slurp
       str/split-lines
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

(->> input
     (partition 3 1)
     (map #(reduce + %))
     (partition 2 1)
     (map measurement-increases)
     (reduce +))

(defn num-increases [ns]
  (->> (partition 2 1 ns)
       (map measurement-increases)
       (reduce +)))

(defn day1-star1 [input]
  (->> input
       num-increases))

(defn day1-star2 [input]
  (->> input
       (partition 3 1)
       (map #(reduce + %))
       (num-increases)))


(day1-star1 input)

(day1-star2 input)
