(ns dq.etl
  (:require [datomic.api :as d]
            [clojure.repl :refer :all]
            [clojure.pprint :as pp]
            
            [dq.psql]
            [dq.conn :refer [conn db cdb]]
            [dq.query :refer [entity-by-external-id]]
            ))




(comment

  (doc slurp)
  (doc read-string)

  (count (dq.psql/player-data))

  ;; load schema
  (def schema (read-string (slurp "resources/schema-aligulac.edn")))
  @(d/transact conn schema)

  ;; load sample data
  (def sample-data (read-string (slurp "resources/sample-data-aligulac.edn")))
  @(d/transact conn sample-data)

  ;; load sql data and transact to datomic
  
  ;player entity has no refs
  @(d/transact conn (dq.psql/player-data))

  ;event entity has no refs
  @(d/transact conn (dq.psql/event-data 50000 0))
  @(d/transact conn (dq.psql/event-data 38689 50000))

  ;match entity has player, event refs
  @(d/transact conn (dq.psql/match-data 50000 0))
  @(d/transact conn (dq.psql/match-data 50000 50000))
  @(d/transact conn (dq.psql/match-data 50000 100000))
  @(d/transact conn (dq.psql/match-data 50000 150000))
  @(d/transact conn (dq.psql/match-data 50000 200000))
  @(d/transact conn (dq.psql/match-data 41316 250000))


  ;earnings entity has player, event refs
  @(d/transact conn (dq.psql/earnings-data))


  ;; construct references
  
  (->>
   (d/q '{:find [(pull ?match [*])]
          :where [[?match :match/id]]}
        (cdb))
  ;  (take 30)
   count)



  (->>
   (d/pull (cdb) '[:db/id] [:player/id 23])
   first second)

  (d/pull (cdb) '[*] 17592186059397)

  (entity-by-external-id :player/id 23)

  (entity-by-external-id :match/id 23)

  (entity-by-external-id :event/id 1111)

  (entity-by-external-id :earnings/id 11)





  (doc d/entid)
  (doc d/entity)


  (keys (ns-publics 'datomic.api)))

