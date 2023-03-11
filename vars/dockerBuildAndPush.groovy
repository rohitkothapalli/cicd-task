def VERSION = 'rohitkothapalli'
def call(String imageName = 'my-image', String imageTag = 'latest', String dockerfilePath = '.', String dockerHubCredentialsId = 'docker-hub-creds') {
  def dockerBuildCmd = "docker build -t ${imageName}:${imageTag} ${dockerfilePath}"
  sh "${dockerBuildCmd}"

  withCredentials([string(credentialsId: dockerHubCredentialsId, variable: 'DOCKERHUB_CREDENTIALS')]) {
    def dockerLoginCmd = "docker login -u ${DOCKERHUB_CREDENTIALS.split(':')[0]} -p ${DOCKERHUB_CREDENTIALS.split(':')[1]}"
    sh "${dockerLoginCmd}"
  }

  def dockerPushCmd = "docker push ${imageName}:${imageTag}"
  sh "${dockerPushCmd}"
}
