package io.skcloud.goblintest.config;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.auth.credentials.WebIdentityTokenFileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.apigateway.ApiGatewayClient;
import software.amazon.awssdk.services.apigatewayv2.ApiGatewayV2Client;
import software.amazon.awssdk.services.cloudwatchlogs.CloudWatchLogsClient;
import software.amazon.awssdk.services.waf.WafClient;
import software.amazon.awssdk.services.wafv2.Wafv2Client;

@Component
public abstract class AwsConfig {
    public abstract ApiGatewayV2Client getAwsV2Client();
    public abstract ApiGatewayClient getAwsClient();
    public abstract WafClient getWafClient();
    public abstract Wafv2Client getWafv2Client();
    public abstract CloudWatchLogsClient getCloudWatchLogsClient();
}
@Component
@Profile("k8s")
class K8sAwsConfig extends AwsConfig {
    public ApiGatewayV2Client getAwsV2Client(){
        return ApiGatewayV2Client.builder()
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
    public ApiGatewayClient getAwsClient(){
        return ApiGatewayClient.builder()
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
    public WafClient getWafClient(){
        return WafClient.builder().region(Region.AP_NORTHEAST_2).build();
    }

    public Wafv2Client getWafv2Client(){
        return Wafv2Client.builder()
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .region(Region.AP_NORTHEAST_2)
                .build();
    }

    @Override
    public CloudWatchLogsClient getCloudWatchLogsClient() {
        return CloudWatchLogsClient.builder()
                .credentialsProvider(WebIdentityTokenFileCredentialsProvider.create())
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
}
@Component
@Profile("default")
class DefaultAwsConfig extends AwsConfig{
    public ApiGatewayV2Client getAwsV2Client(){
        return ApiGatewayV2Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
    public ApiGatewayClient getAwsClient(){
        return ApiGatewayClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
    public WafClient getWafClient(){
        return WafClient.builder().region(Region.AP_NORTHEAST_2).build();
    }

    public Wafv2Client getWafv2Client(){
        return Wafv2Client.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();
    }

    @Override
    public CloudWatchLogsClient getCloudWatchLogsClient() {
        return CloudWatchLogsClient.builder()
                .region(Region.AP_NORTHEAST_2)
                .build();
    }
}
