;Recursion
(defn pow [base exponente]
    (cond
      (= exponente 0) 1
      (< exponente 2) base
      :else (* base (pow base (- exponente 1)))
    ) 
)

; Tail recursion con letfn
(defn pow_tr_letfn [base exponente] 
  (if (= 0 exponente)
    1
    (letfn
      [(exp [b e r]
        (if (< e 2) r
          (recur b (dec e) (* r b))
        )
        )]
      (exp base exponente base)
    )
  )
)

;Tail recursion con loop / recur
(defn pow_tr_loop [base exponente]
  (if (= base 0)
    1
    (loop [b base e exponente r base]
      (if (< e 2)
        r
        (recur b (dec e) (* r b))
      )
    )
  )
)

; lazy-seq
(defn power [base exponente]
  (letfn 
    [(multiply [base factor]
      (lazy-seq
        (cons base (multiply (* base factor) factor))
      )
    )]
    (nth (multiply base base) (dec exponente))
  )
)

; Llamadas a funciones

(pow 2 3)
(pow_tr_letfn 2 3)
(pow_tr_loop 2 3)
(power_lazy 2 3)