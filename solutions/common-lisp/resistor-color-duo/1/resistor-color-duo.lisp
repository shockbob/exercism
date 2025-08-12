(defpackage :resistor-color-duo
  (:use :cl)
  (:export :value))

(in-package :resistor-color-duo)

(defvar colors-coll '("black" "brown" "red" "orange" "yellow" "green" "blue" "violet" "grey" "white") )

(defun color-code (color) (position color (colors) :test #'string=))

(defun colors () colors-coll)

(defun value (colors)
  (+ (* 10 (color-code (first colors))) (color-code (second colors))))
