(ns advent-of-code.2022.day6
  (:require [advent-of-code.utils :as utils]))

(defn marker? [idx coll]
  (if (apply distinct? coll) idx false))

(defn compute [n input]
  (->> (partition n 1 input)
       (map #(into [] %))
       (map-indexed marker?)
       (filter identity)
       (first)
       (+ n)))

; 1st star
(compute 4 (utils/input->str "2022/day6.txt"))

; 2nd star
(compute 14 (utils/input->str "2022/day6.txt"))
