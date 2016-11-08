(ns dp288-detecting-alliteration.core
  (:require [clojure.string :as s]))

(defn file->words [file] 
  (->> file
    (slurp)
    (re-seq #"\w+")
    (map s/lower-case)))

(def not-contains? (complement contains?))

(defn alliterations [inputfile stopwordfile]
  (let [stopwords (set (file->words stopwordfile))
        text (flatten (map #(s/split % #" ") (file->words inputfile)))]
    (->> text
         (filter #(not-contains? stopwords %))
         (partition-by #(first %))
         (filter #(> (count %) 1)))))

(defn -main
  []
  (for [x (alliterations "./data/input.txt" "./data/stopwords.txt")]
    (println (s/join " " x))))
