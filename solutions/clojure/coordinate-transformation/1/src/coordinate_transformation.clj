(ns coordinate-transformation)

(defn translate2d 
  "Returns a function making use of a closure to
   perform a repeatable 2d translation of a coordinate pair."
  [dx dy]
     (fn [x y] [(+ x dx) (+ y dy)])
  )

(defn scale2d 
  "Returns a function making use of a closure to
   perform a repeatable 2d scale of a coordinate pair."
  [sx sy]
   (fn [x y] [(* x sx) (* y sy)])
  )

(defn compose-transform
  "Create a composition fun ction that returns a function that 
   combines two functions to perform a repeatable transformation."
  [f g]
    (fn [x y] (let [[x1 y1] (f x y)]
       (g x1 y1))))

(defn memoize-transform
  "Returns a function that memoizes the last result.
   If the arguments are the same as the last call,
   the memoized result is returned."
  [f]
    (let [memo (atom {:x nil :y nil :answer nil})]
    (fn [x y] 
                (if (= [x y] [(@memo :x)(@memo :y)])
                  (@memo :answer)
                  ( let [fxy (f x y)
                       val (swap! memo assoc :x x)
                       val (swap! memo assoc :y y)
                       val (swap! memo assoc :answer fxy)]
                      fxy)))))
  