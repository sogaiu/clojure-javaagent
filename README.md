# clojure-javaagent

An example project demonstrating how to build and attach a javaagent to a Clojure process at runtime.

Specifically, it attempts to start a socket repl.

A long-form description of the example this is based on is available at:

  <https://dgopstein.github.io/articles/clojure-javaagent>

There are two separate components of this project:

* Generating the javaagent jar: `lein with-profile start-socket-repl uberjar`

* Loading the javaagent: `lein run <pid> <port>`

  * \<pid\> is the process id of a JVM process running Clojure
  * \<port\> is a port number for socket repl to be started on
