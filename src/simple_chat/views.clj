(ns simple-chat.views
  (:require [hiccup.core :as hc]))

(defn css-tag [path]
  [:link {:rel "stylesheet" :href path}])

(defn js-tag [path]
  [:script {:src path}])

(defn layout [title & content]
  (hc/html
    [:html
     [:head
      [:title title]]
      (css-tag "/assets/styles/styles.css")
     [:body
      [:div.container content]
      (js-tag "/assets/js/app.js")
      ]]))

(defn index [{params :params}]
  (layout "hello"
          [:div#out "hey"]
          [:input#inp]))
