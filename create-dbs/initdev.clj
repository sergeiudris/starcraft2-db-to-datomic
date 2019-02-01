(ns initdev
  (:require [datomic.api :as d]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]))




(def db-uri-aligulac "datomic:free://datomicdbfree:4334/aligulac")
; (def db-uri-tutorial "datomic:dev://datomicdb:4334/tutorial")
; (def db-uri-movies "datomic:dev://datomicdb:4334/movies")
; (def db-uri-seattle "datomic:dev://datomicdb:4334/seattle")




(d/create-database db-uri-aligulac)
; (d/create-database db-uri-tutorial)
; (d/create-database db-uri-movies)
; (d/create-database db-uri-seattle)




(defn respond-hello [request]
  {:status 200 :body "Hello, world!"})

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

(defn -main []
  (prn "successfully created dbs")
  (start))