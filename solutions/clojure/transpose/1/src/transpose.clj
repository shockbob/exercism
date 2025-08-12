(ns transpose
    (:require  [clojure.string :as str]))

(defn valid-char [str i]
  (if (< -1 i (count str))
    (nth str i)
    "#"))


(defn get-at [strs row col]
  (valid-char (strs row) col))

(defn get-col [strs col]
     (apply str (for [row (range (count strs)) ]
                     (get-at strs row col))))
(defn transpose
  "Given a string, it returns the transposed version"
  [s]
  (if (= s "")
    ""
  (let [strs (str/split s #"\n")
        max-col-size (apply max (map count strs))
        column-range (range max-col-size)
        columns (map (fn [col] (get-col strs col)) column-range)
        columns (map (fn [col] (str/replace col #"#+$" "")) columns)
        columns (map (fn [col] (str/replace col #"#" " ")) columns)]
      (str/trimr (str/join "\n" columns )))))