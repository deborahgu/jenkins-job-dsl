package platform

import org.yaml.snakeyaml.Yaml
import static org.edx.jenkins.dsl.JenkinsPublicConstants.GENERAL_PRIVATE_JOB_SECURITY
import static org.edx.jenkins.dsl.JenkinsPublicConstants.JENKINS_PUBLIC_LOG_ROTATOR
import static org.edx.jenkins.dsl.JenkinsPublicConstants.GHPRB_CANCEL_BUILDS_ON_UPDATE

/* stdout logger */
Map config = [:]
Binding bindings = getBinding()
config.putAll(bindings.getVariables())
PrintStream out = config['out']

/* Map to hold the k:v pairs parsed from the secret file */
Map ghprbMap = [:]
try {
    out.println('Parsing secret YAML file')
    String ghprbConfigContents = new File("${GHPRB_SECRET}").text
    Yaml yaml = new Yaml()
    ghprbMap = yaml.load(ghprbConfigContents)
    out.println('Successfully parsed secret YAML file')
}
catch (any) {
    out.println('Jenkins DSL: Error parsing secret YAML file')
    out.println('Exiting with error code 1')
    return 1
}

Map publicBokchoyJobConfig = [
    open: true,
    jobName: 'edx-platform-bokchoy-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/bokchoy',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+bokchoy.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'bokchoy',
    pythonVersion: '3.5',
]

Map django20BokchoyJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.0-bokchoy-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.0/bokchoy',
    onlyTriggerPhrase: true,
    triggerPhrase: /.*jenkins\W+run\W+django20\W+bokchoy.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'bokchoy',
    pythonVersion: '3.5',
    toxEnv: 'py35-django20',
]

Map django21BokchoyJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.1-bokchoy-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.1/bokchoy',
    onlyTriggerPhrase: true,
    triggerPhrase: /.*jenkins\W+run\W+django21\W+bokchoy.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'bokchoy',
    pythonVersion: '3.5',
    toxEnv: 'py35-django21',
]

Map django22BokchoyJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.2-bokchoy-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.2/bokchoy',
    onlyTriggerPhrase: true,
    triggerPhrase: /.*jenkins\W+run\W+django22\W+bokchoy.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'bokchoy',
    pythonVersion: '3.5',
    toxEnv: 'py35-django22',
]

Map privateBokchoyJobConfig = [
    open: false,
    jobName: 'edx-platform-bokchoy-pipeline-pr_private',
    repoName: 'edx-platform-private',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/bokchoy',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+bokchoy.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'bokchoy',
    pythonVersion: '3.5',
]

Map publicBokchoyIronwoodJobConfig = [
    open: true,
    jobName: 'ironwood-bokchoy-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/bokchoy',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+bokchoy.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'bokchoy',
    pythonVersion: '2.7',
]

Map privateBokchoyIronwoodJobConfig = [
    open: false,
    jobName: 'ironwood-bokchoy-pipeline-pr_private',
    repoName: 'edx-platform-private',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/bokchoy',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+bokchoy.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'bokchoy',
    pythonVersion: '2.7',
]

Map publicLettuceIronwoodJobConfig = [
    open: true,
    jobName: 'ironwood-lettuce-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/lettuce',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+lettuce.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'lettuce',
    pythonVersion: '2.7',
]

Map privateLettuceIronwoodJobConfig = [
    open: false,
    jobName: 'ironwood-lettuce-pipeline-pr_private',
    repoName: 'edx-platform-private',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/lettuce',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+lettuce.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'lettuce',
    pythonVersion: '2.7',
]

Map publicPythonJobConfig = [
    open: true,
    jobName: 'edx-platform-python-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/python',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+python.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'python',
    pythonVersion: '3.5',
]

Map django20PythonJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.0-python-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.0/python',
    onlyTriggerPhrase: true,
    triggerPhrase: /.*jenkins\W+run\W+django20\W+python.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'python',
    pythonVersion: '3.5',
    toxEnv: 'py35-django20',
]

Map django21PythonJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.1-python-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.1/python',
    onlyTriggerPhrase: true,
    triggerPhrase: /.*jenkins\W+run\W+django21\W+python.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'python',
    pythonVersion: '3.5',
    toxEnv: 'py35-django21',
]

Map django22PythonJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.2-python-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.2/python',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+django22\W+python.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'python',
    pythonVersion: '3.5',
    toxEnv: 'py35-django22',
]

Map privatePythonJobConfig = [
    open: false,
    jobName: 'edx-platform-python-pipeline-pr_private',
    repoName: 'edx-platform-private',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/python',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+python.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'python',
    pythonVersion: '3.5',
]

Map publicPythonIronwoodJobConfig = [
    open: true,
    jobName: 'ironwood-python-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/python',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+python.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'python',
    pythonVersion: '2.7',
]

Map privatePythonIronwoodJobConfig = [
    open: false,
    jobName: 'ironwood-python-pipeline-pr_private',
    repoName: 'edx-platform-private',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/python',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+python.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'python',
    pythonVersion: '2.7',
]

Map publicQualityJobConfig = [
    open: true,
    jobName: 'edx-platform-quality-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/quality',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+quality.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'quality',
    pythonVersion: '3.5',
]

