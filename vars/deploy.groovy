def VERSION = 'rohitkothapalli'
def call(Map config = [:]) {
  def namespace = config.namespace ?: 'default'
  def deploymentName = config.deploymentName
  def yamlFilePath = config.yamlFilePath
  def labels = config.labels ?: [:]

  sh "kubectl create ns ${namespace}"
  sh "kubectl apply -f ${yamlFilePath} -n ${namespace}"
  sh "kubectl get pods"

}
