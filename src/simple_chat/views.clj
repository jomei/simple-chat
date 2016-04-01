(ns simple-chat.views
  (:require [hiccup.core :as hc]))

(defn css-tag [path]
  [:link {:rel "stylesheet" :href path}])

(defn js-tag [path]
  [:script {:src path}])


(defn js-path [file]
  (str "/assets/js/" file))

(defn css-path [file]
  (str "/assets/styles/" file))

(defn layout [title & content]
  (hc/html
    [:html
     [:head
        [:title title]]
        (css-tag (css-path "styles.css"))
     [:body
        [:div.container content]
        (js-tag (js-path "app.js"))
      ]]))

(defn chat [{params :params}]
  (layout "Enjoy"
          [:div#out "Simple Clojure Chat"]
          [:input#inp]
          [:button#send.pull-right "Send"]
          [:div.clear]
          ))

(defn login []
  (layout "Simple Clojure Chat"
          [:form.login {:method "post" :action "/auth"}
              [:input {:type "text" :name "name" :placeholder "Login"}]
              [:input {:type "text" :name "password" :placeholder "Password"}]
              [:label "Avatar" [:input {:type "file" :name "avatar"}]]
              [:input.pull-right {:type "submit" :value "Sign In"}]
           ]))
