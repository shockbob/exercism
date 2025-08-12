(ns knapsack)

(defn all-overweight [maximum-weight items]
   (some (fn [item] (< maximum-weight (item :weight))) items ))


(defn calc-knapsack [maximum-weight items]
  (let [dp-atom  (atom {})
        calc     (doall (for [i (range 1 (inc (count items)))
                              w (range maximum-weight -1 -1)]
                          (let [item-weight (get-in items [(dec i) :weight])]
                          (if (<= item-weight w)
                            (let [dp (deref dp-atom)
                                  key (- w (get-in items [(dec i) :weight]))
                                  value (+ (dp key 0) (get-in items [(dec i) :value]))
                                  value (max value (dp w 0))]
                             (swap! dp-atom assoc w value))))))]
    ((deref dp-atom) maximum-weight)))

(defn maximum-value
  "Calculates the maximum value that can be packed."
  [maximum-weight items]
   (cond (empty? items) 0
        (all-overweight maximum-weight items) 0
        :else (calc-knapsack maximum-weight items)))