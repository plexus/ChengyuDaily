(ns chengyu-daily.util
  (:require [reagent.core :as reagent]
            [clojure.string :as str]))

(defn error-boundary
  "Set up a React error boundary. Takes a component (function) and a view (hiccup
  form). In normal circumstances the view gets rendered. If rendering the view
  causes an error, then the error component is rendered instead, passing it the
  Error and info object."
  [view error-comp]
  (let [error (reagent/atom nil)]
    (reagent/create-class {:component-did-catch
                           (fn [this e info]
                             (reset! error [e info])
                             (let [trace (some-> info
                                                 (.-componentStack)
                                                 (str/split "\n")
                                                 (->> (map str/trim)
                                                      (remove empty?)
                                                      (str/join "\n")))]
                               (set! (.-componentStack info) trace)))

                           :reagent-render
                           (fn [view error-comp]
                             (if-let [[e info] @error]
                               [error-comp e info]
                               view))})))
