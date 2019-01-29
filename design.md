
### concept

see [concept](./concept.md)

### overview

* datomic free
* web ui reagent? om?
* figwheel
* one app (service)
* load data using docker container
* cache build

### checkpoints

*   decide on data-set, add to repo
* ? data to datomic importer

*   add datomic-free
*   add clojure app lein? ?clj?; repl setup; connect to datomic-free
*   create queries, explore data using repl, no ui until the system is data-complete
*   add clojurescript-figwheel app
*   use http to send queries from client
*   load list of queries and show as a paginated list
*   edit, save to datomic and execute
*   display data as text only
* ? add graphical representaiton of data (e.g. as graph or list of shapes)


### data

* load-aligulac-psql - use to upload data to postgresdb service (import over network, not CLI)
* load-aligulac-datomic - use to import data to datomic

