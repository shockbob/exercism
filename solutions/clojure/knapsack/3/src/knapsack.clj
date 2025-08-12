(ns knapsack)

(defn all-overweight [maximum-weight items]
   (some (fn [item] (< maximum-weight (item :weight))) items ))

(defn get-iw-indices [items maximum-weight]
  (for [i (range 1 (inc (count items)))
                              w (range maximum-weight -1 -1)] [i w] ))
(defn calc-knapsack [maximum-weight items]
  (let [dp {}
        iw-indices (get-iw-indices items maximum-weight)]
       (loop [dp dp [f & r] iw-indices]
         (if (nil? f)
           dp
           (let [[i w] f
                 item-weight (get-in items [(dec i) :weight])]
              (if (<= item-weight w)
                  (let [key (- w (get-in items [(dec i) :weight]))
                        value (+ (dp key 0) (get-in items [(dec i) :value]))
                        value (max value (dp w 0))]
                     (recur (assoc dp w value) r))
                (recur dp r)))))))

(defn maximum-value
  "Calculates the maximum value that can be packed."
  [maximum-weight items]
   (cond (empty? items) 0
        (all-overweight maximum-weight items) 0
        :else ((calc-knapsack maximum-weight items) maximum-weight)))