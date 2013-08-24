(ns battleship.core
  (:require [cljs.repl.browser]))

;  (cemerick.piggieback/cljs-repl
;   :repl-env (doto (cljs.repl.browser/repl-env :port 9000)
;               cljs.repl/-setup))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))
