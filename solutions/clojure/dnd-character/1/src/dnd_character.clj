(ns dnd-character)

(defn score-modifier
  "Calculates a score's modifier"
  [score]
   (int (Math/floor (/ (- score 10) 2.0)))
  )

(defn rand-ability
  "Generates a random ability"
  []
  (let [dice (map inc (take 4 (repeatedly #(rand-int 6))))
        result (- (apply + dice) (apply min dice))]
    result
        
  ))

(defn rand-character
  "Generates a random character"
  []
  (let [constitution (rand-ability)]
        {:dexterity (rand-ability) :strength (rand-ability) :charisma (rand-ability) 
         :wisdom (rand-ability) :intelligence (rand-ability) :constitution constitution    
         :hitpoints (- constitution (score-modifier constitution))}
  ))
