(ns spiral-matrix)

(defn square [n]
    (vec (map vec (repeat n (repeat n 0)))))

(defn inrange [row col a n]
    (and (< -1 row n) 
         (< -1 col n) 
         (zero? (get-in a [row col]))))

(defn turn90 [row-dir col-dir]
   [col-dir (- row-dir)])

(defn spr 
   ([n] (spr (square n) n 0 0 0 1 1))
   ([arr n row col row-dir col-dir start]
     (if (pos? (get-in arr [row col]))
        arr
        (let [arr (assoc-in arr [row col] start)
              new-row (+ row row-dir)
              new-col (+ col col-dir)]
              (if (inrange new-row new-col arr n)
                  (recur arr n new-row new-col row-dir col-dir (inc start))
                  (let [[row-dir col-dir] (turn90 row-dir col-dir)
                      row (+ row row-dir)
                      col (+ col col-dir)]
                      (recur arr n row col row-dir col-dir (inc start))))))))

(defn spiral [n]
   (cond
      (zero? n) []
      (= 1 n) [[1]] 
      :else  (spr n)))
