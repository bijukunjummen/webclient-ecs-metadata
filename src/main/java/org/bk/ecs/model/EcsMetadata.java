package org.bk.ecs.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class EcsMetadata {

    @JsonProperty("Cluster")
    private String cluster;
    @JsonProperty("TaskARN")
    private String taskARN;
    @JsonProperty("Family")
    private String family;
    @JsonProperty("Revision")
    private String revision;
    @JsonProperty("DesiredStatus")
    private String desiredStatus;
    @JsonProperty("KnownStatus")
    private String knownStatus;
    @JsonProperty("Containers")
    private List<EcsContainer> containers;

    private EcsMetadata(Builder builder) {
        cluster = builder.cluster;
        taskARN = builder.taskARN;
        family = builder.family;
        revision = builder.revision;
        desiredStatus = builder.desiredStatus;
        knownStatus = builder.knownStatus;
        containers = builder.containers;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public String getCluster() {
        return cluster;
    }

    public String getTaskARN() {
        return taskARN;
    }

    public String getFamily() {
        return family;
    }

    public String getRevision() {
        return revision;
    }

    public String getDesiredStatus() {
        return desiredStatus;
    }

    public String getKnownStatus() {
        return knownStatus;
    }

    public List<EcsContainer> getContainers() {
        return containers;
    }


    public static final class Builder {
        private String cluster;
        private String taskARN;
        private String family;
        private String revision;
        private String desiredStatus;
        private String knownStatus;
        private List<EcsContainer> containers;

        private Builder() {
        }

        public Builder withCluster(String cluster) {
            this.cluster = cluster;
            return this;
        }

        public Builder withTaskARN(String taskARN) {
            this.taskARN = taskARN;
            return this;
        }

        public Builder withFamily(String family) {
            this.family = family;
            return this;
        }

        public Builder withRevision(String revision) {
            this.revision = revision;
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

        public Builder withContainers(List<EcsContainer> containers) {
            this.containers = containers;
            return this;
        }

        public EcsMetadata build() {
            return new EcsMetadata(this);
        }
    }
}


