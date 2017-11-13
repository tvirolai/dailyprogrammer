(ns dp340-first-recurring-character.core)

(defn first-recurring [string]
  (let [recurring (for [[id freq] (frequencies string)
                        :when (> freq 1)]
                    id)]
    (->> recurring first str)))

(defn char-index [string]
  (clojure.string/index-of string (first-recurring string)))

(def inputs
  '("IKEUNFUVFV" "PXLJOUDJVZGQHLBHGXIW" "*l1J?)yn%R[}9~1\"=k7]9;0[$"))
