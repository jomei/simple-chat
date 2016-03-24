(ns simple-chat.views
  (:require [hiccup.core :as hc]))

(defn layout [title & content]
  (hc/html
    [:html
     [:head
      [:title title]]
     [:body content]]))

(defn index [{params :params}]
  (layout "hello"
          [:div#out "hey"]
          [:textarea#inp]))
