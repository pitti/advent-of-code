(ns advent-of-code.2025.day3
  (:require [advent-of-code.utils :as utils]))

(defn pick-jolts [[jolts r] xs]
  (let [subs (drop-last (dec r) xs)
        [idx maxval] (apply max-key second (reverse (map-indexed vector subs)))]
    (cond
      (> r 1) (pick-jolts [(conj jolts maxval) (dec r)] (drop (inc idx) xs))
      :else (conj jolts maxval))))

(defn pick-jolts-str [ls num]
  (read-string
   (reduce str
           (pick-jolts [[] num] (utils/digits->list ls)))))

(defn solve [input len]
  (reduce +
          (map
           #(pick-jolts-str % len)
           input)))

(solve (utils/input->lines "2025/day3.txt") 2) ; 1st star

(solve (utils/input->lines "2025/day3.txt") 12) ; 2nd star

