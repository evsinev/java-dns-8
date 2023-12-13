package com.payneteasy.dns8.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Optional;

import static com.payneteasy.dns8.support.HostsFile.loadHostsFile;

public class HostsCache {

    private static final Logger LOG = LoggerFactory.getLogger( HostsCache.class );

    private final Map<String, InetAddress[]> addresses;

    public HostsCache(File aFile) {
        addresses = new HostAddresses(loadHostsFile(aFile)).toMap();
    }

    public Optional<InetAddress[]> findHostAddresses(String aName) {
        return Optional.ofNullable(addresses.get(aName));
    }

    public void dump() {
        LOG.info("Addresses:");
        for (Map.Entry<String, InetAddress[]> entry : addresses.entrySet()) {
            LOG.info("  {} = {}", entry.getKey(), Arrays.asList(entry.getValue()));
        }
    }
}
