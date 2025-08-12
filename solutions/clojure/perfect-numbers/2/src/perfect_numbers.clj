(ns perfect-numbers)

(defn divs [n] 
    (distinct (reduce (fn [s x] 
              (if (zero? (mod n x)) 
                (concat s [(quot n x) (quot n (quot n x))]) 
                s)) 
            [] 
            (range 2 (inc (Math/sqrt n))))))

(defn sumdiv [n] 
   (inc (apply + (divs n))))

(defn classify [n] 
    (if (<= n 2)
      :deficient
      (if (< n 0)
         (throw (IllegalArgumentException. ""))
       (let [sd (sumdiv n)]
          (cond 
             (= sd n) :perfect
             (< sd n) :deficient
             :else :abundant)))))
