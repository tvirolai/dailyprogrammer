(ns dp287-kaprekars-routine.core
  (:require [clojure.string :as s]))

(defn largest-digit [n]
  (->> n
       (str)
       (sort)
       (last)
       (#(Character/digit % 10))))

(defn padding [n]
  (let [se (seq (str n))]
    (s/join "" (concat (repeat (- 4 (count se)) \0) se))))

(defn sort-digits [n desc]
  (->> n
       (padding)
       (sort)
       (#(if desc (reverse %) %))
       (s/join "")
       (#(s/replace % #"^0+" ""))
       (read-string)))

(defn kaprekar [n]
  (when (< 1 (count (set (str n))))
    (loop [res n i 0]
      (if (= 6174 res)
        i
        (recur (- (sort-digits res true) (sort-digits res false)) (inc i))))))

(defn -main []
  (for [x [6589 5455 6174]]
    (println (kaprekar x))))
