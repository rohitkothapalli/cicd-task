def VERSION = 'rohitkothapalli'
def call(Map config = [:]) {
  def namespace = config.namespace ?: 'default'
  def deploymentName = config.deploymentName
  def yamlFilePath = config.yamlFilePath
  def labels = config.labels ?: [:]

  sh "kubectl apply -f ${yamlFilePath} -n ${namespace}"

  // Set labels on the deployment
  labels.each { key, value ->
    sh "kubectl label deployment ${deploymentName} ${key}=${value} -n ${namespace}"
  }
}
