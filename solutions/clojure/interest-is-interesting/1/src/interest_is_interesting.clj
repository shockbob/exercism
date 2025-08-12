(ns interest-is-interesting)

(defn interest-rate
  "TODO: add docstring"
  [balance]
     (cond 
       (< balance 0) -3.213
       (< balance 1000) 0.5 
       (and (>= balance 1000)(< balance 5000)) 1.621
       :else 2.475))

(defn annual-yield
  "TODO: add docstring"
  [balance]
  (* balance (/ (interest-rate balance) 100.0M))
  )

(defn annual-balance-update
  "TODO: add docstring"
  [balance]
  (bigdec (+ balance (annual-yield balance)))
  )

(defn amount-to-donate
  "TODO: add docstring"
  [balance tax-free-percentage]
  (max 0 (int (* 2 balance (/ tax-free-percentage 100.0))))
  )