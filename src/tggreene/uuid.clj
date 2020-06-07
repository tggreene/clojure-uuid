(ns tggreene.uuid
  (:import [java.util UUID]))

(defn bit-slice
  "Not (particularly) related to [bit-slicing](https://en.wikipedia.org/wiki/Bit_slicing)"
  [x start n]
  (assert (number? x) "x must be a number")
  (bit-and (bit-shift-right  x (- start n)) (dec (long (Math/pow 2 n)))))

(defn uuid->str
  [uuid]
  (let [most  (.getMostSignificantBits uuid)
        least (.getLeastSignificantBits uuid)]
    (format "%08x-%04x-%04x-%04x-%012x"
            (bit-slice most 64 32)
            (bit-slice most 32 16)
            (bit-slice most 16 16)
            (bit-slice least 64 16)
            (bit-slice least 48 48))))

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

(defn is-uuid-v6?
  [^UUID u]
  (java.lang.ByteBuffer. (.getMostSignficantBits u) (.getLeastSignificantBits u)))

(comment
  (java.nio.ByteBuffer)
  (bytes (rand-int 10))
  (type (.getMostSignificantBits (UUID/randomUUID)))
  (generate-v6))
