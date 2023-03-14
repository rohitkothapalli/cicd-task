def VERSION = 'rohitkothapalli'
def call(Map config = [:]) {
  def namespace = config.namespace ?: 'default'
  def deploymentName = config.deploymentName
  def yamlFilePath = config.yamlFilePath
 

 
  sh "kubectl apply -f ${yamlFilePath} -n ${namespace}"
  sh "kubens cicdtask"
  sh "kubectl get pods "

}
