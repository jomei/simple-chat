(defproject simple-chat "0.1.0-SNAPSHOT"
  :description "Clojure training"
  :url "https://github.com/jomei/simple-simple"
  :min-lein-version "2.0.0"
  :dependencies [[org.clojure/clojure "1.7.0"]
                 [compojure "1.4.0"]
                 [http-kit "2.1.19"]
                 [hiccup "1.0.5"]
                 [ring/ring-defaults "0.1.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler simple-chat.handler/app}
  :profiles
  {:dev {:dependencies [[javax.servlet/servlet-api "2.5"]
                        [ring/ring-mock "0.3.0"]]}})
