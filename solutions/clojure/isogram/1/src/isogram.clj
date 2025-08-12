(ns isogram)

(defn isogram? [iso] 
   (let [letters (filter (fn [c] (Character/isAlphabetic (int c))) (.toLowerCase iso))
         freqmap (frequencies letters)]
       (every? (fn [[k v]] (= v 1)) freqmap)))
