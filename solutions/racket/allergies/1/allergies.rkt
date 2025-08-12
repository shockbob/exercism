#lang racket

(provide list-allergies allergic-to?)

(define allergy-hash (hash "eggs" 1 "peanuts" 2 "shellfish" 4
                           "strawberries" 8 "tomatoes" 16 "chocolate" 32
                           "pollen" 64 "cats" 128))

(define reverse-allergy-hash
  (foldl
   (lambda (k h) (hash-set h (hash-ref allergy-hash k) k))
   (hash)
   (hash-keys allergy-hash)))

(define allergy-keys (sort (hash-keys reverse-allergy-hash) <))

(define (list-allergies score)
  (map (lambda (k) (hash-ref reverse-allergy-hash k))
       (filter
        (lambda (key) (allergic-to? (hash-ref reverse-allergy-hash key) score))
        allergy-keys)))
  
(define (allergic-to? str score)
   (not (zero? (bitwise-and score (hash-ref allergy-hash str)))))
