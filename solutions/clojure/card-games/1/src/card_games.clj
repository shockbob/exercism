(ns card-games)

(defn rounds
  "Takes the current round number and returns 
   a `list` with that round and the _next two_."
  [n]
  (list n (inc n) (+ 2 n)))

(defn concat-rounds 
  "Takes two lists and returns a single `list` 
   consisting of all the rounds in the first `list`, 
   followed by all the rounds in the second `list`"
  [l1 l2]
  (concat l1 l2))

(defn contains-round? 
  "Takes a list of rounds played and a round number.
   Returns `true` if the round is in the list, `false` if not."
  [l n]
    (not (nil? (some #{n} l)))
  )

(defn card-average
  "Returns the average value of a hand"
  [hand]
    (/ (float (apply + hand)) (count hand))
  )

(defn compute-median [hand]
   (let [ct (count hand)
         ctdiv2 (/ ct 2)]
       (if (even? ct)
           (/ (+ (nth hand ctdiv2)(nth hand (dec ctdiv2))) 2.0)
           (float (nth hand ctdiv2)))))

(defn approx-average?
  "Returns `true` if average is equal to either one of:
  - Take the average of the _first_ and _last_ number in the hand.
  - Using the median (middle card) of the hand."
  [hand]
    (let [computed-average (card-average hand)
          median (compute-median hand)
          first-last-avg (/ (+ (first hand) (last hand)) 2.0)]
    (or (= computed-average median) (= computed-average first-last-avg) )))

(defn average-even-odd?
  "Returns true if the average of the cards at even indexes 
   is the same as the average of the cards at odd indexes."
  [hand]
     (let [vechand (vec hand)
           evens (map vechand (range 0 (count hand) 2))
           odds (map vechand (range 1 (count hand) 2))]
       (= (card-average odds)(card-average evens))))


(defn maybe-double-last
  "If the last card is a Jack (11), doubles its value
   before returning the hand."
  [hand]
     (if (= 11 (last hand))
       (concat (butlast hand) [22])
       hand)
  )
