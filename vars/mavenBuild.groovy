def VERSION = 'rohitkothapalli'
def call(String pomPath, String mavenOpts = '') {
    withEnv(['MAVEN_OPTS='+mavenOpts]) {
        stage('Build (Maven)') {

//             sh " apt-get install java -y "
            sh "mvn -f ${pomPath} clean install"
        }
    }
}
