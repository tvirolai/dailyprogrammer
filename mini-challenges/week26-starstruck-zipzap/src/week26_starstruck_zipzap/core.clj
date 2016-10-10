(ns week26-starstruck-zipzap.core
  (:require [clojure.string :as s]))

(defn starstruck [string]
  (->> string
       (re-seq #"\*{2,}")
       (s/join "")
       (count)))

(defn zipzap [string]
  (->> string 
       (re-seq #"zip|zap")
       (s/join "")
       (re-find #"zipzip")
       (boolean)
       (not)))

(defn -main []
  (do
    (println (starstruck "a*bc**def****g"))
    (println (zipzap "zipzapzipzzzaphzazipzazapzgzazapzapzapzapzipzapzapzap"))))
