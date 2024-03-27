(ns comp-hw.data.compensation
  (:require [clojure.data.json :as json]))

(defonce map->key
  {"Timestamp" :timestamp
   "How old are you?" :age
   "What industry do you work in?" :industry
   "Job title" :job-title
   "What is your annual salary?" :salary
   "Please indicate the currency" :currency
   "Where are you located? (City/state/country)" :location
   "How many years of post-college professional work experience do you have?" :experience
   "If your job title needs additional context, please clarify here:" :addition-context
   "If \"Other,\" please indicate the currency here:" :other-currency})

(defn- load-db
  []
  (json/read-str
   (slurp "/Users/stephentriphahn/development/comp-hw/resources/salary_survey-1.json")
   :key-fn map->key))

(defonce ^:private db (atom (load-db)))

(comment
  (take 1
        (json/read-str
         (slurp "/Users/stephentriphahn/development/comp-hw/resources/salary_survey-1.json")
         :key-fn map->key)))

(defn connect
  [cs]
  (case cs
    "memory" db))

(defprotocol CompensationDB
  (get [_ args]))
