/*
 * Copyright 2018 Marco Helmich
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.mhelmich.kafka.protocol.generator;

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
