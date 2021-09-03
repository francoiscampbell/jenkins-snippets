import hudson.model.Computer

/**
 * Deletes a Computer, optionally in the background (to avoid 
 * timeouts when deleting lots of computers)
 */
def deleteComputer(Computer computer, bool inBackground = true) {
    def work = {
        computer.doDoDelete()
    }
    if (inBackground) {
        println "Deleting ${computer} in background"
        Computer.threadPoolForRemoting.submit(work)
    } else {
        println "Deleting ${computer}"
        work()
    }
}
