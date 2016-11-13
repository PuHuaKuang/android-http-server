/**************************************************
 * Android Web Server
 * Based on JavaLittleWebServer (2008)
 * <p/>
 * Copyright (c) Piotr Polak 2016-2016
 **************************************************/

package ro.polak.webserver.protocol.serializer;

/**
 * Generic serializer interface.
 *
 * @author Piotr Polak piotr [at] polak [dot] ro
 * @since 201611
 */
public interface Serializer<T> {

    /**
     * Produced serialized string representation of the input attribute.
     *
     * @param input
     * @return
     */
    String serialize(T input);
}
