(ns dq.server
  (:require [datomic.api :as d]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]

            [dq.nrepl]
            [dq.psql]))



(defn gen-resp []
  {:status 200 :body "Hello, world!"})

(defn respond-hello [request]
  (gen-resp))

(def routes
  (route/expand-routes
   #{["/health" :get respond-hello :route-name :health]}))

(defn create-server []
  (http/create-server
   {::http/routes routes
    ::http/type   :jetty
    ::http/host "0.0.0.0"
    ::http/port   8890}))

(defn start []
  (http/start (create-server)))


(comment


  (respond-hello 1)

  (defn gen-resp []
    {:status 200 :body "!Applause!"})
  
  )