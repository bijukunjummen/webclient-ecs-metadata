package org.bk.ecs.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.Instant;
import java.util.List;
import java.util.Map;

public class EcsContainer {
    @JsonProperty("DockerId")
    private String dockerId;

    @JsonProperty("Name")
    private String name;

    @JsonProperty("DockerName")
    private String dockerName;

    @JsonProperty("Image")
    private String image;

    @JsonProperty("ImageID")
    private String imageId;

    @JsonProperty("Labels")
    private Map<String, String> labels;

    @JsonProperty("DesiredStatus")
    private String desiredStatus;

    @JsonProperty("KnownStatus")
    private String knownStatus;

    @JsonProperty("Limits")
    private Limits limits;

    @JsonProperty("CreatedAt")
    private Instant createdAt;

    @JsonProperty("StartedAt")
    private Instant startedAt;

    @JsonProperty("Type")
    private String type;

    @JsonProperty("Networks")
    private List<Network> networks;

    //for jackson
    private EcsContainer() {

    }

    private EcsContainer(Builder builder) {
        dockerId = builder.dockerId;
        name = builder.name;
        dockerName = builder.dockerName;
        image = builder.image;
        imageId = builder.imageId;
        labels = builder.labels;
        desiredStatus = builder.desiredStatus;
        knownStatus = builder.knownStatus;
        limits = builder.limits;
        createdAt = builder.createdAt;
        startedAt = builder.startedAt;
        type = builder.type;
        networks = builder.networks;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getDockerId() {
        return dockerId;
    }

    public String getName() {
        return name;
    }

    public String getDockerName() {
        return dockerName;
    }

    public String getImage() {
        return image;
    }

    public String getImageId() {
        return imageId;
    }

    public Map<String, String> getLabels() {
        return labels;
    }

    public String getDesiredStatus() {
        return desiredStatus;
    }

    public String getKnownStatus() {
        return knownStatus;
    }

    public Limits getLimits() {
        return limits;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getStartedAt() {
        return startedAt;
    }

    public String getType() {
        return type;
    }

    public List<Network> getNetworks() {
        return networks;
    }

    public static class Limits {
        private final int cpu;
        private final int memory;

        public Limits(
                @JsonProperty("CPU") int cpu,
                @JsonProperty("Memory") int memory) {
            this.cpu = cpu;
            this.memory = memory;
        }

        public int getCpu() {
            return cpu;
        }

        public int getMemory() {
            return memory;
        }
    }

    public static class Network {
        private final String networkMode;
        private final List<String> ipv4Addresses;

        @JsonCreator
        public Network(
                @JsonProperty("NetworkMode") String networkMode,
                @JsonProperty("IPv4Addresses") List<String> ipv4Addresses) {
            this.networkMode = networkMode;
            this.ipv4Addresses = ipv4Addresses;
        }

        public String getNetworkMode() {
            return networkMode;
        }

        public List<String> getIpv4Addresses() {
            return ipv4Addresses;
        }
    }


    public static final class Builder {
        private String dockerId;
        private String name;
        private String dockerName;
        private String image;
        private String imageId;
        private Map<String, String> labels;
        private String desiredStatus;
        private String knownStatus;
        private Limits limits;
        private Instant createdAt;
        private Instant startedAt;
        private String type;
        private List<Network> networks;

        private Builder() {
        }

        public Builder withDockerId(String dockerId) {
            this.dockerId = dockerId;
            return this;
        }

        public Builder withName(String name) {
            this.name = name;
            return this;
        }

        public Builder withDockerName(String dockerName) {
            this.dockerName = dockerName;
            return this;
        }

        public Builder withImage(String image) {
            this.image = image;
            return this;
        }

        public Builder withImageId(String imageId) {
            this.imageId = imageId;
            return this;
        }

        public Builder withLabels(Map<String, String> labels) {
            this.labels = labels;
            return this;
        }

        public Builder withDesiredStatus(String desiredStatus) {
            this.desiredStatus = desiredStatus;
            return this;
        }

        public Builder withKnownStatus(String knownStatus) {
            this.knownStatus = knownStatus;
            return this;
        }

        public Builder withLimits(Limits limits) {
            this.limits = limits;
            return this;
        }

        public Builder withCreatedAt(Instant createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder withStartedAt(Instant startedAt) {
            this.startedAt = startedAt;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withNetworks(List<Network> networks) {
            this.networks = networks;
            return this;
        }

        public EcsContainer build() {
            return new EcsContainer(this);
        }
    }
}
