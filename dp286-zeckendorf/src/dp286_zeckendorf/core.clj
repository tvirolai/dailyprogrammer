(ns dp286-zeckendorf.core
  (:require [clojure.string :as s]))

(def fib (lazy-cat [1 1] (map + (rest fib) fib)))

(defn fibo [limit]
  (->> (take 20 fib)
    (filterv #(> limit %))
    (reverse)))

(defn is-fibonacci-no? [n]
  (boolean (some #(= % n) (fibo (inc n)))))

(defn zeckendorf [n]
  (if (is-fibonacci-no? n) [n]
    (loop [res (conj [] (first (fibo n)))]
      (let [sum (reduce + res)
            nextterm (->> (last res)
                          (fibo)
                          (filter #(>= n (+ % sum)))
                          (first))]
        (if (= n sum)
          res
          (recur (conj res nextterm)))))))

(defn -main
  []
  (for [x [5 120 34 88 90 320]]
    (println (str x " = " (s/join " + " (zeckendorf x))))))
