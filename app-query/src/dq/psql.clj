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


(defn player-sql-to-edn 
  [player] 
  (->>
   (identity {:player/id (player :id)
              :player/race (player :race)
              :player/name (player :name)
              :player/tag (player :tag)
              :player/country (player :country)})
   (into {} (remove (comp nil? second)))
   )
  )

(defn player-data []
  (->>
   (jdbc/query db-spec ["select * from player"])
   (mapv player-sql-to-edn)
   )
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



  *1)


