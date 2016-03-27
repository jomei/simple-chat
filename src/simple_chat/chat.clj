(ns simple-chat.chat
  (:require [org.httpkit.server :as httpkit]
            [hiccup.core :as hc]
            ))

(def users (atom {}))

(defn fmt-message [user msg]
  (hc/html
    [:div.message
     [:b (:name user)]
     [:div.msg
      [:pre msg]]]))

(defn add-usr [ch usr]
  (println usr)
  (swap! users assoc ch usr))

(defn rm-usr [ch]
  (swap! users dissoc ch))

(defn b-cast [msg]
  (doseq [[ch u] @users]
    (httpkit/send! ch msg)))

(defn on-msg [ch msg]
  (let [u (get @users ch)]
    (b-cast (fmt-message u msg))))

(comment
  (b-cast "Hi clients")
  (println @users))
