package com.qasandbox.backend.client;

import com.qasandbox.backend.dto.external.ExternalProfileResponse;
import com.qasandbox.backend.exception.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClient;

@Component
public class ExternalProfileClient {

    private final RestClient restClient;

    public ExternalProfileClient(RestClient externalProfileRestClient) {
        this.restClient = externalProfileRestClient;
    }

    public ExternalProfileResponse getProfile(Long userId) {
        try {
            return restClient.get()
                    .uri("/external/users/{id}", userId)
                    .retrieve()
                    .body(ExternalProfileResponse.class);
        } catch (HttpClientErrorException.NotFound exception) {
            throw new ApiException(HttpStatus.NOT_FOUND, "External profile not found for user " + userId);
        } catch (HttpServerErrorException exception) {
            throw new ApiException(HttpStatus.BAD_GATEWAY, "External profile service failed");
        } catch (ResourceAccessException exception) {
            throw new ApiException(HttpStatus.GATEWAY_TIMEOUT, "External profile service timed out");
        }
    }
}
