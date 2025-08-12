(ns bracket-push)

(def opens-to-closes (zipmap "([{" ")]}"))
(def opens (set (keys opens-to-closes))) 

(defn- remove-bad [string] 
    (apply str (keep #{\[ \] \{ \} \( \)} string)))

(defn- brackets-match? [stack [f & r]]
     (if (nil? f)
         (empty? stack)
         (if (contains? opens f)
            (brackets-match? (concat [f] stack) r) 
            (if (= (opens-to-closes (first stack)) f)
                (brackets-match? (rest stack) r) 
                false)))) 

(defn valid? [string] 
   (->> string
        (remove-bad)
        (brackets-match? [])))
