(ns resistor-color)

(def colors
  ["black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white"]
  )
(def colormap (zipmap colors (range) ))

(defn color-code
  "Returns the numerical value associated with the given color"
  [color]
  (colormap color)
  )
