(defproject clojure-javaagent "1.0.0"
  :description "An example project demonstrating how to build and attach a javaagent to a Clojure REPL at runtime"
  :url "https://dgopstein.github.io/articles/clojure-javaagent"
  :license {:name "MIT License"
            :url "https://opensource.org/licenses/MIT"
            :key "mit"
            :year 2017}
  :dependencies [[org.clojure/clojure "1.8.0"]]
  ;; socket repl
  :jvm-opts ["-Dclojure.server.repl={:port 8237 :accept clojure.core.server/repl}"]
  :profiles {:start-socket-repl
             {:main clojure-javaagent.start-socket-repl
              :aot [clojure-javaagent.start-socket-repl]
              ;; Note the '_' instead of '-' since we're describing a java package
              :manifest {"Agent-Class" "clojure_javaagent.start_socket_repl"}}}
  :main clojure-javaagent.core)
