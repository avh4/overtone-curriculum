(use 'overtone.live)


(definst pad [freq 440] 
  (* (env-gen (env-lin))
     (sin-osc freq)))

(definst lead [freq 440] 
  (* (env-gen (env-lin))
     (saw freq)))

(definst drum [freq 440]
  (* (env-gen (perc))
     (white-noise)))

; (definst kick [freq 440]
;   (* (env-gen (perc))
;     (white-noise)))
     

;; We can play notes using frequency in Hz
; (lead 440)
; (pad 440)
; (drum)

(defn play-chord [inst a-chord]
  (doseq [note a-chord] (inst (midi->hz note))))


(def metro (metronome 240))
(defn chord-progression-beat [m beat-num]
  (at (m (+ 0 beat-num)) (play-chord pad (chord :C4 :major)))
  (at (m (+ 4 beat-num)) (play-chord pad (chord :G3 :major)))
  (at (m (+ 8 beat-num)) (play-chord pad (chord :A3 :minor)))
  (at (m (+ 12 beat-num)) (play-chord pad (chord :F3 :major)))
  
  (for [i (range 0 16 3)]
    (at (m (+ i beat-num)) (drum 440))
  )
  
  ; (apply-at (m (+ 16 beat-num)) chord-progression-beat m (+ 16 beat-num) [])
)
(chord-progression-beat metro (metro))

; (stop)
