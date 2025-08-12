(ns anagram
   (:require [clojure.string :as str]))

(defn fixit [s] 
  (str/join (sort (str/lower-case s))))

(defn anagrams-for [word prospect-list] 
   (->> prospect-list
        (filter #(and (not= (.toLowerCase word)(.toLowerCase %)) 
                      (= (fixit word) (fixit %)))))) 

