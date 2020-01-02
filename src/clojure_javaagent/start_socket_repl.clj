(ns clojure-javaagent.start-socket-repl
  (:require
    [clojure.core.server :as ccs]
    [clojure-javaagent.cm :as cm])
  (:gen-class
   :methods [^:static [agentmain [String] void]]))

;; in java >= 9, (.getContextClassLoader (Thread/currentThread)) returns nil
;; for some reason.
;;
;; that messes up clojure.main/repl
;;
;; so the following uses clojure-javaagent.cm/repl which uses
;; (ClassLoader/getSystemclassloader) instead
(defn repl
  []
  (cm/repl
    :init ccs/repl-init
    :read ccs/repl-read))

(defn -agentmain [^String args]
  (println "Loading agentmain")
  (let [port (or (try
                   (Integer. args)
                   (catch Exception _
                     nil))
                 7659)]
    (ccs/start-server
      {:address "127.0.0.1"
       :name "instrument"
       :port port
       :accept 'clojure-javaagent.start-socket-repl/repl})
    ;; XXX: set system property for clojure.server.<name> if successful?
    (println "Finishing agentmain")
    nil))
