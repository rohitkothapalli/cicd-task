def VERSION = 'rohitkothapalli'
def call(String pomPath, String mavenOpts = '') {
    withEnv(['MAVEN_OPTS='+mavenOpts]) {
        stage('Build (Maven)') {
//             sh "apt-get update"
//             sh "apt-get install maven"
            sh "mvn -f ${pomPath} clean install"
        }
    }
}
