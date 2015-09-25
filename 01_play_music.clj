(use 'overtone.live)


(definst pad [note 60 velocity 100]
  (let [freq (midicps note)
        amp (/ velocity 127.0)
        env (env-gen (env-lin))]
    (* amp env (sin-osc freq))))

(definst lead [note 60 velocity 100]
  (let [freq (midicps note)
        amp (/ velocity 127.0)
        env (env-gen (env-lin))]
    (* amp env (saw freq))))

(definst drum [note 60 velocity 100]
  (let [freq (midicps note)
        amp (/ velocity 127.0)
        env (env-gen (perc))]
    (* amp env (white-noise))))


;; We can play notes
; (lead (note :C4))
; (pad (note :C4))
; (drum)


; Play from the MIDI input
(midi-poly-player lead)
(midi-poly-player pad)
; (midi-poly-player drum)

(defn play-notes [inst notes]
  (doseq [note notes] (inst note)))

(defn play-chord [inst root chord-name]
  (play-notes inst (chord root chord-name)))


(defn chord-progression-beat []
  (let [m (metronome 240)]
    (at (m  0) (play-chord pad :A4 :minor))
    (at (m  4) (play-chord pad :F3 :major))
    (at (m  8) (play-chord pad :D3 :minor))
    ; (at (m 12) (play-chord pad :G3 :6 ))
    
    (at (m 16) (play-chord pad :A4 :minor))
    (at (m 20) (play-chord pad :F3 :major))
    (at (m 24) (play-chord pad :D3 :minor))
    ; (at (m 28) (play-chord pad :G3 :6 ))

    (at (m 32) (play-chord pad :F4 :major))
    ; (at (m 36) (play-chord pad :G3 :6 ))
    (at (m 40) (play-chord pad :C3 :major))
    (at (m 42) (play-chord pad :G3 :major))
    (at (m 44) (play-chord pad :F4 :major))

    (at (m 48) (play-chord pad :F4 :major))
    ; (at (m 52) (play-chord pad :G3 :6 ))
    (at (m 56) (play-chord pad :C3 :major))
    (at (m 60) (play-chord pad :D3 :major))

    (doseq [i (range 0 16 3)]
      (at (m i) (drum))
    )
  
    (apply-at (m 64) #(chord-progression-beat))
  )
)
(chord-progression-beat)

; (stop)
