(ns calendar.core)

(defn leapyear?
  "doc-string"
  [year]
  (if (= (rem year 4) 0)
    (if (not= (rem year 100) 0)
      true
      (= (rem year 400) 0))
    false))

(defn count-leapyears
  "counts leap years till the given year (excluding)"
  [year]
(count (filter leapyear? (range 1 year)))
  )

;;(take 31 (cycle ["F " "Sa " "Su " "M " "Tu " "W " "Th "]))

(defn cal
  "doc-string"
  [month year]
  (if (or (< month 1) ( > month 12))
    "Invalid month"
    (let [lp (count-leapyears year)
            month-days [31 28 31 30 31 30 31 31 30 31 30 31]
            week-days ["Fr " "Sa " "Su " "Mo " "Tu " "We " "Th "]
            start-month-day (week-days (rem (+ year lp (if ( leapyear? year) 1 0) (reduce + (take (- month 1) month-days))) 7))
            ;; start-month (+ start-jan-1 (* (- month 1) ))
            ]
        start-month-day
        )
    )
  )
