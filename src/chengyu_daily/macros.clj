(ns chengyu-daily.macros
  (:require [clojure.java.io :as io]))

(defmacro load-chengyu []
  (list 'quote
        (for [line (line-seq (io/reader (io/resource "chengyu.txt")))
              :when (not (= "#" (first line)))]
          (re-find #"(\d+). (.{4}) \(([^)]+)\)[:\s]+(.*)" line))))
