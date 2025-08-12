(ns yacht)

(def count-map (zipmap ["ones" "twos" "threes" "fours" "fives" "sixes"] (range 1 7) ))

(defn get-pattern [dice]
  (sort (vals (frequencies dice))))

(defn get-matching-freq [dice freq]
  (map first (filter (fn [[k v]] (= v freq)) (frequencies dice))))

(defn score
  "Given five dice and a category, it calculates the score of the dice for that category"
  [dice category]
  (let [pattern (get-pattern dice)]
  (cond (= category "yacht")
            (if (= pattern [5])
                50
                0)
        (not= nil (count-map category))
            (let [value (count-map category)]
             (* value (count (filter (fn [x] (= x value)) dice))))
        (= category "full house")
            (if (= pattern [2 3])
                (apply + dice)
                0)
        (= category "four of a kind")
                (if (= pattern [1 4])
                   (* 4 (apply + (get-matching-freq dice 4)))
                   (if (= pattern [5])
                     (* 4 (apply + (get-matching-freq dice 5)))
                     0))
        (= category "little straight")
           (if (= (set dice) #{1 2 3 4 5})
               30
               0)
        (= category "big straight")
           (if (= (set dice) #{2 3 4 5 6})
               30
               0)
        (= category "choice")
           (apply + dice)
  )))
