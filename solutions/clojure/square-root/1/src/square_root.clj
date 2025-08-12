(ns square-root)

(defn square-root
  "Calculates a number's square root"
  [n]
  (if (= n 1)
      1
      (first (filter (fn [i] (= (* i i) n)) (range (quot n 2) 0 -1))
  )))
