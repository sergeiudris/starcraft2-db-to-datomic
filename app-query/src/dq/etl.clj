(ns dq.etl
  (:require [datomic.api :as d]
            [clojure.repl :refer :all]
            [clojure.pprint :as pp]
            [dq.conn :refer [conn db cdb]]
            ))


(comment
  
  (doc slurp)
  (doc read-string)
  
  
  ;; load schema
  (def schema (read-string (slurp "resources/schema-aligulac.edn")))
  @(d/transact conn schema)
  
  ;; load sample data
  (def sample-data (read-string (slurp "resources/sample-data-aligulac.edn")))
  @(d/transact conn sample-data)
  
  
  
  )

