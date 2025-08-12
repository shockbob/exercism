(ns state-of-tic-tac-toe)

(defn gamestate
  "Returns the gamestate of a tic-tac-toe board."
  [board]
     (let [vecboard (vec (apply concat board))
           count-o (count (filter (fn [c] (= c \O)) vecboard))
           count-x (count (filter (fn [c] (= c \X)) vecboard))
           occupied (+ count-o count-x)
           solutions [0 1 2
                      3 4 5 
                      6 7 8 
                      0 3 6 
                      1 4 7 
                      2 5 8 
                      0 4 8 
                      2 4 6]
           board-sols (partition 3 (map vecboard solutions))
           xs (filter (fn [three] (= three [\X \X \X])) board-sols)
           os (filter (fn [three] (= three [\O \O \O])) board-sols)]
          (if (and (not-empty xs)(not-empty os))
             (throw (IllegalArgumentException. "Impossible board: game should have ended after the game was won"))
          (if (or (not-empty xs)(not-empty os))
            :win
            (if (and (= occupied 9)(= count-x 5)(= count-o 4))
              :draw
              (if (> (- count-x count-o) 1)
                  (throw (IllegalArgumentException. "Wrong turn order: X went twice"))
                  (if (> count-o count-x)
                      (throw (IllegalArgumentException. "Wrong turn order: O started"))
                  :ongoing)
       )))
  )))
