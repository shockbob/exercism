(ns robot-name)

(def robomap (atom {}))

(defn get-robomap [] 
  (deref robomap))

(defn get-next-key []
   (if (empty? (get-robomap))
      0
      (inc (apply max (keys (get-robomap))))))

(defn gen-ch [] 
  (char (+ (int \A) (int (rand 26)))))

(defn gen-num [] 
  (+ 100 (int (rand 899))))

(defn gen-name [] 
   (let [name (str (gen-ch) (gen-ch) (gen-num))]
      (if (some (fn [old] (= name old)) (vals (get-robomap)))
         (gen-name)
         name)))

(defn reset-name [robot] 
   (swap! robomap assoc robot (gen-name)))

(defn robot [] 
   (let [key (get-next-key)
        xxx (reset-name key)]
     key))
 
(defn robot-name [robot] 
    ((get-robomap) robot))


