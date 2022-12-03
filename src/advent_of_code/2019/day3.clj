(ns advent-of-code-2019.day3
  (:require [advent-of-code-2019.utils :as utils]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn get-input
  [line]
  (nth
   (clojure.string/split-lines
    (slurp (io/resource "input3.txt"))) line))

(def input1 (get-input 0))
(def input2 (get-input 1))

(def field {})

(defn execute-path
  [field cmd len]

  (map

   #()))

(defn parse-command
  [cmd]
  (vector
   (case (subs cmd 0 1)
     "L" [-1 0]
     "R" [1 0]
     "U" [0 1]
     "D" [0 -1])
   (Integer. (subs cmd 1))))

(parse-command "U123")

(defn walk
  ([dir steps] (walk dir steps [[0 0]]))
  ([dir steps path]
   (if (zero? steps)
     path
     (recur dir
            (dec steps)
            (conj path (mapv + (last path) dir))))))

(walk [0 1] 4 [[0 10]])

;; (start, command) -> (coordinates)
(defn get-path
  [start cmd]
  (println "i want to walk " start cmd)
  (let [[dir steps] (parse-command cmd)]
    (println "walking " dir steps start)
    (walk dir steps [start])))

(get-path (last [ [23 9]]) "R4")


(defn run-commands
  ([commands] (run-commands [[0 0]] commands ))
  ([path commands]
   (println "running commands " commands "on path " path "with" (last path))
   (if (empty? commands)
     path
     (recur (conj path (get-path (last path) (first commands)))
            (rest commands)
            ))))

(run-commands (str/split input1 #","))
(run-commands ["R1" "L2"])

(conj [[0 0]] [[1 2]])

(last [[0 1] [1 0] [1 1]])


(rest ["foo"])


(str/split input1 #",")
(get-path [0 0] "R13")
