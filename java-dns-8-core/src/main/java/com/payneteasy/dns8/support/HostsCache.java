package com.payneteasy.dns8.support;

import java.io.File;
import java.net.InetAddress;
import java.util.Map;
import java.util.Optional;

import static com.payneteasy.dns8.support.HostsFile.loadHostsFile;

public class HostsCache {

    private final Map<String, InetAddress[]> addresses;

    public HostsCache(File aFile) {
        addresses = new HostAddresses(loadHostsFile(aFile)).toMap();
    }

    public Optional<InetAddress[]> findHostAddresses(String aName) {
        return Optional.ofNullable(addresses.get(aName));
    }
}
