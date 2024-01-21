(ns ihub.insights-api.web.controllers.stories
  (:require
    [clojure.pprint :as pp]
    [ihub.insights-api.web.routes.utils :as utils]
    [ring.util.http-response :as http-response]))


(defn stories-list
  [req]
  (pp/pprint (select-keys req [:query-params :params :path-params]))

  (http-response/ok
    (map str (keys req))))
