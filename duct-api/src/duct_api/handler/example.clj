(ns duct-api.handler.example
  (:require [ataraxy.core :as ataraxy]
            [ataraxy.response :as response] 
            [integrant.core :as ig]))

(defmethod ig/init-key :duct-api.handler/example [_ options]
  (fn [{[_] :ataraxy/result}]
    [::response/ok {:example "data"}]))
