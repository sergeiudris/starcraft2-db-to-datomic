(ns dq.dq
  (:require [datomic.api :as d]
            [io.pedestal.http :as http]
            [io.pedestal.http.route :as route]
            
            [dq.nrepl]
            ))

(def  db-uri "datomic:free://datomicdbfree:4334/hello")

(d/create-database db-uri)
(def conn (d/connect db-uri))
(def db (d/db conn))

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
  (prn conn)
  (dq.nrepl/-main)
  (start))



(comment 
  
  (def  uri "datomic:free://localhost:4334/git")

  (d/create-database uri)

  (def conn (d/connect uri))

  conn

  (def db (d/db conn))

  (d/q '[:find ?e :in $] db)

; first query!
  (map first
       (d/q '[:find ?repo
              :where [?e :repo/uri ?repo]]
            db))

  (map first
       (d/q '[:find ?ns
              :where
              [?e :clj/ns ?n]
              [?n :code/name ?ns]]
            db))

  (reduce (fn [agg [o d]]
            (update-in agg [o] (fnil conj []) d))
          {}
          (d/q '[:find ?op ?def
                 :where
                 [?e :clj/def ?d]
                 [?e :clj/defop ?op]
                 [?d :code/name ?def]]
               db))

  (defn foo
    "I don't do a whole lot."
    [x]
    (println x "Hello, World!"))


  (+ 3 4)

; (hello)
  
; (h1)
  
  
  )
