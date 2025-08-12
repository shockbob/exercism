(ns resistor-color-duo)
(def colors
  ["black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white"]
  )
(def colormap (zipmap colors (range) ))

(defn resistor-value
  "Returns the resistor value based on the given colors"
  [colors]
   (let [v0 (colormap (nth colors 0))
         v1 (colormap (nth colors 1))]
     (+ (* v0 10) v1)))
