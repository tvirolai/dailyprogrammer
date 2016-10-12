(ns week26-letter-reveal.core)

(def word "HELLO")

(defn print-letters [letters]
  (let [w-seq (map str (seq word))]
    (->> w-seq
         (map #(if (.contains letters %) % "_"))
         (interpose " ")
         (apply str)
         (println))))

(defn -main []
  (loop [letter ""
        lives 7
        seen []]
    (print-letters seen)
    (when (and (> (count (set word)) (count (set seen)))
               (>= lives 0))
      (let [read-l (read-line)]
        (if (.contains word read-l)
            (recur read-l lives (conj seen read-l))
          (do
              (println (str "\"" read-l "\" is not in the word! " lives " lives left!"))
              (recur read-l (dec lives) seen)))))))
