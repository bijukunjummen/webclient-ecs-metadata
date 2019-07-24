package org.bk.ecs;

import com.github.tomakehurst.wiremock.WireMockServer;
import org.assertj.core.api.Assertions;
import org.bk.ecs.model.EcsContainer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StreamUtils;
import org.springframework.web.reactive.function.client.WebClient;

import java.net.URL;
import java.nio.charset.Charset;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class EcsMetadataClientTest {

    private static final WireMockServer WIREMOCK_SERVER =
            new WireMockServer(wireMockConfig().dynamicPort());

    private EcsMetadataClient ecsMetadataClient;

    @BeforeEach
    public void beforeEachTest() {
        final WebClient.Builder webClientBuilder = WebClient.builder();
        this.ecsMetadataClient = new EcsMetadataClient(webClientBuilder, WIREMOCK_SERVER.baseUrl());
    }

    @Test
    public void testGetMetadata() throws Exception {
        URL sampleMetaDataUrl = ResourceUtils.getURL("classpath:ecs-meta-data-sample.json");
        String sampleMetadata = StreamUtils.copyToString(sampleMetaDataUrl.openStream(), Charset.forName("UTF-8"));

        WIREMOCK_SERVER.stubFor(get(urlEqualTo("/"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", "application/json")
                        .withBody(sampleMetadata)));

        EcsContainer metadata = ecsMetadataClient.getContainerMetadata();

        assertThat(metadata.getDockerId()).isEqualTo("sample-docker-id");
        assertThat(metadata.getName()).isEqualTo("nginx-curl");
        assertThat(metadata.getDockerName()).isEqualTo("ecs-nginx-5-nginx-curl-ccccb9f49db0dfe0d901");
        assertThat(metadata.getImage()).isEqualTo("nrdlngr/nginx-curl");
        assertThat(metadata.getImageId()).isEqualTo("sha256:2e00ae64383cfc865ba0a2ba37f61b50a120d2d9378559dcd458dc0de47bc165");
        assertThat(metadata.getLabels()).hasSize(5);
        assertThat(metadata.getDesiredStatus()).isEqualTo("RUNNING");
        assertThat(metadata.getKnownStatus()).isEqualTo("RUNNING");
        assertThat(metadata.getLimits().getCpu()).isEqualTo(512);
        assertThat(metadata.getLimits().getMemory()).isEqualTo(512);
        assertThat(metadata.getType()).isEqualTo("NORMAL");
        assertThat(metadata.getNetworks().get(0).getIpv4Addresses().get(0)).isEqualTo("10.0.2.106");
        assertThat(metadata.getNetworks().get(0).getNetworkMode()).isEqualTo("awsvpc");
    }

    @BeforeAll
    public static void beforeAll() {
        WIREMOCK_SERVER.start();
    }

    @AfterAll
    public static void afterAll() {
        WIREMOCK_SERVER.stop();
    }
}
