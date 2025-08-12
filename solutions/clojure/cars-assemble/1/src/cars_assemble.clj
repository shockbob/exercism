(ns cars-assemble)
(def success-rate {0 0.0 1 1.00 2 1.00 3 1.00 4 1.00 5 0.90 6 0.90 7 0.90 8 0.90 9 0.8 10 0.77})
(defn production-rate
  "Returns the assembly line's production rate per hour,
   taking into account its success rate"
  [speed]
  (* (success-rate speed) (* 221 speed)))

(defn working-items
  "Calculates how many working cars are produced per minute"
  [speed]
  (int (/ (production-rate speed) 60.0)))
