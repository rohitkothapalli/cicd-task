def VERSION = 'rohitkothapalli'

def call(Map pipelineParams) {
  // Define some default values for the pipeline parameters
  def dockerfilePath = pipelineParams.dockerfilePath ?: '/Users/krvnbangarraju/.jenkins/workspace/cicd-task/Dockerfile'
  def buildArgs = pipelineParams.buildArgs ?: ''
  def dockerImageName = pipelineParams.dockerImageName ?: 'my-docker-image'
  def dockerImageTag = pipelineParams.dockerImageTag ?: 'latest'
  def dockerRegistryUrl = pipelineParams.dockerRegistryUrl ?: 'docker.io'
  def dockerRegistryUsername = pipelineParams.dockerRegistryUsername ?: ''
  def dockerRegistryPassword = pipelineParams.dockerRegistryPassword ?: ''

  // Define the Docker build command
  def dockerBuildCmd = "docker build -t krvnb/${dockerImageName}:${dockerImageTag} -f ${dockerfilePath} ${buildArgs} ."

  // Log the Docker build command to the Jenkins console
  echo "Building Docker image: ${dockerBuildCmd}"

  // Execute the Docker build command
  sh "${dockerBuildCmd}"

  // Log the Docker push command to the Jenkins console
  def dockerPushCmd = "docker push krvnb/${dockerImageName}:${dockerImageTag} "
  echo "Pushing Docker image: ${dockerPushCmd}"

  // Execute the Docker push command
  withCredentials([usernamePassword(credentialsId: 'dockerhub', passwordVariable: 'DOCKER_REGISTRY_PASSWORD', usernameVariable: 'DOCKER_REGISTRY_USERNAME')]) {
    sh """
      docker login ${dockerRegistryUrl} -u ${dockerRegistryUsername} -p ${DOCKER_REGISTRY_PASSWORD}
      ${dockerPushCmd}
      docker logout
    """
  }
}
