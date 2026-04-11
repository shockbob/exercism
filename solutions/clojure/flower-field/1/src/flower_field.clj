(ns flower-field)
(defn inrange [start end value] 
  (and (>= value start) (< value end)))

(def neighbors
    (for [rd [-1 0 1] cd (if (zero? rd) [-1 1] [-1 0 1])]          [rd cd]))

(defn get-in-grid [grid row col] 
    (if (and (inrange 0 (count grid) row) 
             (inrange 0 (count (grid row)) col)) 
       (get-in grid [row col])))

(defn get-flower-count [grid row col]
   (if (= \* (get-in grid [row col]))
      "*"
   (let [surrounds (map (fn [[rd cd]] (get-in-grid grid (+ row rd) (+ col cd))) neighbors)
         flower-count (count (filter (fn [v] (= v \*) ) surrounds))
         flower-str (if (zero? flower-count) " " (str flower-count))]
     flower-str)))

(defn draw
  "Fills in the number of adjacent flowers for each empty square in the board."
  [board]
  (let [board (clojure.string/split board #"\n")
        result (for [row (range (count board))] 
                 (for [ col (range (count (board row)))] (get-flower-count board row col)))
        result (clojure.string/join "\n" (map (partial apply str) result))]
    result)
  )
