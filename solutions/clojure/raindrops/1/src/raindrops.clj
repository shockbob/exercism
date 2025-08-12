(ns raindrops)

(defn convert [num] 
    (let [pling (if (zero? (mod num 3)) "Pling" "")
          plang (if (zero? (mod num 5)) "Plang" "")
          plong (if (zero? (mod num 7)) "Plong" "")
          output (str pling plang plong)]
          (if (empty? output)
              (str num)
              output)))
