(ns matrix)
(require '[clojure.string :as str])

(defn num-row [row]
   (mapv (fn [i] (Integer. i)) (str/split row #" ")))

(defn get-num-rows [s]
 (let [rows (str/split s #"\n")
        num-rows (map num-row rows)]  
   num-rows))

(defn get-row
  "Returns the i-th row of the matrix s"
  [s i]
    (nth (get-num-rows s) (dec i)))

(defn get-column
  "Returns the i-th column of the matrix s"
  [s i]
    (map (fn [r] (nth r (dec i))) (get-num-rows s)))