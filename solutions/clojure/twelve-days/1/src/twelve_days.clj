(ns twelve-days)
(require '[clojure.string :as str])
(def lyrics "twelve Drummers Drumming, eleven Pipers Piping, ten Lords-a-Leaping, nine Ladies Dancing, eight Maids-a-Milking, seven Swans-a-Swimming, six Geese-a-Laying, five Gold Rings, four Calling Birds, three French Hens, two Turtle Doves, a Partridge in a Pear Tree")

(def gifts (str/split lyrics #", "))
(def gift-map (zipmap (range) (reverse gifts)))
(def ordinals ["first","second","third","fourth","fifth","sixth","seventh",
               "eighth","ninth","tenth","eleventh","twelfth"])
(defn run [start]
  (if (zero? start)
    (str (gift-map 0) ".")
    (str  (str/join ", " (map gift-map (range start, 0, -1)))
        ", and " (gift-map 0) ".")))

(defn verse [n]
   (str "On the " (ordinals n) " day of Christmas my true love gave to me: " (run n)))

(defn recite
  "Returns the lyrics of the song: 'The Twelve Days of Christmas.'"
  [start-verse end-verse]
   (str/join "\n" (map verse (range (dec start-verse) end-verse)))
  )
