package com.payneteasy.dns8;

import com.payneteasy.dns8.support.HostsCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Optional;
import java.util.ServiceConfigurationError;

import static com.payneteasy.dns8.support.HostsFile.isEmpty;

public class Dns8NameService implements sun.net.spi.nameservice.NameService {

    private static final Logger LOG = LoggerFactory.getLogger(Dns8NameService.class);

    private final HostsCache hostsCache;

    public Dns8NameService() {
        String hostsFilename = System.getenv("HOSTS_FILE");
        if (isEmpty(hostsFilename)) {
            throw new ServiceConfigurationError("No env variable HOSTS_FILE");
        }
        LOG.info("Loading hosts from file {}", hostsFilename);
        hostsCache = new HostsCache(new File(hostsFilename));
        hostsCache.dump(LOG);
    }

    @Override
    public InetAddress[] lookupAllHostAddr(String aHost) throws UnknownHostException {
        Optional<InetAddress[]> addresses = hostsCache.findHostAddresses(aHost);

        if (addresses.isPresent()) {
            if (LOG.isDebugEnabled()) {
                LOG.debug("Found {} for {}", aHost, Arrays.asList(addresses.get()));
            }
            return addresses.get();
        } else {
            LOG.debug("Not found {}", aHost);
            throw new UnknownHostException(aHost);
        }
    }

    @Override
    public String getHostByAddr(byte[] addr) throws UnknownHostException {
        if(LOG.isDebugEnabled()) {
            LOG.debug("getHostByAddr: {}", toIpAddress(addr));
        }
        throw new UnknownHostException();
    }

    private static String toIpAddress(byte[] addr) {
        StringBuilder sb = new StringBuilder();
        if(addr != null) {
            for (byte b : addr) {
                sb.append(".");
                sb.append(b);
            }
        }
        return sb.toString();
    }
}
