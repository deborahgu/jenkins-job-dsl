package analytics
import static org.edx.jenkins.dsl.AnalyticsConstants.common_multiscm
import static org.edx.jenkins.dsl.AnalyticsConstants.common_parameters
import static org.edx.jenkins.dsl.AnalyticsConstants.to_date_interval_parameter
import static org.edx.jenkins.dsl.AnalyticsConstants.common_log_rotator
import static org.edx.jenkins.dsl.AnalyticsConstants.common_wrappers
import static org.edx.jenkins.dsl.AnalyticsConstants.common_publishers
import static org.edx.jenkins.dsl.AnalyticsConstants.common_triggers

class UserLocationByCourse {
    public static def job = { dslFactory, allVars ->
        allVars.get('ENVIRONMENTS').each { environment, env_config ->
            dslFactory.job("user-location-by-course-$environment") {
                logRotator common_log_rotator(allVars, env_config)
                parameters common_parameters(allVars, env_config)
                parameters to_date_interval_parameter(allVars)
                multiscm common_multiscm(allVars)
                triggers common_triggers(allVars, env_config)
                wrappers common_wrappers(allVars)
                publishers common_publishers(allVars)
                steps {
                    shell(dslFactory.readFileFromWorkspace('dataeng/resources/user-location-by-course.sh'))
                    if (env_config.get('SNITCH')) {
                        shell('curl https://nosnch.in/' + env_config.get('SNITCH'))
                    }
                    if (env_config.get('OPSGENIE_HEARTBEAT_NAME') && allVars.get('OPSGENIE_HEARTBEAT_KEY')){
                        shell("curl -X GET 'https://api.opsgenie.com/v2/heartbeats/" + env_config.get('OPSGENIE_HEARTBEAT_NAME') + "/ping' -H 'Authorization: GenieKey " + allVars.get('OPSGENIE_HEARTBEAT_KEY') + "'")
                    }
               }
            }
        }
    }
}
