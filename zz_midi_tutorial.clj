(use ['overtone.synth.sts :only ['prophet]])

;; Now, we know we can trigger synths manually with code:
 
(prophet :freq 110 :decay 5 :rq 0.6 :cutoff-freq 2000)
(prophet :freq 130 :decay 5 :rq 0.6 :cutoff-freq 2000)

(on-event [:midi :note-on]
          (fn [m]
            (let [note (:note m)]
              (prophet :freq (midi->hz note)
                       :decay 5
                       :rq 0.6
                       :cutoff-freq 1000
                       :amp (:velocity-f m))))
          ::prophet-midi)

;; To see all the information passed to the event system:
 
(on-event [:midi :note-on]
          (fn [m]
            (println (type (:velocity-f m))))
          ::midi-debug)

;; We can remove our event handlers with:
 
(remove-event-handler ::prophet-midi)
(remove-event-handler ::midi-debug)


; For example, print out all incoming note-on messages:

(on-event [:midi :note-on] (fn [{note :note velocity :velocity}]
                             (println "Note: " note ", Velocity: " velocity))
          ::note-printer)

(remove-event-handler ::note-printer)



(definst poly-ding
  [note 60 amp 1 gate 1]
  (let [freq (midicps note)
        snd  (sin-osc freq)
        env  (env-gen (adsr 0.001 0.1 0.6 0.3) gate :action FREE)]
    (* amp env snd)))


; Create a polyphonic midi player:
(def ding-player (midi-poly-player poly-ding))

; and stop it:
(midi-player-stop ding-player)