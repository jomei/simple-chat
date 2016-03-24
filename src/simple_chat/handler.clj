(ns simple-chat.handler
  (:require [compojure.core :refer :all]
            [compojure.route :as route]
            [org.httpkit.server :as httpkit]
            [simple-chat.views :as views]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (GET "/" [] views/index)
  (route/not-found "Not Found"))

(def app
  (wrap-defaults app-routes site-defaults))

(defn start []
  (def stop
    (httpkit/run-server #'app {:port 3000})))
