rootProject.name = "hexarch.dms"

include(":shared:util")
include(":shared:event")

include(":configuration:web-api")

include(":preparation:domain")
include(":preparation:application")
include(":preparation:adapter-in-web")
include(":preparation:adapter-out-event")
include(":preparation:adapter-out-db")

include(":verification:domain")
include(":verification:application")
include(":verification:adapter-in-event")
include(":verification:adapter-in-web")
include(":verification:adapter-out-db")
include(":verification:adapter-out-preparation")
