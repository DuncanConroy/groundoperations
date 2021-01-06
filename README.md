#Ground operations with Kotlin - An introduction to the Kotlin programming language based on airport procedures
This repository supports my talk on Kotlin on the 13th January 2021, which will be given for TwoDigits (Accenture) internally.
## Live-Coding
###What's in place
    - GroundoperationsApplication
    - Terminal
    - TaxiController
    - AutoTaxiSerivce
    - Plane extension (empty)

###What to show
	- GroundoperationsApplication
        -> SpringBootApplication annotation -> jump into source
        -> main fun is anonymous, not part of the GroundoperationsApplication - no brackets needed to define a class
        -> runApplication 
	- Terminal
        -> What's a data class and what's the simple signature?
            * Look at the plane third party
            * Write plane in kotlin
        -> Print a line for each plane with stand and callsign
            * start with printing the planes map
            * Call planesToTalbe in the string template
            * Adjust printline
            * Use padEnd(6) for stand, padEnd(12 for callsign)
	- TaxiController
        -> getTaxiRoute.sh
        -> addRobot
        -> Planeplus
        -> TaxiNode refactoring
	- AutoTaxiService
        -> Show, then go to plane
    - Plane
        -> Planefrontwheel
        -> Planesteerleft
        -> Planesteerright
        -> Planesteerstraight
        -> Oh that’s a lot of repetition. Let's clean this up. We'll introduce a sealed class for steering and make use of the when statement
        -> Planesteerref1 - 4
    - AutoTaxiService
        -> Start execution
        -> Implement globalscope and re-execute
        -> show refactoring