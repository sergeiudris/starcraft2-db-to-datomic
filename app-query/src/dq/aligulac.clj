(ns dq.aligulac
  (:require [datomic.api :as d]
            [clojure.repl :refer :all]
            [dq.conn :refer [conn db]]
            ))




(comment
  
  (doc slurp)
  (doc read-string)
  
  
  
  (def schema (read-string (slurp "resources/schema-aligulac.edn")))
  @(d/transact conn schema)
  
  
  
  
  )

