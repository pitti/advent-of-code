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
         (concat (crate n) (reverse items))))

; push the items (in same order) to the crate, return the new crate
(defn push-crate-star2 [n items crate]
  (assoc crate n
         (concat (crate n) items)))

; move num crates from stack 'from' to 'to' with the help of a pusher-fn 'f'
(defn move
  [f stacks [num from to]]
  (->> stacks
       (f to (peek-crate from num stacks))
       (pop-crate from num)))

(defn create-mover [f]
  (fn
    ([stacks] stacks)
    ([stacks instr] (move f stacks instr))))

; get all top crates from the stacks
(defn top-crates [stacks]
  (->>
   (seq (into (sorted-map) stacks))
   (map (comp last last))))

(def star1 (create-mover push-crate))
(def star2 (create-mover push-crate-star2))

(defn parse-instr [s]
  (read-string
   (str "("
        (clojure.string/replace s #"[a-z]" "") ")")))

(defn read-instr [fname]
  (->> (filter #(str/starts-with? % "move")
               (utils/input->lines fname))
       (map parse-instr)))

; star1
(->> (read-instr "2022/day5.txt")
     (reduce star1 crates)
     (top-crates))

; star2
(->> (read-instr "2022/day5.txt")
     (reduce star2 crates)
     (top-crates))
