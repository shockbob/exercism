(ns killer-sudoku-helper)
(defn all-ok [group bad] 
   (nil? (some (set bad) group)))
(defn combinations
  "Returns the valid combinations for a given cage."
  [cage]
  (let [combos (cond (= 1 (cage :size ))
                         [[(cage :sum)]]
                    (and (= 45 (cage :sum)) (= 9 (cage :size )))
                         [[1 2 3 4 5 6 7 8 9]]
                    (= 2 (cage :size))
                       (filter identity (for [i (range 1 10) j (range (inc i) 10) ] (if (= (cage :sum) (+ i j)) [i j])))
                    (= 3 (cage :size))
                       (filter identity (for [i (range 1 10) j (range (inc i) 10) k (range (inc j) 10)] (if (= (cage :sum) (+ i j k)) [i j k])))
     )]
    (filter (fn [group] (all-ok group (cage :exclude))) combos)
  ))
