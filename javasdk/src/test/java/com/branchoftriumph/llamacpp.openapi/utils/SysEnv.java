package com.branchoftriumph.llamacpp.openapi.utils;

import lombok.extern.log4j.Log4j2;

/**
 * Helper for retrieving environment variables used by tests.
 */
@Log4j2
public class SysEnv {
    private static final String SERVER_HOSTNAME = "LLAMA_CPP_SERVER_HOSTNAME";
    private static final String COMPLETION_SERVER_PORT = "LLAMA_CPP_COMPLETION_SERVER_PORT";
    private static final String EMBEDDING_SERVER_PORT = "LLAMA_CPP_EMBEDDING_SERVER_PORT";

    public static String getTestServerHostName() {
        return getEnvWithDefault(SERVER_HOSTNAME, "localhost");
    }

    public static String getTestCompletionServerPort() {
        return getEnvWithDefault(COMPLETION_SERVER_PORT, "8080");
    }

    public static String getTestEmbeddingServerPort() {
        return getEnvWithDefault(EMBEDDING_SERVER_PORT, "8081");
    }

    private static String getEnvWithDefault(final String name, final String defaultValue) {
        final String var = System.getenv(name);
        if (var == null) {
            log.warn("{} not defined. Returning default value [{}].", name, defaultValue);
            return defaultValue;
        }
        return var;
    }
}
