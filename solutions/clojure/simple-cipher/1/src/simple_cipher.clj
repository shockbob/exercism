(ns simple-cipher)
(def letters "abcdefghijklmnopqrstuvwxyz")
(def letters-map (zipmap letters (range)))
(defn rand-key
  "Returns a random key"
  []
  (apply str (map (fn [i] (nth letters (rand 26))) (range 100)))
  )

(defn encoded [p k]
  (let [offset (letters-map k)
        p-offset (letters-map p)]
    (nth letters (mod (+ offset p-offset) 26))))
(defn decoded [p k]
  (let [offset (- (letters-map k))
        p-offset (letters-map p)
        new-loc (+ offset p-offset)
        new-loc (if (neg? new-loc) (+ new-loc 26) new-loc) ]
    (nth letters (mod (+ offset p-offset) 26))))
(defn encode
  "Encodes text using the specified key"
  [key plaintext]
  (apply str (map (fn [i] (encoded (nth plaintext i)  
                                   (nth key (mod i (count key)))))                   (range (count plaintext)))))

(defn decode
  "Decodes text using the specified key"
  [key ciphertext]
  (apply str (map (fn [i] (decoded (nth ciphertext i) 
                                  (nth key (mod i (count key)))))                      (range (count ciphertext)))))
