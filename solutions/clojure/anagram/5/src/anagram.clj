(ns anagram
   (:require [clojure.string :as str]))

(defn normalize [s] 
  (sort (str/lower-case s)))

(defn anagrams-for [word prospect-list] 
  (let [normalized-word (normalize word)]
   (->> prospect-list
        (filter #(and (not= (str/lower-case word)(str/lower-case %)) 
                      (= normalized-word (normalize %)))))))

