(ns dp284-wandering-fingers.core
  (:require [clojure.string :as s]))

(def dict (->> (slurp "./enable1.txt")
               (#(s/split % #"\r\n"))
               (filter #(>= (count %) 5))))

(defn regex-from-word [word]
  (->> word
       (re-seq #"\w")
       (partition-by identity)
       (map first)
       (interpose ".*")
       (apply str)
       (#(str "^" % "$"))
       (re-pattern)))

(defn get-candidates [input]
  (let [regex (re-pattern (str "^" (first input) ".+" (last input) "$"))]
    (filter #(re-find regex %) dict)))

(defn get-matches [input]
  (let [candidates (get-candidates input)]
    (filter #(re-find (regex-from-word %) input) candidates)))

(defn -main []
  (for [x ["qwertyuytresdftyuioknn" "gijakjthoijerjidsdfnokg"]]
    (get-matches x)))
