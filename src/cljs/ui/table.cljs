(ns battleship.ui.table
  (:require [clojure.string :as string]
            [goog.dom :as gd]))

(defn grid->table [grid element-id]
  (let [element     (gd/getElement element-id)
        tr-open     "<tr>"
        tr-close    "</tr>"
        table-open  [(str "<table id='" element-id "'>")]
        table-close ["</table>"]
        table-cell  (fn [x y value]
                      (str "<td data-x='" x "' data-y='" y "' class='battleship-cell unknown'>&nbsp;</td>"))
        table-row   (fn [y row]
                      (concat tr-open
                       (mapcat (fn [x y value] (table-cell x y value)) (range) (repeat y) row)
                       tr-close))
        html        (concat table-open
                            (mapcat table-row (range) grid)
                            table-close)]
    (string/join html)))

(defn render-initial-table! [grid element-id]
  (let [html (grid->table grid element-id)
        element (gd/getElement element-id)]
    (set! (.-innerHTML element) html)))

(defn update-table! [grid element-id]
  (let [table-cells (prim-seq (gd/getElementsByTagNameAndClass "td"))
        array-cells (flatten grid)
        update-cell-class! (fn [table-cell value]
                             (let [classes {102 "missed"
                                            101 "destroyed"}
                                   class (get classes value "unknown")]
                               (set! (.-className table-cell)
                                     (str "battleship-cell " class))))]
    (doall (map update-cell-class! table-cells array-cells))))

(defn update-missed! [count element-id]
  (let [el (gd/getElement element-id)]
    (set! (.-innerHTML el) count)))

(defn update-status! [status element-id]
  (let [el (gd/getElement element-id)]
    (set! (.-innerHTML el) status)))

(defn set-ui-listeners! [click-handler]
  (let [fire-button (gd/getElement "fire")
        board       (gd/getElement "board")]
    (.addEventListener board "click" click-handler)))
