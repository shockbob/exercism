(ns flatten-array)

(defn flatme [coll]
    (when-let [[f & r] (seq coll)]
      (if (sequential? f)
        (concat (flatme f) (flatme r))
        (cons f (flatme r)))))

(defn flatten [coll]
  (filter identity (flatme coll)))
