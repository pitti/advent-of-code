(ns advent-of-code-2019.day2)

(def memory [1, 0, 0, 3, 1, 1, 2, 3, 1, 3, 4, 3, 1, 5, 0, 3, 2, 6, 1, 19, 1,
             19, 5, 23, 2, 9, 23, 27, 1, 5, 27, 31, 1, 5, 31, 35, 1, 35, 13,
             39, 1, 39, 9, 43, 1, 5, 43, 47, 1, 47, 6, 51, 1, 51, 13, 55, 1,
             55, 9, 59, 1, 59, 13, 63, 2, 63, 13, 67, 1, 67, 10, 71, 1, 71, 6,
             75, 2, 10, 75, 79, 2, 10, 79, 83, 1, 5, 83, 87, 2, 6, 87, 91, 1,
             91, 6, 95, 1, 95, 13, 99, 2, 99, 13, 103, 1, 103, 9, 107, 1, 10,
             107, 111, 2, 111, 13, 115, 1, 10, 115, 119, 1, 10, 119, 123, 2,
             13, 123, 127, 2, 6, 127, 131, 1, 13, 131, 135, 1, 135, 2, 139, 1,
             139, 6, 0, 99, 2, 0, 14, 0])

(defn execute-instr
  [memory instr op1 op2 target]
  (case instr
    1 (assoc memory target (+ op1 op2))
    2 (assoc memory target (* op1 op2))
    memory))

(defn run-program
  "Execute the instruction given by the IP on the memory"
  ([memory]
   (run-program memory 0))
  ([memory ip]
   (let [[instr pos1 pos2 target] (nthrest memory ip)]
     (if (= 99 instr)
       (first memory)
       (recur (execute-instr memory instr (nth memory pos1) (nth memory pos2) target)
              (+ ip 4))))))

(defn run
  [memory op1 op2]
  (run-program
   (assoc (assoc memory 1 op1) 2 op2)))

(def memo-run (memoize run))

(defn star1 []
  (memo-run memory 12 2))

(defn star2 []
  (for [op1 (range 100) op2 (range 100)
        :when (= 19690720 (memo-run memory op1 op2))]
    (+ (* op1 100) op2)))
