(ns isogram)

(defn repeats
    ([coll] (repeats coll #{}))
    ([[f & r] s] 
     (if (nil? f) 
         false 
         (if (contains? s f) 
             true 
             (repeats r (conj s f))))))

(defn isogram? [iso] 
   (let [letters (filter (fn [c] (Character/isAlphabetic (int c))) (.toLowerCase iso))]
      (not (repeats letters))))
         
