package com.justinrmiller.redismicroservices.pojo;

/**
 * @author Justin Miller (Copyright 2015)
 */
public class Feature {
    private String name;
    private Long createTimestamp;
    private String author;
    private Boolean enabled;

    public Feature() {
        // jackson
    }

    public Feature(String name, Long createTimestamp, String author, Boolean enabled) {
        this.name = name;
        this.createTimestamp = createTimestamp;
        this.author = author;
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    public String getName() {
        return name;
    }

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Feature feature = (Feature) o;

        if (author != null ? !author.equals(feature.author) : feature.author != null) return false;
        if (createTimestamp != null ? !createTimestamp.equals(feature.createTimestamp) : feature.createTimestamp != null)
            return false;
        if (enabled != null ? !enabled.equals(feature.enabled) : feature.enabled != null) return false;
        if (name != null ? !name.equals(feature.name) : feature.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (createTimestamp != null ? createTimestamp.hashCode() : 0);
        result = 31 * result + (author != null ? author.hashCode() : 0);
        result = 31 * result + (enabled != null ? enabled.hashCode() : 0);
        return result;
    }
}
