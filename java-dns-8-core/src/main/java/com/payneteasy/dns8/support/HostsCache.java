package com.payneteasy.dns8.support;

import org.slf4j.Logger;

import java.io.File;
import java.net.InetAddress;
import java.util.Arrays;
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

    public void dump(Logger aLogger) {
        aLogger.info("Addresses:");
        for (Map.Entry<String, InetAddress[]> entry : addresses.entrySet()) {
            aLogger.info("  {} = {}", entry.getKey(), Arrays.asList(entry.getValue()));
        }
    }
}
