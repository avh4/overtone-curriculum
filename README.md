
# Computer setup

 - (Mac): Do a software update
 - (Mac): In the App Store, download the latest version of Xcode
 - (Mac): Install [homebrew](http://brew.sh/)
   - Open the Terminal app
   - paste the following command: `ruby -e "$(curl -fsSL https://raw.githubusercontent.com/Homebrew/install/master/install)"`
 - (Mac): In Terminal, run `brew install leiningen` to install `lein`, the command-line app we need to run Overtone
 - Run `git clone https://github.com/avh4/overtone-curriculum.git` to copy this project to your computer
 - Switch to the newly-copied project with `cd overtone-curriculum`
 - Run `lein repl` to start the clojure interpreter and to download Overtone if necessary
 - Enter `(use 'overtone.live)` to initalize Overtone
 - Copy and paste code from the files, or type your own code
   - [00_does_overtone_work.clj](00_does_overtone_work.clj) some small samples.  You should hear sound if you run them.
   - [01_play_music.clj](01_play_music.clj) a drum track, and chords with a MIDI-controlled lead for you to play

# Good things to know

 - enter `(stop)` to terminate any playing sounds
 - type Control-D to exit the clojure interpreter

# What to explain

 - clojure `doseq`


# Useful tools

 - (Mac): [MIDI Monitor](http://www.snoize.com/MIDIMonitor/) check to see if MIDI events are getting to the computer
 - [VMPK](http://vmpk.sourceforge.net/) Virtual MIDI Piano Keyboard (if you don't have a real MIDI instrument)
