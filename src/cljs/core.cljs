(ns battleship.core
  (:require [clojure.browser.repl :as repl]
            [battleship.functional-board :as fb]
            [battleship.ui.table :as table-ui]))

(defn ^:export nrepl []
  (repl/connect "http://localhost:9000/repl"))

(set! (.-nrepl js/window)
      nrepl)

(def board (atom nil))

(def board-id "board")

(defn click-handler [board event]
  (when-not (fb/game-over? @board)
    (let [cell         (.-target event)
          x            (js/parseInt (.getAttribute cell "data-x"))
          y            (js/parseInt (.getAttribute cell "data-y"))]
      (swap! board fb/fire x y)
      (table-ui/update-table!  @board "board")
      (table-ui/update-status! (fb/game-status @board) "status")
      (table-ui/update-missed! (fb/missed-count @board) "missed"))))

(defn setup! [event]
  (reset! board (-> (fb/empty-grid 5 5)
                    (fb/populate-grid 4 3 4)))
  (table-ui/render-initial-table! @board board-id)
  (table-ui/set-ui-listeners!
   #(click-handler board %)))

(.addEventListener js/window "load" setup! false)
