(ns advent-of-code.2019.day1
  (:gen-class)
  (:require [clojure.java.io :as io]
            [advent-of-code.utils :as utils]
            [clojure.string :as str]))

(defn fuel [n]
  (max 0
       (- (int (Math/floor (/ n 3))) 2)))

(defn solve [fn input]
  (transduce
   (comp
    (map read-string)
    (map fn))
   +
   (str/split-lines input)))

(defn fuel2
  "Calculate the fuel for the given mass and the remaining fuel for that mass
  until no more fuel is required."
  ([weight] (fuel2 0 weight))
  ([rem weight]
   (if (zero? weight)
     rem
     (let [n (fuel weight)]
       (recur (+ rem n) n)))))

; 1st star
(solve fuel (utils/input->str "2019/day1.txt"))

; 2nd star
(solve fuel2 (utils/input->str "2019/day1.txt"))
