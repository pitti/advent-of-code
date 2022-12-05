(ns advent-of-code.2022.day5
  (:require [clojure.string :as str]
            [advent-of-code.utils :as utils]))

; initial crates, first pos is top of stack, last pos is bottom
(def crates {1 (reverse '(R C H))
             2 (reverse '(F S L H J B))
             3 (reverse '(Q T J H D M R))
             4 (reverse '(J B Z H R G S))
             5 (reverse '(B C D T Z F P R))
             6 (reverse '(G C H T))
             7 (reverse '(L W P B Z V N S))
             8 (reverse '(C G Q J R))
             9 (reverse '(S F P H R T D L))})

(def testcrates {1 '(Z N)
                 2 '(M C D)
                 3 '(P)})

; return the items (in same order) from the crate
(defn peek-crate [n num crate]
  (take-last num (crate n)))

; remove num items from the top of the crate n, return the crate
(defn pop-crate [n num crate]
  (assoc crate n
         (drop-last num (crate n))))

; push the items (in reverse order) to the crate, return the new crate
(defn push-crate [n items crate]
  (assoc crate n
         (concat (crate n) items)))

(defn move
  [[num from to] crate]
  (->> crate
       (push-crate to (peek-crate from num crate))
       (pop-crate from num)))

(defn create-code [s]
  (read-string
   (str "("
        (clojure.string/replace s #"[a-z]" "") ")")))

(defn run-moves
  ([crate] (crate))
  ([crate instr] (move instr crate))
  ([crate instr & rest]
   (apply run-moves (move instr crate) rest)))

(run-moves testcrates
           (create-code "move 1 from 2 to 1")
           (create-code "move 3 from 1 to 3")
           (create-code "move 2 from 2 to 1")
           (create-code "move 1 from 1 to 2"))

; move 1 from 2 to 1
; move 3 from 1 to 3
; move 2 from 2 to 1
; move 1 from 1 to 2

(map last
     (map last
          (seq (into (sorted-map)
                     (reduce run-moves crates
                             (->> (filter #(str/starts-with? % "move")
                                          (utils/input->lines "2022/day5.txt"))
                                  (map create-code)))))))
