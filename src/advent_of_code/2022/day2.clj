(ns advent-of-code.2022.day2
  (:require
   [advent-of-code.utils :as utils]
   [clojure.core.match :as m]))

; check if m2 wins against m1
(defn win? [[m1 m2]]
  (m/match [m1 m2]
    ['A 'B] true
    ['B 'C] true
    ['C 'A] true
    :else nil))

(defn draw? [[m1 m2]]
  (= m1 m2))

(defn game-score [move]
  (cond
    (win? move) 6
    (draw? move) 3
    :else 0))

(defn final-score [[m1 m2]]
  (+ (game-score [m1 m2]) ({'A 1 'B 2 'C 3} m2)))

(defn eval-input [move-selector lines]
  (->> lines
       (map
        (comp
         #(apply final-score [%])
         move-selector
         read-string
         #(str "[" % "]")))
       (reduce +)))

(defn star2-selector [[m order]]
  (m/match [order]
    ['X] [m ({'A 'C 'B 'A 'C 'B} m)] ; win
    ['Y] [m m] ; draw
    ['Z] [m ({'A 'B 'B 'C 'C 'A} m)] ; lose
    ))

(defn star1-selector [[m order]]
  [m ({'X 'A 'Y 'B 'Z 'C} order)])

; 1st star
(eval-input star1-selector (utils/input->lines "2022/day2.txt"))

; 2nd star
(eval-input star2-selector (utils/input->lines "2022/day2.txt"))
