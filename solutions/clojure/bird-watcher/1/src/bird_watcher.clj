(ns bird-watcher)

(def last-week 
  [0 2 5 3 7 8 4])
(def birds-per-day
  4)

(defn today [birds]
  (last birds))

(defn inc-bird [birds]
  (concat (take 6 birds) [(inc (last birds))]))

(defn day-without-birds? [birds]
  (true? (some zero? birds)))

(defn n-days-count [birds n]
  (reduce + (take n birds)))

(defn busy-days [birds]
  (count (filter (fn [x] (>= x 5)) birds)))

(defn odd-week? [birds]
  (= birds [1 0 1 0 1 0 1]))
