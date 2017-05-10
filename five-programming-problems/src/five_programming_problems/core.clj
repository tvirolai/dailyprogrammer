(ns five-programming-problems.core)

; Problem 1
; Write three functions that compute the sum of the numbers in a given list using a for-loop, a while-loop, and recursion.

(defn prob-1-a
  ([l] (prob-1-a l 0))
  ([l sum] 
   (if (empty? l)
     sum
     (recur (rest l) (+ sum (first l))))))

(defn prob-1-b [l]
  (reduce + l))

; Problem 2
; Write a function that combines two lists by alternatingly taking elements. 
; For example: given the two lists [a, b, c] and [1, 2, 3], the function should return [a, 1, b, 2, c, 3].

(defn prob-2 [l1 l2]
  (interleave l1 l2))

; Problem 3
; Write a function that computes the list of the first 100 Fibonacci numbers. By definition, the first two numbers in the Fibonacci sequence are 0 and 1, and each subsequent number is the sum of the previous two. As an example, here are the first 10 Fibonnaci numbers: 0, 1, 1, 2, 3, 5, 8, 13, 21, and 34.

(defn prob-3 []
  (loop [fib [(biginteger 0) (biginteger 1)]]
    (if (= 100 (count fib))
      fib
      (recur
        (conj fib
              (reduce + (take-last 2 fib)))))))

; Problem 4
; Write a function that given a list of non negative integers, arranges them such that they form the largest possible number. For example, given [50, 2, 1, 9], the largest formed number is 95021.

(defn prob-4 [l]
  (->> l
       (map str)
       (sort-by first)
       (reverse)
       (clojure.string/join "")
       (Integer/parseInt)))
