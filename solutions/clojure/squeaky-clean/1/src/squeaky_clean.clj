(ns squeaky-clean
  (:require [clojure.string :as str]))
(defn to-camel [[kf & kr] is-dash camel]
  (if (nil? kf)
      (apply str camel)
      (if (= \- kf)
          (to-camel kr true camel)
          (if is-dash
             (recur kr false (concat camel (str (Character/toUpperCase kf))))
             (recur kr false (concat camel (str kf)))))))

(defn clean-kebab [s] 
  (to-camel s false ""))

(defn clean-non-alpha [s] 
  (str/replace s #"[0-9]" "") )

(defn clean-non-letters [s]  
  (str/replace s #"[^a-zA-Z-_\u0000-\u1EFF]" "") )
(defn clean-lower-case-greek [s]  
  (str/replace s #"[\u03AC-\u03CE]" "") )
(defn clean-underscores [s] 
  (str/replace s " " "_") )
(defn clean-ctrls [s] 
  (str/replace s #"\p{Cc}" "CTRL"))
(defn clean  [s]
   (let [clean-all (comp  clean-kebab clean-underscores clean-ctrls clean-non-alpha clean-non-letters clean-lower-case-greek)]
     (clean-all s)))
