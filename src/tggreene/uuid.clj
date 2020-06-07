(ns tggreene.uuid
  (:import [java.util UUID]))

(def gregorian-to-epoch-seconds 122192928000000000)

(defn generate-v6
  "Generate a v6 UUID according to proposal"
  []
  (let [;; take random bits from java.util v4 implementation
        uuid (UUID/randomUUID)
        ;; we can't resolve finer than some number of milliseconds on most
        ;; systems so we need to "fake" nanoseconds
        time (- (* (System/currentTimeMillis) 10000) gregorian-to-epoch-seconds)
        least (.getLeastSignificantBits uuid)
        most (bit-or 0x0000000060000000
                     (bit-and 0x7FFFFFFF00000000 (bit-shift-left time 4))
                     (bit-and 0x000000000FFFFFFF time))]
    (UUID. most least)))
