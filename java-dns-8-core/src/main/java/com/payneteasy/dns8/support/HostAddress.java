package com.payneteasy.dns8.support;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.StringTokenizer;

import static lombok.AccessLevel.PRIVATE;

@Data
@FieldDefaults(makeFinal = true, level = PRIVATE)
@Builder
public class HostAddress {
    String host;
    String address;

    public InetAddress getInetAddress() {
        try {
            return Inet4Address.getByAddress(ipToBytes(address));
        } catch (UnknownHostException e) {
            throw new IllegalStateException("Cannot parse ip address " + address);
        }
    }

    private byte[] ipToBytes(String aIp) {
        StringTokenizer st  = new StringTokenizer(aIp, ".");
        byte[]          buf = new byte[4];
        buf[0] = nextByte(st);
        buf[1] = nextByte(st);
        buf[2] = nextByte(st);
        buf[3] = nextByte(st);
        return buf;
    }

    private static byte nextByte(StringTokenizer st) {
        return (byte) Integer.parseInt(st.nextToken());
    }

}
