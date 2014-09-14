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


; Play the lead from the MIDI input
(def lead-player (midi-poly-player lead))

; and stop it:
; (midi-player-stop lead-player)

(defn play-notes [inst notes]
  (doseq [note notes] (inst note)))

(defn play-chord [inst root chord-name]
  (play-notes inst (chord root chord-name)))


(defn chord-progression-beat []
  (let [m (metronome 240)]
    (at (m  0) (play-chord pad :C4 :major))
    (at (m  4) (play-chord pad :G3 :major))
    (at (m  8) (play-chord pad :A3 :minor))
    (at (m 12) (play-chord pad :F3 :major))
  
    (doseq [i (range 0 16 3)]
      (at (m i) (drum))
    )
  
    (apply-at (m 16) #(chord-progression-beat))
  )
)
(chord-progression-beat)

; (stop)
