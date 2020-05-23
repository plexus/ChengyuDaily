(ns chengyu-daily.core
  (:require [reagent.core :as r]
            [reagent.react-native :as rn]))

(defn hello []
  [rn/view {:style {:flex 1 :align-items "center" :justify-content "center"}}
   [rn/text {:style {:font-size 50}} "不可得兼"]
   [rn/text {:style {:font-size 30}} "bù kě dé jiān"]
   [rn/text {:style {:font-size 25 :text-align "center"}} "“you can’t have both at the same time”"]])

(defn ^:export -main [& args]
  (r/as-element [hello]))
