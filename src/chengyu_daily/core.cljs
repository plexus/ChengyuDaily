(ns chengyu-daily.core
  (:require [reagent.core :as r]
            [reagent.react-native :as rn]
            [chengyu-daily.util :as util])
  (:require-macros [chengyu-daily.macros :refer [load-chengyu]]))

(def chengyus (load-chengyu))

(def chengyu (r/atom (rand-nth chengyus)))

(defn error-view [e info]
  [rn/view {:style {:flex 1 :align-items "center" :justify-content "center"}}
   [rn/text {:style {:font-size 50}} "Error"]
   [rn/text {:style {:font-size 30}} (pr-str e)]
   [rn/text {:style {:font-size 25 :text-align "center"}} (pr-str info)]])

(defn hello []
  (let [[_ _ cn py en] @chengyu
        new-chengyu! #(reset! chengyu (rand-nth chengyus))]
    [util/error-boundary
     [rn/view {:style {:flex 1 :align-items "center" :justify-content "center"}}
      [rn/text {:style {:font-size 50} :on-press new-chengyu!} cn]
      [rn/text {:style {:font-size 30} :on-press new-chengyu!} py]
      [rn/text {:style {:font-size 25 :padding 50 :text-align "center"}
                :on-press new-chengyu!} en]]
     error-view]))

(defn ^:export -main [& args]
  (r/as-element [hello]))
