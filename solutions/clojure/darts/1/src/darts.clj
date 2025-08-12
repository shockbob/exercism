(ns darts)

(defn score
  "Calculates the score of a dart throw"
  [x y]
  (let [distsq (+ (* x x) (* y y))]
    (cond (> distsq 100) 0
          (> distsq 25) 1
          (> distsq 1) 5
          :else 10))
  )
