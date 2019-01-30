(ns dq.aligulac
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
  
  
  ;; sample queries
  
  (d/q '{:find [(pull ?e [*])]
         :where [
                 [?e :player/id 23]
                 ]}
       (cdb)
       )

  ;; find player id
  (defn player-id [tag] 
    (->>
     (d/q '{:find [?player-id]
            :in [$ ?tag]
            :where [[?e :player/tag ?tag]
                    [?e :player/id ?player-id]]}
          (cdb) tag)
     ffirst
     ))

  (player-id "Scarlett")  
  
  ;; find all matches Scarlett played in
  
  (d/q '{:find [(pull ?match [*])]
         :in [$ ?payer-id]
         :where [(or
                  [?match :match/pla_id ?player-id]
                  [?match :match/plb_id ?player-id])
                 ]
         }
       (cdb) 23
       )
  
  ; (d/q '{:find [(pull ?match [*])]
  ;        :in [$ ?player]
  ;        :where [
  ;                [?player :player/id ?player-id]
  ;                (or
  ;                 [?match :match/pla_id ?player-id]
  ;                 [?match :match/plb_id ?player [] id])]}
  ;      (cdb) [:player/tag "Scarlett"])
  
  (->>
  (d/q '{:find [(pull ?match [*])]
         :in [$ ?player-id]
         :where [
                 (or
                  [?match :match/pla_id ?player-id]
                  [?match :match/plb_id ?player-id])
                 ]}
       (cdb) (player-id "Scarlett"))
  pp/pprint
   )
  
  
  ;; find all events Bomber participated in
  
  
  
  )

