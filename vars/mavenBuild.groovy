def VERSION = 'rohitkothapalli'
def call(String pomPath, String mavenOpts = '') {
    withEnv(['MAVEN_OPTS='+mavenOpts]) {
        stage('Build (Maven)') {
            sh "mvn -f ${pomPath} clean install"
        }
    }
}
