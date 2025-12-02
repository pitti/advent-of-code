(ns advent-of-code.2025.day2
  (:require [advent-of-code.utils :as utils]
            [clojure.string :as str]))

(defn invalid-nums
  "applies fn on all numbers in the closed interval [start, end]"
  [fn [start end]]
  (filter some? (map fn (range (Long. start) (inc (Long. end))))))

(defn solve [fn]
  (->>
   (str/split (str/trim (utils/input->str "2025/day2.txt")) #",")
   (map #(str/split % #"-"))
   (mapcat #(invalid-nums fn %))
   (reduce +)))

(defn star1-invalid?
  "an invalid id only contains a sequence of digits repeated twice"
  [id]
  (let [strid (str id)]
    (when (first (re-matches (re-pattern (str "(\\d{" (quot (count strid) 2) "})\\1")) strid))
      id)))

(defn star2-invalid?
  "an invalid id only contains a sequence of digits repeated at least twice"
  [id]
  (when (first (re-matches #"(\d+)\1+" (str id))) id))

(solve star1-invalid?)  ; 1st star

(solve star2-invalid?)

