(ns dq.psql
  (:require [clojure.repl :refer :all]
            [clojure.java.jdbc :as jdbc]
            [dq.nrepl]))


(def db-spec 
  {:dbtype "postgresql"
   :dbname "aligulac"
   :user "aligulac"
   :host "postgresdb"
   :port 5432
   :password "postgres"
   })

(jdbc/query db-spec ["select 3*5 as result"])

(defn hello [] (prn "hello"))

(hello)
