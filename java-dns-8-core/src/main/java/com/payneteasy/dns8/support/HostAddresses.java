package com.payneteasy.dns8.support;

import java.net.InetAddress;
import java.util.*;
import java.util.stream.Collectors;

public class HostAddresses {

    private final Map<String, List<InetAddress>> addresses;

    public HostAddresses(List<HostAddress> aList) {
        addresses = toAddresses(aList);
    }

    private static Map<String, List<InetAddress>> toAddresses(List<HostAddress> aList) {
        Map<String, List<InetAddress>> map = new HashMap<>();
        for (HostAddress hostAddress : aList) {
            List<InetAddress> inetAddresses = map.computeIfAbsent(hostAddress.getHost(), k -> new ArrayList<>());
            inetAddresses.add(hostAddress.getInetAddress());
        }
        return map;
    }

    public Map<String, InetAddress[]> toMap() {
        return addresses.entrySet()
                .stream()
                .collect(Collectors.toMap(
                        Map.Entry::getKey
                        , e -> e.getValue().toArray(new InetAddress[0])
                ));
    }
}
