(ns advent-of-code.2025.day3
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]))

(def testinput
  "987654321111111
811111111111119
234234234234278
818181911112111")

(defn choose-pair
  ([[a b] n]
   (let [ab (+ (* 10 a) b)
         bn (+ (* 10 b) n)
         an (+ (* 10 a) n)]
     (cond
       (> bn ab) [b n]
       (> an ab) [a n]
       :else [a b]))))

(defn solve [ls]
  (->> (map utils/digits->list ls)
       (map #(reduce choose-pair [0 0] %))
       (map (fn [[a b]] (+ (* 10 a) b)))
       (reduce +)))

(solve (str/split-lines testinput))

(solve (utils/input->lines "2025/day3.txt")) ; 1st star

