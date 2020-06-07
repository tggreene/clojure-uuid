# clojure-uuid

A collection of uuid implementations for clojure including v6 proposal
https://tools.ietf.org/html/draft-peabody-dispatch-new-uuid-format

## Usage

Currently there's only one type of uuid implemented:

```clojure
(require '[clojure-uuid.core :as clojure-uuid])

(clojure-uuid/generate-v6) ;; => #uuid "6866d018-6f2e-40a0-ac01-4125e1163220"
```

It should have the properties of sequential uuids,
[COMBs](https://en.wikipedia.org/wiki/Universally_unique_identifier#:~:text=This%20so-called%20%22COMB%22%20(combined%20time-GUID))
but with an open specification (albeit in draft).

## License

Copyright © 2020 Tim Greene

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.

This Source Code may also be made available under the following Secondary
Licenses when the conditions for such availability set forth in the Eclipse
Public License, v. 2.0 are satisfied: GNU General Public License as published by
the Free Software Foundation, either version 2 of the License, or (at your
option) any later version, with the GNU Classpath Exception which is available
at https://www.gnu.org/software/classpath/license.html.
