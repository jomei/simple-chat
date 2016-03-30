(ns simple-chat.account
  (:require [simple-chat.db :as db]
            [monger.collection :as mc])
  (:import org.bson.types.ObjectId)) ;; todo: any entity should't know enything about db

(def entity "account")

(defn exec [action params]
  (db/exec entity action params))

(defn find-by [field value]
  (let [hash {(keyword field) value}]
    (exec mc/find-one-as-map hash)))

(defn create [user]
  (exec mc/insert-and-return (merge {:_id (ObjectId.)} user)))
