(ns simple-chat.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [org.httpkit.server :as httpkit]
            [simple-chat.views :as views]
            [simple-chat.chat :as chat]
            [simple-chat.account :as acc]
            [simple-chat.routes :as r]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [ring.util.response :as response]))

(defn handle-chat [{params :params :as req}]
  (httpkit/with-channel req ch
                        (chat/add-usr ch params)
                        (httpkit/on-receive ch (fn [msg] (chat/on-msg ch msg)))
                        (httpkit/on-close ch (fn [st] (chat/rm-usr ch)))
                        ))


(defn chat [{params :params}]
  {:body (views/chat params)
   :status 200})

(defn login [{params :params}]
  {:body (views/login)
   :status 200})

(defn auth [{params :params}]
  (let [login (:name params)
        pass (:password params)]
    (let [acc (acc/find-by "name" login)]
      (cond
        (nil? acc) (do (acc/create {:name login :password pass})
                        (response/redirect (str r/index login)))
        (= (:password acc) pass) (response/redirect (str r/index login))
        :else (response/not-found)))))


(defroutes app-routes
           (GET r/index [] #'login)
           (GET r/chat [] #'chat)
           (GET r/reg-socket [] #'handle-chat)
           (POST r/auth [] #'auth)
           (route/resources r/assets)
           (route/not-found r/not-found))

(def app
  (handler/site app-routes))

(defn start []
  (def stop
    (httpkit/run-server #'app {:port 3030})))
;; (stop)
(start)
