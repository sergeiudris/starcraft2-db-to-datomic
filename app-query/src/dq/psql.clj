(ns dq.psql
  (:require [clojure.repl :refer :all]
            [clojure.java.jdbc :as jdbc]
            [clojure.pprint :as pp]
            [dq.nrepl]))

(defn hello [] (prn "hello"))

 (def db-spec
   {:dbtype "postgresql"
    :dbname "aligulac"
    :user "aligulac"
    :host "postgresdb"
    :port 5432
    :password "postgres"})



(comment


  (jdbc/query db-spec ["select 3*5 as result"])

  (jdbc/query db-spec ["select * from player where tag = 'Scarlett'"])

  (jdbc/query db-spec ["select * from player order by id limit 5 offset 5"])
  
  (->>
   (jdbc/query db-spec ["select * from player order by id limit 5 offset 5"])
   pp/pprint
   )
  
   (->>
    (jdbc/query db-spec ["select id from match order by id limit 5 offset 5"])
    pp/pprint
    )
  
  
  )


