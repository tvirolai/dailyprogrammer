(ns dp286-reverse-factorial.core
  (:gen-class))

(defn rev-factorial [no]
  (loop [n no divisor 2]
    (let [res (/ n divisor)]
      (if-not (and (integer? res) (> res 1))
        (case res
          1 (str no " = " divisor "!")
          (str no " NONE"))
        (recur res (inc divisor))))))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (println "Hello, World!"))
