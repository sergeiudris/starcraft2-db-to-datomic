(ns dq.query
  (:require [datomic.api :as d]
            [clojure.repl :refer :all]
            [clojure.pprint :as pp]
            [dq.conn :refer [conn db cdb]]
            ))

 (defn entity-by-external-id
   [attribute external-id]
   (->>
    (d/pull (cdb) '[:db/id] [attribute external-id])
    first second))

(comment
  
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

  (->>
   (d/q '{:find [(count ?match )]
          :in [$ ?player-id]
          :where [
                  (or
                   [?match :match/pla_id ?player-id]
                   [?match :match/plb_id ?player-id])
                  ]}
        (cdb) (player-id "Scarlett"))
   pp/pprint
   )
  
  ;; count all mathces bobmber palyed in
  (->>
   (d/q '{:find [?match]
          :in [$ ?tag]
          :where [[?player :player/tag ?tag]
                  (or
                   [?match :match/pla ?player]
                   [?match :match/plb ?player])
                  ]
          }
        (cdb) "Bomber")
   count)
  ; => 583
  
  ;; find all events Bomber participated in
  (->>
   (d/q '{:find [(distinct ?event) .]
          :in [$ ?tag]
          :where [
                  [?player :player/tag ?tag]
                  (or
                   [?match :match/pla ?player]
                   [?match :match/plb ?player]
                   )
                  [ ?match :match/eventobj ?event ]
                  ]}
        (cdb) "Bomber"
        )
   count
   )
  ; => 402

  
  
  
  )


(comment

  ;; count all players
  (->>
   (d/q '{:find [(count ?e)]
          :where [[?e :player/id]]}
        (cdb))
   ffirst
    ; pp/pprint
   )
  
;; count all matches
  (->>
   (d/q '{:find [(count ?e)]
          :where [[?e :match/id]]}
        (cdb))
   ffirst
    ; pp/pprint
   )
  ;; count all events
  (->>
   (d/q '{:find [(count ?e)]
          :where [[?e :event/id]]}
        (cdb))
   ffirst
    ; pp/pprint
   )
  
    ;; count all earnings
  (->>
   (d/q '{:find [(count ?e)]
          :where [[?e :earnings/id]]}
        (cdb))
   ffirst
    ; pp/pprint
   )

  ;; count matches that have player ref
  (->>
   (d/q '{:find [(count ?e)]
          :where [[?e :match/pla]]}
        (cdb))
   ffirst
    ; pp/pprint
   )

 ;; count earnings that have eventobj ref
  (->>
   (d/q '{:find [(count ?e)]
          :where [[?e :earnings/eventobj]]}
        (cdb))
   ffirst
    ; pp/pprint
   )


  
  )

