
; setup a sound for our metronome to use
(def kick (sample (freesound-path 2086)))

; setup a tempo for our metronome to use
(def one-twenty-bpm (metronome 120))

# (def kick (sin-osc))

; this function will play our sound at whatever tempo we've set our metronome to 
(defn looper [nome sound]    
    (let [beat (nome)]
        (at (nome beat) (sound))
        (apply-by (nome (inc beat)) looper nome sound [])))

; turn on the metronome
(looper one-twenty-bpm kick)
(stop)

; to get a feel for how the metronome works, try defining one at the REPL
(def nome (metronome 200))
(nome)
; 8 
; why is this 8? shouldn't it be 1? let's try it again
(nome)
