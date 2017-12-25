package org.kafka.protocol;

class ComplexType {
    final String name;
    final boolean isArray;

    ComplexType(String name, boolean isArray) {
        this.name = name;
        this.isArray = isArray;
    }

    @Override
    public String toString() {
        return name + " - " + isArray;
    }
}
