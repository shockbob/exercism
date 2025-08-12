(ns connect
  (:gen-class))
(require '[clojure.string :as str])
(def EX -1)
(def OH -2)
(def OFF -5)
(def DOT -3)
(def SPACE -6)
(def piece-map {\X EX \O OH \. DOT \space SPACE})
(def human-readable {EX "X ", OH "O " SPACE "SP"})
(defn num-players [board]
  (let [flat-board (flatten board)
        onlypieces (filter (fn [pc] (or (= pc OH)(= pc EX))) flat-board)]
    (count onlypieces)))

(def neighbors [[-1 -1][1 1][-1 1][1 -1][0 -2] [0 2]]) 

(defn board-at [board [row col]]
  (if (not (< -1 row (count board)))
    OFF 
    (let [which-row (board row)]
      (if (not (< -1 col (count which-row)))
        OFF
        (which-row col)))))

(defn get-neighbors [board [row column]]
  (map (fn [[dr dc]] (let [new-row (+ row dr)
                           new-col (+ column dc)]
                           [[new-row new-col] (board-at board [new-row new-col])])) neighbors))

(defn get-valid-neighbors [board coord which]
    (let [neighbors (get-neighbors board coord)]
        (filter (fn [[rc cell]] (= cell which)) neighbors)))

(defn get-all-neighbors [board coords which]
    (vec (reduce 
      (fn [set-coords coord] (concat set-coords (get-valid-neighbors board coord which)))
      []  coords))) 

(defn get-em-row [row which]
   (for [col (range 0 (count row)) 
         :when (= which (get row col))] col ))

(defn get-em [board which]
   (for [row (range 0 (count board)) 
         col (get-em-row (board row) which)] [row col]))

(defn copy-row [row]  
   (vec (for [col (range 0 (count row))] (piece-map (get row col) DOT))))

(defn copy-board [board] 
    (vec (for [row (range 0 (count board))] (copy-row (board row))))) 

(defn get-first-col [board]
   (vec (for [row (range 0 (count board)) ]
         [[row row] (board-at board [row row])])))

(defn get-last-col [board]
   (vec (for [row (range 0 (count board)) ]
          (let [last-index (dec (count (board row)))]
         [[row last-index] (board-at board [row last-index])])))) 

(defn replace-in-board [board coords what]
    (reduce (fn [bd coord] (assoc-in bd coord what)) board coords))

(defn get-row [board index]
    (vec (for [col (range 0 (count (board index)) 2)]
           [[index col] (board-at board [index col])])))

(defn get-first-row [board]
   (get-row board 0))    

(defn get-last-row [board]
      (get-row board (dec (count board))))

(defn get-seeds [board which]
  (if (= which OH)
         (get-first-row board)
         (get-first-col board)))

(defn get-seeds-filtered [board which]
   (map first (filter (fn [[rc cell]] (= cell which)) (get-seeds board which))))

(defn fill-in-grid [board which generation boards]
    (let [seeds (get-em board (dec generation))
          neighbors (map first (get-all-neighbors board seeds which))
          board (replace-in-board board neighbors generation)]
          (if (empty? seeds)
               [board boards]
               (fill-in-grid board which (inc generation) (concat boards board))))) 
                 
(defn seed-board [board which]
   (let [seeds (get-seeds-filtered board which)]
       (replace-in-board board seeds 0)))

(defn find-winner [board-in]
    (let [board (seed-board board-in OH)
          [board boards] (fill-in-grid board OH 1 [])
          last-row (get-last-row board)
          any-there (some pos? (map last last-row))]
       (if any-there
           :O
           (let [board (seed-board board-in  EX)
              [board boards] (fill-in-grid board EX 1 [])
              last-col (get-last-col board)
              any-there (some pos? (map last last-col))]
              (if any-there 
                  :X
                  :no-winner)))))


(def player-map {"O" :O "X" :X})
(defn connect-winner
  "Returns the winner of the given connect board."
  [board]
  (let [copy (copy-board board)]
  (cond (zero? (num-players copy)) :no-winner
        (= (count board) 1)  (player-map (get-in board [0]) :no-winner)
        :else (find-winner (copy-board board)))))

