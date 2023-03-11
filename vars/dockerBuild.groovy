def VERSION = 'rohitkothapalli'
def call(Map pipelineParams) {
  def dockerfilePath = pipelineParams.dockerfilePath ?: './Dockerfile'
  def buildArgs = pipelineParams.buildArgs ?: ''
  def dockerImageName = pipelineParams.dockerImageName ?: 'my-docker-image'
  def dockerImageTag = pipelineParams.dockerImageTag ?: 'latest'
  def dockerRegistryUrl = pipelineParams.dockerRegistryUrl ?: 'docker.io'
  def dockerRegistryUsername = pipelineParams.dockerRegistryUsername ?: ''
  def dockerRegistryPassword = pipelineParams.dockerRegistryPassword ?: ''

  def dockerBuildCmd = "docker build -t ${dockerImageName}:${dockerImageTag} -f ${dockerfilePath} ${buildArgs} ."
  
  echo "Building Docker image: ${dockerBuildCmd}"


  sh "${dockerBuildCmd}"

  def dockerPushCmd = "docker push ${dockerImageName}:${dockerImageTag}"
  echo "Pushing Docker image: ${dockerPushCmd}"

  withCredentials([usernamePassword(credentialsId: 'docker-registry-creds', passwordVariable: 'DOCKER_REGISTRY_PASSWORD', usernameVariable: 'DOCKER_REGISTRY_USERNAME')]) {
    sh """
      docker login ${dockerRegistryUrl} -u ${dockerRegistryUsername} -p ${DOCKER_REGISTRY_PASSWORD}
      ${dockerPushCmd}
      docker logout
    """
  }
}
