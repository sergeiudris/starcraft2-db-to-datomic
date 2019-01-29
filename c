#!/bin/bash

export COMPOSE_CONVERT_WINDOWS_PATHS=1

# kill $(lsof -t -i:8080)
# kill -9


dc(){
      docker-compose \
    -f ./docker-compose.yml \
    "$@"
}

up(){
  dc up -d --build
}
down(){
  dc down
}

ip(){
  docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}'   "$@"
}


prune(){
  down
  docker system prune -a
  docker volume prune

  # docker image prune # dangling
  # docker image prune -a # all unused
  # docker container  prune

}

appquery(){
  # docker-compose exec -e TERM clojure sh
  # docker-compose -f datomic.yml exec app-pro sh
  bash c dc exec app-query bash
}


repl(){
  # lein repl :headless :host 0.0.0.0 :port 35543
  cd clj-lein
  lein repl :start :host 0.0.0.0 :port 35543
}

conn(){
  lein repl :connect 0.0.0.0:35543
}

ladsh(){
  bash c dc exec load-aligulac-datomic bash
}
lapsh(){
  bash c dc exec load-aligulac-psql bash
}


transactor(){
  datomic-free-0.9.5703/bin/transactor config/samples/free-transactor-template.properties # Replace path/to/ with the path to the file.
}


"$@"
