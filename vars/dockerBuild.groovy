def VERSION = 'rohitkothapalli'
def call(String dockerfilePath = '/Users/krvnbangarraju/.jenkins/workspace/cicd-task/', String imageName = 'my-image', String imageTag = 'latest') {
  def dockerBuildCmd = "docker build -t ${imageName}:${imageTag} ${dockerfilePath}"
  sh "${dockerBuildCmd}"
}
