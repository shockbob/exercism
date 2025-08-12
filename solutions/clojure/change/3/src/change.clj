(ns change)

(defn dochange [pennies [f & r] output]
   (if (nil? f)
       output
       (let [dv (quot pennies f)
             rm (mod pennies f)]
           (dochange rm r (concat (repeat dv f) output )))))


(defn possible-answers [pennies coins]
   (->> coins 
         (sort-by -)
         (iterate rest)
         (take-while first)
         (map (fn [c] (dochange pennies c [])))))

(defn get-combos [coin pennies coins]
    (let [max (quot pennies coin)]
      (mapcat identity
        (for [num (range max)]
          (map (fn [ans] (concat (repeat  num coin) ans)) 
               (possible-answers (- pennies (* num coin)) coins))))))

(defn all-answers [pennies coins]
  (reduce (fn [coll coin]
            (let [combos (get-combos coin pennies (remove #{coin} coins))]
              (concat coll combos))) [] coins))

(defn get-valid-results [answers pennies]
   (filter (fn [answer] (= pennies (apply + answer))) answers))

(defn get-slow-results [pennies coins]
   (get-valid-results (all-answers pennies coins) pennies))

(defn get-coins [pennies coins] 
   (let [answers (possible-answers pennies coins)
         valid-answers (get-valid-results answers pennies)]
       (if (empty? valid-answers)     
         (first (sort-by count (get-slow-results pennies coins)))
         (first (sort-by count valid-answers)))))

(defn issue [pennies coins] 
    (cond 
        (zero? pennies) []
        (neg? pennies) (throw (IllegalArgumentException. "target can't be negative"))
        (not (some (fn [c] (<= c pennies)) coins)) (throw (IllegalArgumentException."can't make target with given coins"))
        (neg? pennies) (throw (IllegalArgumentException."cannot change"))
        :else (let [results (get-coins pennies coins)]
                (if (empty? results)
                     (throw (IllegalArgumentException."can't make target with given coins"))
                     results)))) 

