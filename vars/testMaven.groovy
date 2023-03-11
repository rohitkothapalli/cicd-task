def VERSION = 'rohitkothapalli'
def call() {
    def mvnHome = tool 'Maven' // Set the Maven installation
    def mavenCommand = "${mvnHome}/bin/mvn" // Set the Maven command
    sh "${mavenCommand} test" // Run Maven test command
}
