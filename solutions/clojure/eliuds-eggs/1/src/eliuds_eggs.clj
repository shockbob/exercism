(ns eliuds-eggs)

(defn egg-count
  "Returns the number of 1 bits in the binary representation of the given number."
  [num]
  (if (< num 2)
    num
    (+ (mod num 2) (egg-count (quot num 2)))))
