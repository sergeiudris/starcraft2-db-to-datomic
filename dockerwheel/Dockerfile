FROM clojure

WORKDIR /clj

ADD project.clj /clj

RUN lein deps
RUN lein cljsbuild once

VOLUME /clj
ADD . /clj

# 3449 is default http and websocket port that figwheel uses to communicate
EXPOSE 3449
# 7888 is the default nrepl port
EXPOSE 7888

# CMD lein figwheel
# This command will start figwheel and open a repl socket
# This would need to change for a live environment -- you wouldn't want
# the figwheel socket open in production, but I'll leave that bit as an
# exercise for the reader.