def VERSION = 'rohitkothapalli'
def call(String scmType, String scmUrl) {
    switch (scmType.toLowerCase()) {
        case 'git':
            checkoutGit(scmUrl)
            break
        case 'svn':
            checkoutSvn(scmUrl)
            break
        case 'mercurial':
            checkoutMercurial(scmUrl)
            break
        default:
            throw new IllegalArgumentException("Unsupported SCM type: ${scmType}")
    }
}

def checkoutGit(String scmUrl) {
    stage('Checkout (Git)') {
        checkout([$class: 'GitSCM', branches: [[name: '*/master']], userRemoteConfigs: [[url: scmUrl]]])
    }
}

def checkoutSvn(String scmUrl) {
    stage('Checkout (SVN)') {
        checkout([$class: 'SubversionSCM', locations: [[credentialsId: 'my-svn-creds', remote: scmUrl]], workspaceUpdater: [$class: 'UpdateUpdater']])
    }
}

def checkoutMercurial(String scmUrl) {
    stage('Checkout (Mercurial)') {
        checkout([$class: 'MercurialSCM', credentialsId: 'my-hg-creds', source: scmUrl])
    }
}
