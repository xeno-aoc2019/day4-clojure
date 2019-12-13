(ns main)
(require '[clojure.string :as str])
(def input (first (str/split-lines (slurp "../input.txt"))))
(println input)
(def split (str/split input #"-"))
(def from (first split))
(def to (first (rest split)))

(defn char=< [a b] (>= 0 (compare a b)))

(def from-num (Integer. from))
(def to-num (Integer. to))

(defn should-keep [n]
  (def s (char-array (format "%s" n)))
  (def a (nth s 0))
  (def b (nth s 1))
  (def c (nth s 2))
  (def d (nth s 3))
  (def e (nth s 4))
  (def f (nth s 5))
  ; (println (format "%s %s %s %s %s %s" a b c d e f))

  ; (println (char=< a b) (char=< b c) (char=< c d) (char=< d e) (char=< e f))
  (def increasing (and (char=< a b) (char=< b c) (char=< c d) (char=< d e) (char=< e f)))
  (def has-pair (or (= a b) (= b c) (= c d) (= d e) (= e f)))
  (def has-clean-pair (or
                        (and (= a b) (not (= b c)))
                        (and (not (= a b)) (= b c) (not (= c d)))
                        (and (not (= b c)) (= c d) (not (= d e)))
                        (and (not (= c d)) (= d e) (not (= e f)))
                        (and (not (= d e)) (= e f))
                        ))

  (and increasing has-clean-pair))


(println "from=" from-num "to=" to-num)

(def filtered (filter should-keep (range from-num (+ to-num 1))))
; (doseq [n filtered]
;  (println n))

(println (count filtered))