/*
 * Copyright 2016 Crown Copyright
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
package gaffer.serialisation.implementation;

import gaffer.serialisation.Serialisation;
import gaffer.serialisation.implementation.raw.CompactRawIntegerSerialiser;
import gaffer.serialisation.implementation.raw.CompactRawLongSerialiser;

/**
 * This class is used to serialise and deserialise a boolean value
 */
public class SerialisationFactory {
    private static final Serialisation[] SERIALISERS = new Serialisation[]{
            new BooleanSerialiser(),
            new DateSerialiser(),
            new DoubleSerialiser(),
            new FloatSerialiser(),
            new CompactRawIntegerSerialiser(),
            new CompactRawLongSerialiser(),
            new StringSerialiser(),
            new TreeSetStringSerialiser(),
            new JavaSerialiser()
    };

    public Serialisation getSerialiser(final Class<?> objClass) {
        if (null == objClass) {
            throw new IllegalArgumentException("Object class for serialising is required");
        }

        for (Serialisation serialiser : SERIALISERS) {
            if (serialiser.canHandle(objClass)) {
                return serialiser;
            }
        }

        throw new IllegalArgumentException("No serialiser found for object class: " + objClass);
    }
}
