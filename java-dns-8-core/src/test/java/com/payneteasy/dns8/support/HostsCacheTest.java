package com.payneteasy.dns8.support;

import lombok.SneakyThrows;
import org.junit.Test;

import java.io.File;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class HostsCacheTest {

    private final HostsCache hostsCache = new HostsCache(new File("./src/test/resources/hosts.txt"));

    @Test
    public void multi_address() throws UnknownHostException {
        Optional<InetAddress[]> host1 = hostsCache.findHostAddresses("host1");
        assertThat(host1).isNotNull();
        assertThat(host1.isPresent()).isTrue();
        assertThat(host1.get().length).isEqualTo(2);
        assertThat(host1.get()[0]).isEqualTo(Inet4Address.getByAddress(new byte[]{1, 2, 1, 1}));
        assertThat(host1.get()[1]).isEqualTo(Inet4Address.getByAddress(new byte[]{1, 2, 2, 1}));
    }

    @Test
    public void one_address() throws UnknownHostException {
        Optional<InetAddress[]> host4 = hostsCache.findHostAddresses("host4");
        assertThat(host4).isNotNull();
        assertThat(host4.isPresent()).isTrue();
        assertThat(host4.get().length).isEqualTo(1);
        assertThat(host4.get()[0]).isEqualTo(Inet4Address.getByAddress(new byte[]{1, 2, 3, 4}));
    }

}