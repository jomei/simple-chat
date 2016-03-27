(ns simple-chat.handler
  (:require [compojure.core :refer :all]
            [compojure.handler :as handler]
            [compojure.route :as route]
            [org.httpkit.server :as httpkit]
            [simple-chat.views :as views]
            [simple-chat.chat :as chat]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defn chat [{params :params :as req}]
  (httpkit/with-channel req ch
                        (chat/add-usr ch params)
                        (httpkit/on-receive ch (fn [msg] (chat/on-msg ch msg)))
                        (httpkit/on-close ch (fn [st] (chat/rm-usr ch)))
                        ))

(defn index [{params :params}]
  {:body (views/index params)
   :status 200})

(defroutes app-routes
           (GET "/" [] #'index)
           (GET "/:name" [] #'index)
           (GET "/chat/:name" [] #'chat)
           (route/resources "/assets/")
           (route/not-found "Not Found"))

(def app
  (handler/site app-routes))

(defn start []
  (def stop
    (httpkit/run-server #'app {:port 3030})))
