

(ns prime-factors)

(defn prime? [n]
   (nil? (some
      (fn [x] (zero? (mod n x)))
      (range 2 (inc (int (Math/sqrt n)))))))

(defn nextprime [n]
    (first (filter prime? (iterate inc (inc  n)))))

(defn of
  ([n] (of n 2 []))
  ([n p c]
     (cond (= n 1) []
           (< n 4) [n]
           :else (if (>= 1 n)
                      c
                   (if (zero? (mod n p))
                      (let [f1 (quot n p)
                            f2 (quot n f1)
                            part1 (if (prime? f1) [f1] (of f1 2 c))
                            part2 (if (prime? f2) [f2] (of f2 2 c))]
                          (concat part2 part1))
                      (recur n (nextprime p) c))))))
