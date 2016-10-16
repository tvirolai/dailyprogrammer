(ns dp287-mathagrams.core
  (:require [clojure.math.combinatorics :as combo]
            [clojure.string :as s]))

(defn numbers-in-string [string]
  (->> string
       (s/join "")
       (re-seq #"\d")
       (map #(Integer/parseInt %))))

(defn missing-numbers 
  "Returns a list of numbers that fill in the empty spaces"
  [string]
  (let [input (s/join "" string)
        input-nos (numbers-in-string input)
        gram-length (case (count input)
                      9 1
                      18 2
                      27 3)
        number-pool (flatten (repeat gram-length (range 1 10)))
        both (sort (flatten (conj number-pool input-nos)))]
    (->> both
         (partition-by identity)
         (map #(repeat (- (* 2 gram-length) (count %)) (first %)))
         (flatten))))

(defn combine
  "This function takes an input sequence and a list of filler numbers  and returns a vector where the numbers fill the x's"
  [input numbers]
  (loop [inp (seq (s/join input))
         nos numbers
         newseq []]
    (if (= (count inp) 0) newseq
      (let [n (Character/digit (first inp) 10)]
        (if (> n 0)
          (recur (rest inp) nos (conj newseq n))
          (recur (rest inp) (rest nos) (conj newseq (first nos))))))))

(defn vec-to-numberseq 
  "Transforms sequence like [1 2 3 4 5 5 6 7 9] -> (123 456 789)" 
  [numbers]
  (->> numbers
       (partition 3)
       (map #(apply str %))
       (map read-string)))

(defn parts
  "Determine if input is simple, double or triple mathagram
  Return vector of type [headlength taillegth]" 
  [numberseq]
  (let [tail-len (case (count numberseq)
               3 1
               6 2
               9 4)
        head-len (- (count numberseq) tail-len)]
    [(take head-len numberseq) (take-last tail-len numberseq)]))

(defn correct? 
  "Takes a sequence of numbers and evaluates its correctness" 
  [vect]
  (let [parts (parts (vec-to-numberseq vect))
        solution (reduce + (last parts))
        first-sums (reduce + (first parts))]
    (= first-sums solution)))

(defn solve [input]
  (loop [nos (missing-numbers input)]
    (let [se (combine input nos)]
      (if (correct? se)
        se
        (recur (shuffle nos))))))

(defn format-result [vect]
  (letfn [(join-part [x] (s/join " + " (map str x)))]
    (let [parts (parts (vec-to-numberseq vect))
          first-part (join-part (first parts))
          last-part (join-part (last parts))]
      (str first-part " = " last-part))))

(def examples [["1xx" "xxx" "468"]
               ["xxx" "x81" "9x4"]
               ["xxx" "5x1" "86x"]
               ["xxx" "39x" "x75"]
               ["xxx" "xxx" "23x" "571" "xxx" "x82"]
               ["xxx" "xxx" "xx7" "212" "xxx" "889"]
               ["xxx" "xxx" "1x6" "142" "xxx" "553"]
               ["xxx" "xxx" "xxx" "4x1" "689" "xxx" "xxx" "x5x" "957"]
               ["xxx" "xxx" "xxx" "64x" "581" "xxx" "xxx" "xx2" "623"]
               ["xxx" "xxx" "xxx" "x81" "759" "xxx" "xxx" "8xx" "462"]
               ["xxx" "xxx" "xxx" "6x3" "299" "xxx" "xxx" "x8x" "423"]
               ["xxx" "xxx" "xxx" "58x" "561" "xxx" "xxx" "xx7" "993"]])

(defn -main []
  (for [x examples]
    (println (format-result (solve x)))))
