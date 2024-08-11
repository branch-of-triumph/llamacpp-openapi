package com.branchoftriumph.llamacpp.openapi;

import com.branchoftriumph.llamacpp.openapi.utils.SysEnv;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeAll;

/**
 * API tests for LlamaCppClient
 */
@Log4j2
public abstract class TestBase {

    protected static LlamaCppClient llamaChatClient;

    protected static LlamaCppClient llamaEmbeddingClient;

    @BeforeAll
    public static void before() {
        llamaChatClient = new LlamaCppClient(new ApiClient()
                .setBasePath(String.format("http://%s:%s",
                        SysEnv.getTestServerHostName(), SysEnv.getTestCompletionServerPort())));
        llamaEmbeddingClient = new LlamaCppClient((new ApiClient()
                .setBasePath(String.format("http://%s:%s",
                        SysEnv.getTestServerHostName(), SysEnv.getTestEmbeddingServerPort()))));
    }
}
