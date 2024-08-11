package com.branchoftriumph.llamacpp.openapi;

import com.branchoftriumph.llamacpp.openapi.utils.ResponseValidators;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
public class SlotsAPIsTest extends TestBase {
    /**
     * Returns the current slots processing state.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void getSlots_returnsSlots() throws ApiException {
        final List<Slot> response = llamaChatClient.getSlots();
        assertNotNull(response);
        assertFalse(response.isEmpty());

        log.debug(response);
    }

    /**
     * Save, restore, or erase the prompt cache of the specified slot to a file.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postSlots_saveEraseRestore_succeeds() throws ApiException {
        final Integer idSlot = 0;
        final String filename = "jdk-integration.bin";

        // Perform a dummy inference to fill up slots cache.
        // The server crashes if restoring from a blank cache file.
        log.info("Performing dummy PostCompletion for /slots tests...");
        final PostCompletionRequest postCompletionRequest = new PostCompletionRequest()
                .prompt("")
                .nPredict(1);
        log.info("Performed dummy PostCompletion for /slots tests.");
        final PostCompletionResponse completionResponse = llamaChatClient.postCompletion(postCompletionRequest);
        log.debug(completionResponse);

        log.info("Saving cache of slot {} to {}...", idSlot, filename);
        final PostSlotsResponse saveResponse = llamaChatClient.postSlots(idSlot, "save", new PostSlotsRequest()
                .filename(filename));
        log.info("Saved slot {} to {}.", idSlot, filename);
        assertNotNull(saveResponse);
        assertNotNull(saveResponse.getIdSlot());
        assertNotNull(saveResponse.getFilename());
        assertNotNull(saveResponse.getnSaved());
        assertNotNull(saveResponse.getnWritten());
        ResponseValidators.validateSlotsTimings(saveResponse.getTimings());
        log.debug(saveResponse);

        log.info("Erasing cache of slot {}...", idSlot);
        final PostSlotsResponse eraseResponse = llamaChatClient.postSlots(idSlot, "erase", new PostSlotsRequest());
        log.info("Erased cache of slot {}.", idSlot);
        assertNotNull(eraseResponse);
        assertNotNull(eraseResponse.getIdSlot());
        assertNotNull(eraseResponse.getnErased());
        log.debug(eraseResponse);

        log.info("Restoring cache of slot {} from {}...", idSlot, filename);
        final PostSlotsResponse restoreResponse = llamaChatClient.postSlots(idSlot, "restore", new PostSlotsRequest()
                .filename(filename));
        log.info("Restored cache of slot {} from {}.", idSlot, filename);
        assertNotNull(restoreResponse);
        assertNotNull(restoreResponse.getIdSlot());
        assertNotNull(restoreResponse.getFilename());
        assertNotNull(restoreResponse.getnRestored());
        assertNotNull(restoreResponse.getnRead());
        ResponseValidators.validateSlotsTimings(restoreResponse.getTimings());
        log.debug(restoreResponse);

    }
}
