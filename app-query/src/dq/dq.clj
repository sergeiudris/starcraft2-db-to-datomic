(ns dq.dq
  (:require [datomic.api :as d]
            [dq.nrepl]
            [dq.psql]
            [dq.conn :refer [conn db]]
            [dq.server]
            ))

(defn -main []
  (prn conn)
  (dq.nrepl/-main)
  (dq.server/start))

