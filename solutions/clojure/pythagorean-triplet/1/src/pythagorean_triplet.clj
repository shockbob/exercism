(ns pythagorean-triplet)
(defn valid [a b c] (and (> (+ a b ) c)
                         (> (+ c b) a)
                         (> (+ a c) b)))
(defn find-pythagorean-triplets
  "Given an integer N, it returns all Pythagorean triplets for which a + b + c = N"
  [N]
  (filter identity 
          (for [a (range 1 (quot N 2)) 
                b (range (inc a) (quot N 2))]
                    (let [c (- N (+ a b))] 
                      (if (valid a b c)
                        (let [ab2 (+ (* a a)(* b b))]
                        (if (= ab2 (* c c))
                            [a b c])))))))
