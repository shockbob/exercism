

(ns largest-series-product
    (:require [clojure.string :as str]))

(defn calc-result [size nums]
   (let [nums (map (fn [c] (Integer/parseInt (str c))) nums)
         parts (partition size 1 nums)
         mults (map (partial apply *)  parts)
         answer (apply max mults)] 
      answer))

(defn illegals? [nums]
   (not (empty? (str/replace nums #"[0-9]" ""))))

(defn largest-product [size nums]
  (let [ct (count nums)]
   (cond (and (not (zero? size)) (zero? ct)) (throw (IllegalArgumentException/new  "span must not exceed string length" ))
         (> size ct)  (throw (IllegalArgumentException/new  "span must not exceed string length" ))
         (illegals? nums) (throw (IllegalArgumentException/new  "digits input must only contain digits" ))
         (neg? size) (throw (IllegalArgumentException/new  "span must not be negative"))
         (empty? nums) (throw (Throwable. ""))
         (zero? size) 1 

         :else (calc-result size nums)))) 
