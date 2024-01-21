(ns ihub.insights-api.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[insights-api starting]=-"))
   :start      (fn []
                 (log/info "\n-=[insights-api started successfully]=-"))
   :stop       (fn []
                 (log/info "\n-=[insights-api has shut down successfully]=-"))
   :middleware (fn [handler _] handler)
   :opts       {:profile :prod}})
