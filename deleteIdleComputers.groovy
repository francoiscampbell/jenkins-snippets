/**
 * Delete idle computers
 */
Jenkins.instance.computers.drop(1).each { computer ->
  if (computer.offline) {
    deleteComputer(computer)
    return
  }  
  def executable = computer.executors[0].currentExecutable
  if (!executable) {
    deleteComputer(computer)
    return
  }
  def task = executable.parent
  if (!task.run()) {
    deleteComputer(computer)
  }
}
