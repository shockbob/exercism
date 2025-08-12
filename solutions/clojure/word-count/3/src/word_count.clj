(ns word-count
   (:require [clojure.string :as str]))
(defn freq [c] (reduce (fn [m e] (assoc m e (inc (m e 0)))) {} c))

(defn fix-quotes [s] 
    (let [s (str/replace s #"^'", "")
          s (str/replace s #"'$", "")]
    s))
(defn word-count [s] 
    (let [s (.toLowerCase  s)
          s (fix-quotes s)
          s (str/replace s #"[^a-z0-9 ,']" "")
          words (map (partial apply str) (str/split s #"[, \n]"))
          words (map fix-quotes words)
          words (remove #{""} words)]
    (freq words)))  
