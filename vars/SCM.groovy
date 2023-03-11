def VERSION = 'master'
def call(String scmUrl) {
    stage('Checkout') {
        checkout([$class: 'GitSCM', branches: [[name: '*/*']], userRemoteConfigs: [[url: scmUrl]]])
    }
}
