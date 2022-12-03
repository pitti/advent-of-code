(ns advent-of-code.2021.day3

  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [advent-of-code.utils :as utils]))

(def testinput
  "00100
11110
10110
10111
10101
01111
00111
11100
10000
11001
00010
01010")

(defn sum-vec [first ns]
  (map + first ns))

(defn amplify [ns]
  (map (partial * 2) ns))

(defn lower [ns]
  (map (partial - 1) ns))

(defn sign [ns]
  (map #(if (> 0 %) 1 0) ns))

(defn gamma-rate [input]
  (->> input
       (str/split-lines)
       (map utils/digits->list)
       (map amplify)
       (map lower)
       (reduce sum-vec)
       (sign)))

(lower (gamma-rate testinput))

(defn powers-of-two [] (iterate (partial * 2) 1))

(defn to-decimal [xs]
  (reduce +  (map * (reverse xs) (powers-of-two))))

(defn star1 [input]
  (let [gamma (gamma-rate input)
        epsilon (lower gamma)]

    (* (to-decimal gamma) (to-decimal epsilon))))


(star1 (utils/input->str "day3.txt"))
