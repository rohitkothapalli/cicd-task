def VERSION = 'rohitkothapalli'
def call(String scmUrl) {
    stage('Checkout') {
        checkout([$class: 'GitSCM', branches: [[name: 'rohitkothapalli']], userRemoteConfigs: [[url: scmUrl]]])
    }
}
