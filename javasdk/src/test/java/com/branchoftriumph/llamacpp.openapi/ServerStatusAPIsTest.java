package com.branchoftriumph.llamacpp.openapi;

import com.branchoftriumph.llamacpp.openapi.utils.ResponseValidators;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * API tests for LlamaCppClient
 */
@Log4j2
public class ServerStatusAPIsTest extends TestBase {

    /**
     * Get the health status of the server without passing include_slots
     *
     * Returns the current state of the server
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getHealth_nullParam_returnsHealthOnly() throws ApiException {
        log.info("Attempting to check health of server without slot data...");
        final GetHealthResponse response = llamaChatClient.getHealth(null, null);
        log.info("Server status without slot data is [{}].", response.getStatus());
        assertEquals(response.getStatus(), "ok");
        assertNotNull(response.getSlotsIdle());
        assertNotNull(response.getSlotsProcessing());
        assertNotNull(response.getSlots());
        assertTrue(response.getSlots().isEmpty());
        log.debug(response);
    }

    /**
     * Get the health status of the server
     *
     * Returns the current state of the server
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getHealth_hasIncludeSlots_returnsHealthAndSlots() throws ApiException {
        log.info("Attempting to check health of server with slot data...");
        final GetHealthResponse response = llamaChatClient.getHealth(true, true);
        log.info("Server status with slot data is [{}].", response.getStatus());
        assertEquals(response.getStatus(), "ok");
        assertNotNull(response.getSlotsIdle());
        assertNotNull(response.getSlotsProcessing());
        assertNotNull(response.getSlots());
        log.info("Got data for {} slot(s).", response.getSlots().size());
        assertFalse(response.getSlots().isEmpty());
        log.debug(response);
    }

    /**
     * Get the metrics of the server
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getMetrics_returnsMetrics() throws ApiException {
        log.info("Attempting to retrieve server metrics...");
        final String response = llamaChatClient.getMetrics();
        assertNotNull(response);
        final long numLines = response.lines()
                .filter((line) -> line.contains("#"))
                .count();
        log.info("Retrieved {} lines of metrics.", numLines);
        log.debug(response);
    }

    /**
     * Get the current settings of the server
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getProps_returnsProps() throws ApiException {
        log.info("Attempting to retrieve server properties...");
        final GetPropsResponse response = llamaChatClient.getProps();
        log.info("Retrieved server properties.");
        assertNotNull(response.getSystemPrompt());
        assertNotNull(response.getTotalSlots());
        assertNotNull(response.getChatTemplate());

        final GenerationSettings generationSettings = response.getDefaultGenerationSettings();
        ResponseValidators.validateGenerationSettings(generationSettings);

        assertNotNull(response.getTotalSlots());
        assertNotNull(response.getChatTemplate());

        log.debug(response);
    }
}
