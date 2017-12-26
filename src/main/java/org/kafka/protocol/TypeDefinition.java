package org.kafka.protocol;

class TypeDefinition {
    final String name;
    final boolean isArray;
    final boolean isComplex;

    TypeDefinition(String name, boolean isArray, boolean isComplex) {
        this.name = name;
        this.isArray = isArray;
        this.isComplex = isComplex;
    }

    @Override
    public String toString() {
        return name + " - " + isArray + " - " + isComplex;
    }
}
