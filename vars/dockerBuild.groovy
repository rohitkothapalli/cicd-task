def VERSION = 'rohitkothapalli'
def call(Map pipelineParams) {
  def dockerfilePath = pipelineParams.dockerfilePath ?: '/Users/krvnbangarraju/.jenkins/workspace/cicd-task/Dockerfile'
  def buildArgs = pipelineParams.buildArgs ?: ''
  def dockerImageName = pipelineParams.dockerImageName ?: 'my-docker-image'
  def dockerImageTag = pipelineParams.dockerImageTag ?: 'latest'
  def dockerRegistryUrl = pipelineParams.dockerRegistryUrl ?: 'https://hub.docker.com/'
  def dockerRegistryUsername = pipelineParams.dockerRegistryUsername ?: 'krvnb'
  def dockerRegistryPassword = pipelineParams.dockerRegistryPassword ?: 'RohiT.123'

  def dockerBuildCmd = "docker build -t ${dockerImageName}:${dockerImageTag} -f ${dockerfilePath} ${buildArgs} ."
  
  echo "Building Docker image: ${dockerBuildCmd}"


  sh "${dockerBuildCmd}"

  def dockerPushCmd = "docker push ${dockerImageName}:${dockerImageTag}"
  echo "Pushing Docker image: ${dockerPushCmd}"

  withCredentials([usernamePassword(passwordVariable: 'DOCKER_REGISTRY_PASSWORD', usernameVariable: 'DOCKER_REGISTRY_USERNAME')]) {
    sh """
      docker login ${dockerRegistryUrl} -u ${dockerRegistryUsername} -p ${DOCKER_REGISTRY_PASSWORD}
      ${dockerPushCmd}
      docker logout
    """
  }
}
