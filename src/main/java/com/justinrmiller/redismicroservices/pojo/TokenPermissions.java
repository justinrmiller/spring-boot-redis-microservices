package com.justinrmiller.redismicroservices.pojo;

import java.util.List;

/**
 * @author Justin Miller (Copyright 2015)
 */
public class TokenPermissions {
    private Long createTimestamp;
    private List<String> permissions;

    public TokenPermissions() {
        // jackson
    }

    public TokenPermissions(Long createTimestamp, List<String> permissions) {
        this.createTimestamp = createTimestamp;
        this.permissions = permissions;
    }

    public Long getCreateTimestamp() {
        return createTimestamp;
    }

    public List<String> getPermissions() {
        return permissions;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TokenPermissions that = (TokenPermissions) o;

        if (createTimestamp != null ? !createTimestamp.equals(that.createTimestamp) : that.createTimestamp != null)
            return false;
        if (permissions != null ? !permissions.equals(that.permissions) : that.permissions != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = createTimestamp != null ? createTimestamp.hashCode() : 0;
        result = 31 * result + (permissions != null ? permissions.hashCode() : 0);
        return result;
    }
}
