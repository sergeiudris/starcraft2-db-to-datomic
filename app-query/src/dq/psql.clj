(ns dq.psql
  (:require [clojure.repl :refer :all]
            [clojure.java.jdbc :as jdbc]
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
  
  
  
  
  
  )


