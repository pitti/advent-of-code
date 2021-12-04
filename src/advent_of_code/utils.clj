(ns advent-of-code.utils
  (:gen-class)
  (:require [clojure.java.io :as io]))

(defn as-num-vector
  "Reads from the file and returns a vector of numbers"
  [file]
  (with-open [rdr (io/reader file)]
    (map read-string
         (reduce conj [] (line-seq rdr)))))

(defn apply-and-sum
  "Applies fn on the list of numbers in file and returns the sum of the operation"
  [fn file]
  (reduce + 0
          (map fn (as-num-vector file))))
