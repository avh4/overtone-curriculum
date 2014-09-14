(use 'overtone.live)


(definst pad [freq 440] 
  (* (env-gen (env-lin))
     (sin-osc freq)))

(definst lead [freq 440] 
  (* (env-gen (env-lin))
     (saw freq)))

(definst drum []
  (* (env-gen (perc))
     (white-noise)))

; (definst kick []
;   (* (env-gen (perc))
;     (white-noise)))
     

;; We can play notes using frequency in Hz
; (lead 440)
; (pad 440)
; (drum)

(defn play [inst notes]
  (doseq [note notes] (inst (midi->hz note))))

(defn play-chord [inst root chord-name]
  (play inst (chord root chord-name)))


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
