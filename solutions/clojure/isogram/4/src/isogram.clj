(ns isogram)

(defn isogram? ([s] (isogram? s #{}))
   ([[f & r] letters]
      (if (nil? f)
          true
          (let [f (Character/toLowerCase f)]
              (if (and (Character/isAlphabetic (int f)) (contains? letters f))
                  false
                  (recur r (conj letters f)))))))
