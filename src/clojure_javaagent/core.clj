(ns clojure-javaagent.core
  (:require
   [clojure.java.io :as cji])
  (:import
   [com.sun.tools.attach VirtualMachine]))

(set! *warn-on-reflection* true)

(defn -main
  [& args]
  ;; pid - string
  ;; port - integer as string
  (let [[^String pid port] args
        project-path (.getCanonicalPath (cji/file "."))
        javaagent-path (str project-path "/target/clojure-javaagent-1.0.0.jar")]
    (.loadAgent (VirtualMachine/attach pid) javaagent-path (str port))))
