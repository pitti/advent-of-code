(ns advent-of-code.2021.day2

  (:require [clojure.string :as str]
            [clojure.java.io :as io]
            [advent-of-code.utils :as utils]))

(def testinput
  "forward 5
down 5
forward 8
up 3
down 8
forward 2")

(defn drive-uboot
  [state cmd]

  (let [[direction steps] cmd]
    (utils/inc-val state direction (Integer. steps))))

(defn get-position-and-depth [state]
  (* (state "forward") (- (state "down") (state "up"))))

(defn star1 [input]
  (->> input
       (map #(str/split %1 #"\s"))
       (reduce drive-uboot {})
       (get-position-and-depth)))

(star1 (utils/input->lines "day2.txt"))

(defn move-forward-with-depth [state steps]
  (-> state
      (utils/inc-val "position" steps)
      (utils/inc-val "depth" (* steps (get state "aim" 0)))))

(defn drive-uboot-star2
  [state [direction steps]]
  (case direction
    "forward" (move-forward-with-depth state steps)
    "up" (utils/inc-val state "aim" (- steps))
    "down" (utils/inc-val state "aim" steps)))


(defn get-position-and-depth2 [state]
  (* (state "position") (state "depth")))

(defn star2 [input]
(->> input
     (map #(str/split % #"\s"))
     (map (fn [[direction steps]] [direction (Integer. steps)]))
     (reduce drive-uboot-star2 {})
     get-position-and-depth2))


(star2 (utils/input->lines "day2.txt"))
