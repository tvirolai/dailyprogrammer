(ns dp283-anagram-detector.core
  (:require [clojure.string :as s]))

(defn is-anagram? [entry]
  (letfn [(to-seq [x] 
            (->> (re-seq #"\w+" x)
                 (s/join)
                 (s/lower-case)
                 (seq)
                 (sort)))]
    (= (to-seq (first entry)) (to-seq (last entry)))))

(defn -main []
  (for [x [["wisdom" "mid sow"] 
           ["Seth Rogan" "Gathers No"]
           ["Reddit" "Eat Dirt"]
           ["Schoolmaster" "The classroom"] 
           ["Astronomers" "Moon starer"] 
           ["Vacation Times" "I'm Not as Active"] 
           ["Dormitory" "Dirty Rooms"]]]
    (let [answer (if (is-anagram? x) " is" " is NOT")]
      (println 
        (str "\"" (first x)  
             "\"" answer " an anagram of \"" 
             last x) "\""))))
