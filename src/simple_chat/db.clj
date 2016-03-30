(ns simple-chat.db
  (:require [monger.core :as mg]))


(def db
  (let [conn (mg/connect)]
    (mg/get-db conn "clojure-chat")))

(defn exec [entity action params]
  (action db entity params))
