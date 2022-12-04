(ns advent-of-code.2022.day4
  (:require [clojure.string :as str]
            [advent-of-code.utils :as utils]))

(defn fully-overlaps?
  "checks if one interval fully overlaps the other"
  [[a b] [x y]]
  (or
   (<= a x y b)
   (<= x a b y)))

(defn overlaps?
  "checks if one interval (partially) overlaps the other"
  [[a b] [x y]]
  (and
   (<= x b)
   (<= a y)))

(defn str->vec [s]
  (into []
        (map read-string)
        (str/split s #"-")))

(defn str-contains? [f [a b]]
  (f (str->vec a) (str->vec b)))

(defn compute [f input]
  (transduce
   (comp
    (map #(str/split % #","))
    (map #(str-contains? f %))
    (filter identity)
    (map (constantly 1)))
   +
   input))

; 1st star
(compute fully-overlaps? (utils/input->lines "2022/day4.txt"))

; 2nd star
(compute overlaps? (utils/input->lines "2022/day4.txt"))
