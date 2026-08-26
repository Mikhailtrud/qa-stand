package com.qasandbox.backend.client;

import com.qasandbox.backend.dto.external.ExternalProfileResponse;
import com.qasandbox.backend.exception.ApiException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestClient;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withResourceNotFound;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

class ExternalProfileClientTest {

    private MockRestServiceServer server;
    private ExternalProfileClient client;

    @BeforeEach
    void setUp() {
        RestClient.Builder builder = RestClient.builder().baseUrl("http://profiles.test");
        server = MockRestServiceServer.bindTo(builder).build();
        client = new ExternalProfileClient(builder.build());
    }

    @Test
    void returnsExternalProfile() {
        server.expect(once(), requestTo("http://profiles.test/external/users/1"))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess("{\"userId\":1,\"status\":\"ACTIVE\",\"score\":85}", MediaType.APPLICATION_JSON));

        ExternalProfileResponse response = client.getProfile(1L);

        assertThat(response.status()).isEqualTo("ACTIVE");
        assertThat(response.score()).isEqualTo(85);
        server.verify();
    }

    @Test
    void mapsNotFoundResponse() {
        server.expect(requestTo("http://profiles.test/external/users/404"))
                .andRespond(withResourceNotFound());

        assertThatThrownBy(() -> client.getProfile(404L))
                .isInstanceOf(ApiException.class)
                .hasMessageContaining("not found");
    }
}
