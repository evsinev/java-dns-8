package com.payneteasy.dns8.support;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.ServiceConfigurationError;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HostsFile {

    public static List<HostAddress> loadHostsFile(File aFile) {
        if (!aFile.exists()) {
            throw new ServiceConfigurationError("File " + aFile.getAbsoluteFile() + " not found");
        }

        try (Stream<String> lines = Files.lines(aFile.toPath())) {
            return lines
                    .filter(HostsFile::hastText)
                    .filter(line -> !line.trim().startsWith("#"))
                    .map(HostsFile::toHostAddress)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new ServiceConfigurationError("Cannot read hosts file from " + aFile.getAbsoluteFile());
        }
    }

    private static HostAddress toHostAddress(String aLine) {
        try {
            StringTokenizer st = new StringTokenizer(aLine, " \t#");
            return HostAddress.builder()
                    .address(st.nextToken())
                    .host(st.nextToken())
                    .build();
        } catch (Exception e) {
            throw new ServiceConfigurationError("Cannot parse line '" + aLine + "'", e);
        }
    }

    public static boolean isEmpty(String aText) {
        return aText == null || aText.isEmpty() || aText.trim().isEmpty();
    }

    private static boolean hastText(String aText) {
        return !isEmpty(aText);
    }

}
