(ns advent-of-code.2025.day1
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]))

(def nums
  (->
   (utils/input->str "2025/day1.txt")
   (str/replace "L" "-")
   (str/replace "R" "+")
   (str/split-lines)
   (#(map read-string %))))

(quot 50 100)

(defn spin-to-win [[size pos zeros] delta]
  (let [newpos (rem (+ pos delta) size)]
    (cond
      (> newpos 0) [size newpos zeros]
      (= newpos 0) [size newpos (inc zeros)]
      (< newpos 0) [size (+ 100 newpos) zeros])))

(reduce spin-to-win [100 50 0] nums) ; 1st star

(defn spin-to-win-star2 [[size pos zeros] delta]
  (let [newpos (rem (+ pos delta) size)
        rotations (-> (+ pos delta)
                      (quot size)
                      abs)
        crossed (cond (< pos 0 newpos) 1
                      (> pos 0 newpos) 1
                      :else 0)
        overflow? (>= (+ pos delta) size)]
    (cond
      (> newpos 0) [size newpos (+ rotations zeros)]

      ;; make sure we don't count twice (rotations up to 0 and our pos on zero)
      (= newpos 0) [size newpos
                    (+ 1 (if overflow? (dec rotations) rotations) zeros)]

      ;; only when we cross 0 we should add one
      (< newpos 0) [size (+ size newpos) (+ crossed rotations zeros)])))

(reduce spin-to-win-star2 [100 50 0] nums)
