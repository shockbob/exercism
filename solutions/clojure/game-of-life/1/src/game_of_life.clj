(ns game-of-life)

(def neighbors 
   (remove #{[0 0]} (for [i [-1 0 1] j [-1 0 1]] [ i j])))
(defn valid-coord [row col num-rows num-cols]
   (and (< -1 row num-rows) (< -1 col num-cols)))

(defn get-living-neighbors [cells row col num-rows num-cols]
    (apply + (for [neighbor neighbors] 
                 (let [new-row (+ row (neighbor 0)) 
                       new-col (+ col (neighbor 1))]
                   (if (valid-coord new-row new-col num-rows num-cols)
                     ((cells new-row)new-col)
                     0                    
                     )
    ))))
(defn tick
  "Returns the next generation of the cells."
  [cells]
  (if (empty? cells)
    []
    (let [num-rows (count cells)
          num-cols (count (cells 0))]
     (for [row (range (count cells))]
       (for [col (range (count (cells row)))] 
            (let [neighbors (get-living-neighbors cells row col num-rows num-cols)]
               (if (= 1 ((cells row) col))
                  (if (or (= neighbors 3)(= neighbors 2))
                      1
                      0
                  )
                  (if ( = neighbors 3)
                     1
                     0))))))))