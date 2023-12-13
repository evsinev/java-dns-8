package com.payneteasy.dns8;

import com.payneteasy.dns8.support.HostsCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;
import java.util.StringTokenizer;

import static com.payneteasy.dns8.support.HostsFile.isEmpty;

public class Dns8NameService implements sun.net.spi.nameservice.NameService {

    private static final Logger LOG = LoggerFactory.getLogger(Dns8NameService.class);

    private final HostsCache hostsCache;

    public Dns8NameService() {
        String hostsFilename = System.getenv("HOSTS_FILE");
        if (isEmpty(hostsFilename)) {
            throw new IllegalStateException("No env variable HOSTS_FILE");
        }
        hostsCache = new HostsCache(new File(hostsFilename));
    }

    @Override
    public InetAddress[] lookupAllHostAddr(String aHost) throws UnknownHostException {
        LOG.debug("lookupAllHostAddr: {}", aHost);

        return hostsCache.findHostAddresses(aHost)
                .orElseThrow(() -> new UnknownHostException(aHost));
    }

    private byte[] ipToBytes(String aIp) {
        StringTokenizer st  = new StringTokenizer(aIp, ".");
        byte[]          buf = new byte[4];
        buf[0] = (byte) Integer.parseInt(st.nextToken());
        buf[1] = (byte) Integer.parseInt(st.nextToken());
        buf[2] = (byte) Integer.parseInt(st.nextToken());
        buf[3] = (byte) Integer.parseInt(st.nextToken());
        return buf;
    }

    @Override
    public String getHostByAddr(byte[] addr) throws UnknownHostException {
        LOG.debug("getHostByAddr: {}", addr);
        throw new UnknownHostException();
    }
}
