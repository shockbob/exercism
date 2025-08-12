(ns wordy)
  (require '[clojure.string :as str])

(def op-map {"multiplied" * "divided" / "minus" - "plus" +})

(defn calc-result [words]
     (last (reduce (fn [[op res] word] 
                   (cond 
                      (nil? res) [op word] 
                      (nil? op) [word res] 
                      :else [nil (op res word)])) 
                 [nil nil] 
                 words)))

(defn isnumber [s]
  (empty? (str/replace s #"[0-9\-]" "")))

(defn postfix [words] 
   (contains? op-map (get words (dec (count words)))))
 
(defn prefix [words]
  (contains? op-map (get words 0)))

(defn evaluate [math] 
    (let [math (.substring math 0 (dec (count math)))
          words (vec (str/split math #" "))
          filtered-words (vec (remove #{"What" "is" "by" ""} words))]
      (if (not= (words 0) "What")
         (throw (IllegalArgumentException/new "syntax error"))
         (if (not (every? (fn [word] (or (isnumber word)(contains? op-map word))) filtered-words))
             (throw (IllegalArgumentException/new "unknown operation"))
             (if (or (even? (count filtered-words)) (postfix filtered-words) (prefix filtered-words))
                 (throw (IllegalArgumentException/new "syntax error"))     
                 (let [words (map (fn [word] (op-map word word)) filtered-words)
                       words (mapv (fn [word] (if (string? word) (Integer/parseInt word) word)) words)]
                      (calc-result words)))))))

