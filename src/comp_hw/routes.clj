(ns comp-hw.routes
  (:require [compojure.core :refer :all]
            [clojure.pprint :refer [pprint]]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]))

(defroutes app-routes
  (context "/api/v1" []
    (GET "/compensation_data" {params :params}
      (str params))
    (GET "/compensation_data/:id" [id]
      (str id)))
  (route/not-found "Not Found"))

(defn wrap-log-request [handler]
  (fn [request]
    (pprint request)
    (handler request)))

(def app
  (-> app-routes
      wrap-log-request
      (wrap-defaults site-defaults)))
