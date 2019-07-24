package org.bk.ecs;

import org.bk.ecs.model.EcsContainer;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Responsible for querying ECS metadata endpoint and populating a set of properties from it
 */
public class EcsMetadataClient {

    private WebClient.Builder webClientBuilder;

    private static final String ECS_CONTAINER_METADATA_URI = "ECS_CONTAINER_METADATA_URI";

    public EcsMetadataClient() {
        this(WebClient.builder(), System.getenv(ECS_CONTAINER_METADATA_URI));
    }

    public EcsMetadataClient(WebClient.Builder webClientBuilder, String ecsMetadataBaseUrl) {
        this.webClientBuilder = webClientBuilder
                .baseUrl(ecsMetadataBaseUrl);
    }

    public EcsContainer getContainerMetadata() {
        EcsContainer container = webClientBuilder.build()
                .get()
                .exchange()
                .flatMap(resp -> resp.bodyToMono(EcsContainer.class)).block();

        return container;
    }
}
