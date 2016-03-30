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

(defn chat [{params :params}]
  (layout "Enjoy"
          [:div#out "hey"]
          [:input#inp]))

(defn login []
  (layout "Simple Clojure Chat"
          [:form {:method "post" :action "/auth"}
              [:input {:type "text" :name "name" :placeholder "Login"}]
              [:input {:type "text" :name "password" :placeholder "Password"}]
              [:label "Avatar" [:input {:type "file" :name "avatar"}]]
              [:input {:type "submit" :value "Sign In"}]
           ]))
