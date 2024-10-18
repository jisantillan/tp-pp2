package org.domingus.app;

import java.util.Objects;

public class ClassroomAssignment {

    private Integer version;
    public ClassroomAssignment(Integer version) {
        this.version = version;
    }

    public Integer getVersion() {
        return version;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        ClassroomAssignment that = (ClassroomAssignment) object;
        return Objects.equals(version, that.version);
    }

    @Override
    public int hashCode() {
        return Objects.hash(version);
    }
}
