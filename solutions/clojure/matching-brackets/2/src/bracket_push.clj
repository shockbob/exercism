(ns bracket-push)

(def opp {\( \) \[ \] \{ \} })
(def opens (set (keys opp))) 

(defn- remove-bad [string] 
    (apply str (keep #{\[ \] \{ \} \( \)} string)))

(defn- brackets-match? [stack [f & r]]
     (if (nil? f)
         (empty? stack)
         (if (contains? opens f)
            (brackets-match? (concat [f] stack) r) 
            (if (= (opp (first stack)) f)
                (brackets-match? (rest stack) r) 
                false)))) 

(defn valid? [string] 
   (->> string
        (remove-bad)
        (brackets-match? [])))
