(ns saddle-points)
(defn get-column [matrix column]
    (map (fn [row] (row column)) matrix))

(defn saddle [matrix [row col]]
   (and (= ((matrix row) col) (apply max (matrix row)))
        (= ((matrix row) col) (apply min (get-column matrix col)))))

(defn saddle-points
  "Returns the saddle points of a matrix"
  [matrix]
  (into #{} (for [row (range 0 (count matrix)) 
                         col (range 0 (count (matrix row))) 
                         :when (saddle matrix [row col])] [(inc row) (inc col)])))
