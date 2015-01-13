(ns calendar.core)

;;(take 31 (cycle ["F " "Sa " "Su " "M " "Tu " "W " "Th "]))
(def week-days ["Su" "Mo" "Tu" "We" "Th" "Fr" "Sa"])
(def month-days [31 28 31 30 31 30 31 31 30 31 30 31])

(defn leapyear?
  "doc-string"
  [year]
  (if (= (rem year 4) 0)
    (if (not= (rem year 100) 0)
      true
      (= (rem year 400) 0))
    false))

(defn leapyearalt?
  "alternate way to calculate leap year"
  [year]
  (if (not= ( rem year 4) 0)
      false
      (if (not= (rem year 100) 0)
        true
       (if (not= (rem year 400) 0)
         false
         true
         ))))

(defn count-leapyears
  "counts leap years till the given year (excluding)"
  [year]
  ;;(println "year passed " year)
  (count (filter leapyear? (range 1 year))))

(defn cal-start-day-of-month
  "calculates start day on 1st of that month"
  [month year]
  (let [lp (count-leapyears year)
          start-month-day (week-days (rem (+ year lp (if (and (> month 2) (leapyear? year)) 1 0) (reduce + (take (- month 1) month-days))) 7))]
      start-month-day))

(defn gencal
  "generates calendar format for dates of the month"
  [start-weekday month]
  (let [first-row false
        start-idx (.indexOf week-days start-weekday)
        vt (into [] (partition-all 7 (range start-idx (month-days month))))]
    (println week-days)
    ()
        )
  )

(defn cal
  ([month year]
(if (or (< month 1) ( > month 12))
      "Invalid Month :: should be b/w 1-12"
      (cal-start-day-of-month month year)
      ))
  ([year]
    "calculates weekday on 1st Jan of given year"
    (cal-start-day-of-month 1 year)
    )
  )

(defn ical
  "Only for testing purpose:: gives day on 1st Jan of given year using java.util.Calendar"
  ([y]
    (let [idx (.get (doto (java.util.Calendar/getInstance) (.set y 0 1)) java.util.Calendar/DAY_OF_WEEK)]
      ( week-days (- idx 1) )))
  ([m y]
   (let [idx (.get (doto (java.util.Calendar/getInstance) (.set y (dec m) 1)) java.util.Calendar/DAY_OF_WEEK)]
      ( week-days (- idx 1) )))
  )
