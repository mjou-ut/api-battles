(ns ihub.insights-api.core
  (:gen-class)
  (:require
    [clojure.tools.logging :as log]
    [ihub.insights-api.config :as config]
    [ihub.insights-api.env :refer [defaults]]
    [ihub.insights-api.web.handler]
    ;; Routes
    [ihub.insights-api.web.routes.api]
    [integrant.core :as ig]
    ;; Edges       
    [kit.edge.server.undertow]))


;; log uncaught exceptions in threads
(Thread/setDefaultUncaughtExceptionHandler
  (reify Thread$UncaughtExceptionHandler
    (uncaughtException [_ thread ex]
      (log/error {:what :uncaught-exception
                  :exception ex
                  :where (str "Uncaught exception on" (.getName thread))}))))


(defonce system (atom nil))


(defn stop-app []
  ((or (:stop defaults) (fn [])))
  (some-> (deref system) (ig/halt!))
  (shutdown-agents))


(defn start-app [& [params]]
  ((or (:start params) (:start defaults) (fn [])))
  (->> (config/system-config (or (:opts params) (:opts defaults) {}))
       (ig/prep)
       (ig/init)
       (reset! system))
  (.addShutdownHook (Runtime/getRuntime) (Thread. stop-app)))


(defn -main [& _]
  (start-app))
