(ns dp223-garland-words.core)

(defn garland
  ([word] (garland word 0 1))
  ([word found i]
   (if (= i (count word))
     found
     (if (= (take i word) (take-last i word))
       (recur word i (inc i))
       (recur word found (inc i))))))

(defn load-words [filename]
  (line-seq (clojure.java.io/reader filename)))
