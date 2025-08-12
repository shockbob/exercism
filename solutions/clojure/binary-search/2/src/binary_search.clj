(ns binary-search)

(defn search-for ([x coll]  (search-for x coll 0 (dec (count coll))))
   ([x coll mn mx]
      (let [coll (vec coll)
            mid (quot (+ mn mx) 2)]
   (cond
      (empty? coll) -1
      (= x (coll mid)) mid
      (<= mx mn) -1
      (< x (coll mid)) (search-for x coll mn (dec mid)) 
      :else  (search-for x coll (inc mid) mx)))))

(defn middle [coll] 
   (quot (count coll) 2))
