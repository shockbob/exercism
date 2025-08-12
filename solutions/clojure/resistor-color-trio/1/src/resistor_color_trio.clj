(ns resistor-color-trio (:require [clojure.string :as str]))

(def colors
  ["black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white"])

(defn zeros [n]
   (apply str (repeat n "0")))

(def color-map (zipmap colors (range) ))

(def zero-map (zipmap colors (map (fn [color] (zeros (color-map color))) colors)))

(def patterns-raw [{:ends (zeros 9)  :replace " giga"}{:ends (zeros 6) :replace " mega"}{:ends (zeros 3) :replace " kilo"}])

(def patterns (map 
                  (fn [m] (assoc m :re (re-pattern (str (m :ends) "$"))))
                  patterns-raw)) 

(defn replacer [s [f & r]]
  (if (nil? f)
     (str s " ")
     (if (str/ends-with? s (f :ends))
         (str/replace s (f :re) (f :replace))   
         (replacer s r))))

(defn replace-multiples [s]
  (replacer s patterns))

(defn resistor-label
  "Returns the resistor label based on the given color bands."
  [colors]
  (let [orig (str (color-map (colors 0)) (color-map (colors 1)) (zero-map (colors 2)))
        orig (if (str/starts-with? orig "0") (str/replace orig #"^0" "") orig)]
    (str (replace-multiples orig) "ohms")
         
  ))
