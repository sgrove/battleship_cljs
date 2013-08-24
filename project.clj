(defproject battleship "0.1.0-SNAPSHOT"
  :plugins [[lein-cljsbuild "0.3.0"]
            [lein-bikeshed "0.1.0"]
            [lein-pprint "1.1.1"]
            [lein-ring "0.8.2"]]
  :description "Battleship"
  :url "http://trapm.posterous.com"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/cljs"
                 "src/battleship"]
  :dependencies [[org.clojure/clojure "1.5.1"]
                 [org.clojure/core.async "0.1.0-SNAPSHOT"]
                 [com.cemerick/piggieback "0.1.0"]]
  :repositories {"sonatype-oss-public" "https://oss.sonatype.org/content/groups/public/"}
  :repl-options {:nrepl-middleware
                 [cemerick.piggieback/wrap-cljs-repl]}
  :cljsbuild {:builds
              [{:source-paths ["src/cljs"],
                :id "dev",
                :compiler {:pretty-print true,
                           :output-dir "resources/public/js/bin-debug",
                           :output-to "resources/public/js/bin-debug/main.js",
                           :externs [],
                           :optimizations :whitespace}}
               {:source-paths ["src/cljs"
                               "test-cljs"],
                :id "test",
                :compiler {:pretty-print true,
                           :output-to "resources/private/js/unit-test.js",
                           :externs [],
                           :optimizations :whitespace}}
               {:source-paths ["src/cljs"],
                :id "prod",
                :compiler {:pretty-print false,
                           :output-dir "resources/public/js/bin",
                           :output-to "resources/public/js/bin/main.js",
                           :debug true,
                           :externs [],
                           :optimizations :advanced}}],
              :test-commands {"unit"
                              ["phantomjs"
                               "--debug=yes"
                               "phantom/unit-test.js"
                               "resources/private/html/unit-test.html"]},
              :repl-listen-port 9000,
              :repl-launch-commands {"firefox-naked" ["firefox"
                                                      "resources/private/html/naked.html"
                                                      :stdout ".repl-firefox-naked-out"
                                                      :stderr ".repl-firefox-naked-err"],
                                     "phantom" ["phantomjs"
                                                "phantom/repl.js"
                                                :stdout ".repl-phantom-out"
                                                :stderr ".repl-phantom-err"],
                                     "phantom-naked" ["phantomjs"
                                                      "phantom/repl.js"
                                                      "resources/private/html/naked.html"
                                                      :stdout ".repl-phantom-naked-out"
                                                      :stderr ".repl-phantom-naked-err"],
                                     "firefox" ["firefox"
                                                :stdout ".repl-firefox-out"
                                                :stderr ".repl-firefox-err"]}})