Map django20QualityJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.0-quality-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.0/quality',
    onlyTriggerPhrase: true,
    triggerPhrase: /.*jenkins\W+run\W+django20\W+quality.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'quality',
    pythonVersion: '3.5',
    toxEnv: 'py35-django20',
]

Map django21QualityJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.1-quality-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.1/quality',
    onlyTriggerPhrase: true,
    triggerPhrase: /.*jenkins\W+run\W+django21\W+quality.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'quality',
    pythonVersion: '3.5',
    toxEnv: 'py35-django21',
]

Map django22QualityJobConfig = [
    open: true,
    jobName: 'edx-platform-django-2.2-quality-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/django-2.2/quality',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+django22\W+quality.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'quality',
    pythonVersion: '3.5',
    toxEnv: 'py35-django22',
]

Map privateQualityJobConfig = [
    open: false,
    jobName: 'edx-platform-quality-pipeline-pr_private',
    repoName: 'edx-platform-private',
    whitelistBranchRegex: /^((?!open-release\/).)*$/,
    context: 'jenkins/quality',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*jenkins\W+run\W+quality.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'quality',
    pythonVersion: '3.5',
]

Map publicQualityIronwoodJobConfig = [
    open: true,
    jobName: 'ironwood-quality-pipeline-pr',
    repoName: 'edx-platform',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/quality',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+quality.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'quality',
    pythonVersion: '2.7',
]

Map privateQualityIronwoodJobConfig = [
    open: false,
    jobName: 'ironwood-quality-pipeline-pr_private',
    repoName: 'edx-platform-private',
    whitelistBranchRegex: /open-release\/ironwood.master/,
    context: 'jenkins/ironwood/quality',
    onlyTriggerPhrase: false,
    triggerPhrase: /.*ironwood\W+run\W+quality.*/,
    jenkinsFileDir: 'scripts/Jenkinsfiles',
    jenkinsFileName: 'quality',
    pythonVersion: '2.7',
]

List jobConfigs = [
    publicBokchoyJobConfig,
    django20BokchoyJobConfig,
    django21BokchoyJobConfig,
    django22BokchoyJobConfig,
    privateBokchoyJobConfig,
    publicBokchoyIronwoodJobConfig,
    privateBokchoyIronwoodJobConfig,
    publicLettuceIronwoodJobConfig,
    privateLettuceIronwoodJobConfig,
    publicPythonJobConfig,
    django20PythonJobConfig,
    django21PythonJobConfig,
    django22PythonJobConfig,
    privatePythonJobConfig,
    publicPythonIronwoodJobConfig,
    privatePythonIronwoodJobConfig,
    publicQualityJobConfig,
    django20QualityJobConfig,
    django21QualityJobConfig,
    django22QualityJobConfig,
    privateQualityJobConfig,
    publicQualityIronwoodJobConfig,
    privateQualityIronwoodJobConfig
]

/* Iterate over the job configurations */
jobConfigs.each { jobConfig ->

    // This is the job DSL responsible for creating the main pipeline job.
    pipelineJob(jobConfig.jobName) {

        definition {

            if (!jobConfig.open.toBoolean()) {
                authorization GENERAL_PRIVATE_JOB_SECURITY()
            }
            properties {
                githubProjectUrl("https://github.com/edx/${jobConfig.repoName}/")
            }
            logRotator JENKINS_PUBLIC_LOG_ROTATOR(7)
            environmentVariables(
                REPO_NAME: "${jobConfig.repoName}",
                TOX_ENV: "${jobConfig.toxEnv}",
                PYTHON_VERSION: "${jobConfig.pythonVersion}"
            )

            triggers {
                githubPullRequest {
                    admins(ghprbMap['admin'])
                    useGitHubHooks()
                    triggerPhrase(jobConfig.triggerPhrase)
                    onlyTriggerPhrase(jobConfig.onlyTriggerPhrase)
                    userWhitelist(ghprbMap['userWhiteList'])
                    orgWhitelist(ghprbMap['orgWhiteList'])
                    whiteListTargetBranches([jobConfig.whitelistBranchRegex])
                    extensions {
                        commitStatus {
                            context(jobConfig.context)
                        }
                    }
                }
            }

            configure GHPRB_CANCEL_BUILDS_ON_UPDATE(false)

            cpsScm {
                scm {
                    git {
                        extensions {
                            cleanBeforeCheckout()
                            cloneOptions {
                                honorRefspec(true)
                                noTags(true)
                                shallow(true)
                            }
                            // To speed up builds, do a sparse checkout of just
                            // the files needed to run the pipeline. However, in
                            // case old branches/forks trigger this job, check
                            // out the 'scripts' directory to avoid the case
                            // where Jenkins tries to do a sparse check out of
                            // non-existent files (and corrupts the git state)
                            sparseCheckoutPaths {
                                sparseCheckoutPaths {
                                    sparseCheckoutPath {
                                        path('scripts')
                                    }
                                }
                            }
                        }
                        remote {
                            credentials('jenkins-worker')
                            github("edx/${jobConfig.repoName}", 'ssh', 'github.com')
                            refspec('+refs/pull/${ghprbPullId}/*:refs/remotes/origin/pr/${ghprbPullId}/*')
                            branch('\${sha1}')
                        }
                    }
                }
                scriptPath(jobConfig.jenkinsFileDir + '/' + jobConfig.jenkinsFileName)
            }
        }
    }
}
