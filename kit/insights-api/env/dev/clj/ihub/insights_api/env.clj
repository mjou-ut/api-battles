(ns ihub.insights-api.env
  (:require
    [clojure.tools.logging :as log]
    [ihub.insights-api.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init       (fn []
                 (log/info "\n-=[insights-api starting using the development or test profile]=-"))
   :start      (fn []
                 (log/info "\n-=[insights-api started successfully using the development or test profile]=-"))
   :stop       (fn []
                 (log/info "\n-=[insights-api has shut down successfully]=-"))
   :middleware wrap-dev
   :opts       {:profile       :dev
                :persist-data? true}})
