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


(defn filterm-nil [x] (into {} (remove (comp nil? second)) x))

;; mappers

(defn player->edn 
  [player] 
  (->>
   (identity {:player/id (player :id)
              :player/race (player :race)
              :player/name (player :name)
              :player/tag (player :tag)
              :player/country (player :country)})
   filterm-nil
   )
  )


(defn match->edn
  [x]
  (->>
   (identity {:match/id (x :id)
              :match/event (x :match/event)
              :match/eventobj_id (x :eventobj_id)
              :match/pla_id (x :pla_id)
              :match/plb_id (x :plb_id)
              :match/sca (x :sca)
              :match/scb (x :scb)
              :match/rca (x :rca)
              :match/rcb (x :rcb)
              :match/game (x :game)
              :match/date (x :date)
              })
   filterm-nil
   )
  )

(defn player-data []
  (->>
   (jdbc/query db-spec ["select * from player"])
   (mapv player->edn)
   )
)

(defn match-data []
  (->>
   (jdbc/query db-spec ["select * from match limit 91316 offset 200000"])
   (mapv match->edn))
  )



(comment


  (pp/pprint (take 5 (player-data)))
  
  (mapv player-sql-to-edn [])

 (doc identity) 

  (jdbc/query db-spec ["select 3*5 as result"])

  (jdbc/query db-spec ["select * from player where tag = 'Scarlett'"])
  (->>
   (jdbc/query db-spec ["select * from player where tag = 'Bomber'"])
   pp/pprint)

  (jdbc/query db-spec ["select * from player order by id limit 5 offset 5"])

  (->>
   (jdbc/query db-spec ["select * from match where plb_id = 23 and pla_id = 12 order by id limit 5 "])
   pp/pprint)

  (->>
   (jdbc/query db-spec ["select * from match where id = 62618 order by id limit 5 "])
   pp/pprint)


  (->>
   (jdbc/query db-spec ["select * from event where id in (12991,3506) order by id limit 5 "])
   pp/pprint)


  (->>
   (jdbc/query db-spec ["select id from player order by id desc limit 5 offset 5"])
   pp/pprint)
  (->>
   (jdbc/query db-spec ["select * from player order by id desc limit 5 offset 100"])
   pp/pprint)

  (count (jdbc/query db-spec ["select * from player order by id "]))
  (count (jdbc/query db-spec ["select * from match order by id "]))
  (count (jdbc/query db-spec ["select * from event order by id "]))
  (count (jdbc/query db-spec ["select * from earnings order  by id "]))

   (jdbc/query db-spec ["select count(*) from match"])



  *1)


