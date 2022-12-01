(ns advent-of-code.2022.day1
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(def testinput "1000
2000
3000

4000

5000
6000

7000
8000
9000

10000")

(defn str-array-to-int [arr]
  (->>
   (map read-string arr)
   (into [])))

(defn day1 [input]
  (->> (str/split input #"\n\n")
       (map str/split-lines)
       (map str-array-to-int)
       (map #(reduce + %))
       (map-indexed (fn [idx v] [v (inc idx)]))
       (sort-by first >)))

; print out [kcal num]
(first (day1 testinput))

(first (day1 (utils/input->str "day1.txt")))
