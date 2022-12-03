(ns advent-of-code-2019.day1
  (:gen-class)
  (:require [clojure.java.io :as io]))

(defn calc-fuel [weight]
  (max 0
       (- (quot weight 3) 2)))

(defn calc-fuel-star2
  "Calculate the fuel for the given mass and the remaining fuel for that mass
  until no more fuel is required."
  ([weight]
   (calc-fuel-star2 0 weight))
  ([rem weight]
   (if (zero? weight)
     rem
     (let [fuel (calc-fuel weight)]
       (recur (+ rem fuel) fuel)))))
