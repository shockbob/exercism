(ns list-ops)

(defn append [a [f & r]] 
  (if (nil? f) 
    a 
    (append (conj a f) r)))

(defn concatenate 
  "Given a vector of vectors, it combines all the vectors into one flattened vector"
  [colls]
  (reduce (fn [c e] (concat c e)) [] colls))

(defn select-if
  "Given a predicate and a vector, it returns the vector of all items for which predicate(item) is true"
  [pred [f & r]]
    (if (nil? f)
      '()
      (if (pred f)
        (conj (select-if pred r) f)
        (select-if pred r)))) 

(defn length 
  "Given a vector, it returns the number of items within it"
  [[f & r]]
  (if (nil? f)
     0
     (inc (length r))))

(defn apply-to-each 
  "Given a function and a vector, it returns the vector of the results of applying function(item) on all items"
  [func [f & r]]
    (if (nil? f)
        '()
        (conj (apply-to-each func r) (func f))))

(defn foldl 
  "Given a function, a vector, and initial accumulator, it folds (reduces) each item into the accumulator from the left"
  [f coll acc]
  (reduce f acc coll))

(defn foldr
  "Given a function, a vector, and an initial accumulator, it folds (reduces) each item into the accumulator from the right"
  [f coll acc]
  (reduce f acc (reverse coll)))

(defn reverse-order 
  "Given a vector, it returns a vector with all the original items, but in reverse order"
   [[f & r]]
    (if (nil? f) 
        []
        (conj (reverse-order r) f)))