(defpackage :eliuds-eggs
  (:use :cl)
  (:export :egg-count))

(in-package :eliuds-eggs)

(defun egg-count (number)
  (if (= 0 number)
      0
     (+ (mod number 2) (egg-count (floor number 2)))))
