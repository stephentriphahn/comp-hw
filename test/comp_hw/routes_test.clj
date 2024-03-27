(ns comp-hw.routes-test
  (:require [clojure.test :refer :all]
            [ring.mock.request :as mock]
            [comp-hw.routes :refer :all]))

(deftest test-app
  (testing "main route"
    (let [response (app (mock/request :get "/api/v1/compensation_data"))]
      (is (= (:status response) 200))
      (is (= (:body response) "Hello World"))))

  (testing "not-found route"
    (let [response (app (mock/request :get "/invalid"))]
      (is (= (:status response) 404)))))
