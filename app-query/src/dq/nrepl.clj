(ns dq.nrepl 
  (:require [nrepl.server :refer [start-server stop-server]]
            [clojure.repl :refer :all]))

(defn -main []
  (defonce server (start-server :bind "0.0.0.0" :port 7888)))

(comment
  
  (+ 1 1)
  (+)
  
  )