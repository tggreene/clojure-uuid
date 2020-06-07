(ns clojure-uuid.core-test
  (:require [clojure.test :refer [deftest testing is]]
            [clojure-uuid.core :as core]))

(deftest produces-a-uuid
  (is (uuid? (core/generate-v6))))
