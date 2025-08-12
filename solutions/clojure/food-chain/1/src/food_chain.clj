(ns food-chain)
(require '[clojure.string :as str])
(def end-end  "I don't know why she swallowed the fly. Perhaps she'll die.")
(def verses
  [{:animal "fly"
    :end end-end}
   {:animal "spider"
    :special " that wriggled and jiggled and tickled inside her."
    :end "It wriggled and jiggled and tickled inside her."}
   {:animal "bird"
    :end "How absurd"}
   {:animal "cat"
    :end "Imagine that,"}
   {:animal "dog"
    :end "What a hog,"}
   {:animal "goat"
    :end "Just opened her throat and swallowed a goat!"}
   {:animal "cow"
    :end "I don't know how she swallowed a cow!"}
   {:animal "horse"
    :end "She's dead, of course!"}])

(defn inside [start]
  (let [animal1 (get (verses start) :animal)
        animal2 (get (verses (dec start)) :animal)
        special (get (verses (dec start)) :special ".")]
    (str "She swallowed the " animal1 " to catch the " animal2 special)))

(defn start [start]
  (let [animal (get (verses start) :animal)
        ending (get (verses start) :end)
        first-line (str "I know an old lady who swallowed a " animal ".")]
    (if (or (= (last ending) \.) (= (last ending) \!))
      (str first-line "\n" ending)
      (str first-line "\n" ending " to swallow a " animal "!"))))

(defn last-part-of-verse [verse]
  (map (fn [x] (inside x)) (range verse 0 -1)))

(defn whole-verse [verse]
  (let [first-part (str (start verse))
        last-part (if (= verse 7) [] (last-part-of-verse verse))
        ending (if (or (= verse 7) (zero? verse)) "" (str "\n" end-end))]
    (if (empty? last-part)
      (str first-part ending)
      (str first-part "\n" (str/join "\n" last-part) ending))))

(defn recite
  "Returns the lyrics of the song: 'I Know an Old Lady Who Swallowed a Fly.'"
  [start-verse end-verse]
  (let [verses (map whole-verse (range (dec start-verse) end-verse))]
    (str/join "\n\n" verses)))
