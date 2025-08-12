(ns triangle)

(defn lengths-ok [a b c]
         (and (> (+ a b) c) (> (+ b c) a) (> (+ a c) b)))

(defn valid? [a b c]
    (and (not (or (zero? a) (zero? b) (zero? c))) 
              (lengths-ok a b c)))

(defn unique-sides [a b c] 
    (count (set [a b c])))

(defn equilateral? [a b c]
    (and (valid? a b c) (= 1 (unique-sides a b c))))

(defn isosceles? [a b c]
    (and (valid? a b c) (<= 1 (unique-sides a b c) 2)))

(defn scalene? [a b c]
    (and (valid? a b c)  (= 3 (unique-sides a b c))))
