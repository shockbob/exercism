(ns bob
    (:require [clojure.string :as str]))

(defn all-spaces? [s]
  (zero? (count (str/replace s #"[\s]" ""))))

(defn response-for [s] 
   (let [all-spaces (= [\space] (distinct (vec s)))
         has-uppers (some (fn [c] (<= (int \A) (int c) (int \Z))) s) 
         all-uppers (and has-uppers (= (.toUpperCase s) s))
         question (= (last (str/trim s)) \?)
         exclaim (= (last s) \!) ]
   (cond
      all-spaces "Fine. Be that way!" 
      (and all-uppers question) "Calm down, I know what I'm doing!" 
      question "Sure."
      (and all-uppers exclaim) "Whoa, chill out!"
      all-uppers "Whoa, chill out!" 
      (all-spaces? s) "Fine. Be that way!" 
      :else "Whatever.")))
