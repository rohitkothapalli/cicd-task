def VERSION = 'rohitkothapalli'

def call(Map pipelineParams) {
  
  def dockerfilePath = pipelineParams.dockerfilePath ?: '/var/jenkins_home/workspace/cicdtask/Dockerfile'
  def dockerImageName = pipelineParams.dockerImageName ?: 'my-docker-image'
  def dockerImageTag = pipelineParams.dockerImageTag ?: 'latest'
  def dockerRegistryUrl = pipelineParams.dockerRegistryUrl ?: 'docker.io'
  def dockerRegistryUsername = pipelineParams.dockerRegistryUsername ?: ''
  def dockerRegistryPassword = pipelineParams.dockerRegistryPassword ?: ''


  def dockerBuildCmd = "docker build -t krvnb/${dockerImageName}:${dockerImageTag} -f ${dockerfilePath} . "

  echo "Building Docker image: ${dockerBuildCmd}"

  
  sh "${dockerBuildCmd}"


  def dockerPushCmd = "docker push krvnb/${dockerImageName}:${dockerImageTag} "
  echo "Pushing Docker image: ${dockerPushCmd}"

  
  withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'DOCKER_REGISTRY_PASSWORD', usernameVariable: 'DOCKER_REGISTRY_USERNAME')]) {
    sh """
      docker login ${dockerRegistryUrl} -u ${dockerRegistryUsername} -p ${DOCKER_REGISTRY_PASSWORD}
      ${dockerPushCmd}
    """
  }
}
