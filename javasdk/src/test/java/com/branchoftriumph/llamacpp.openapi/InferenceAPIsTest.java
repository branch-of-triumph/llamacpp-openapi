package com.branchoftriumph.llamacpp.openapi;

import com.branchoftriumph.llamacpp.openapi.utils.ResponseValidators;
import com.google.common.collect.ImmutableList;
import lombok.extern.log4j.Log4j2;
import okhttp3.Call;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * API tests for LlamaCppClient
 */
@Log4j2
public class InferenceAPIsTest extends TestBase {
    /**
     * Generate a text completion
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postCompletion_providePrompt_returnsResponse() throws ApiException {
        final String testPrompt = "What is one good pickup line?";
        final int numTokens = 10;
        final PostCompletionRequest postCompletionRequest = new PostCompletionRequest()
                .prompt(testPrompt)
                .nPredict(numTokens);
        log.info("Attempting to generate the next {} tokens of [{}]...", numTokens, testPrompt);
        final PostCompletionResponse response = llamaChatClient.postCompletion(postCompletionRequest);
        log.info("Generated the next {} tokens of [{}]: [{}].", numTokens, testPrompt, response.getContent());
        log.debug(response);
    }

    /**
     * Generate a streamed text completion
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postCompletion_providePromptWithStreamEnabled_streamsResponse() throws ApiException, IOException {
        final String testPrompt = "What is one good pickup line?";
        final int numTokens = 10;
        final PostCompletionRequest postCompletionRequest = new PostCompletionRequest()
                .prompt(testPrompt)
                .nPredict(numTokens)
                .stream(true);
        log.info("Attempting to stream the next {} tokens of [{}]...", numTokens, testPrompt);
        final Call streamedCall = llamaChatClient.postCompletionCall(postCompletionRequest, null);
        final InputStream rawStream = llamaChatClient.getApiClient().executeStream(streamedCall, null);
        final BufferedReader streamReader = new BufferedReader(new InputStreamReader(rawStream, StandardCharsets.UTF_8));
        final StringBuilder finalResponse = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            if (inputStr.isEmpty()) {
                continue;
            }
            final PostCompletionResponse responseChunk = JSON.deserialize(
                    inputStr.replace("data: ", ""), PostCompletionResponse.class);
            finalResponse.append(responseChunk.getContent());
        }
        streamReader.close();
        log.info("Streamed the next {} tokens of [{}]: [{}]", numTokens, testPrompt, finalResponse);
    }

    /**
     * Tokenize a given text
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postTokenize_tokenizeString_returnsTokens() throws ApiException {
        final String testPrompt = "What is one good pickup line?";
        final PostTokenizeRequest postTokenizeRequest = new PostTokenizeRequest().content(testPrompt);
        log.info("Attempting to tokenize [{}]...", testPrompt);
        final PostTokenizeResponse response = llamaChatClient.postTokenize(postTokenizeRequest);
        log.info("Tokenized [{}] to: [{}].", testPrompt, response.getTokens());
        assertNotNull(response);
        assertNotNull(response.getTokens());
        assertEquals(response.getTokens(), ImmutableList.of(1841, 603, 974, 1426, 35583, 2017, 235336));
        log.debug(response);
    }

    /**
     * Detokenize given tokens
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postDetokenize_provideTokens_returnsExpectedString() throws ApiException {
        final String testPrompt = "What is one good pickup line?";
        final List<Integer> testTokens = ImmutableList.of(1841, 603, 974, 1426, 35583, 2017, 235336);
        final PostDetokenizeRequest postDetokenizeRequest = new PostDetokenizeRequest()
                .tokens(testTokens);
        log.info("Attempting to detokenize [{}]...", testTokens);
        final PostDetokenizeResponse response = llamaChatClient.postDetokenize(postDetokenizeRequest);
        log.info("Detokenize [{}] to: [{}].", testTokens, response.getContent());
        assertNotNull(response);
        assertNotNull(response.getContent());
        assertEquals(response.getContent(), testPrompt);
        log.debug(response);
    }

    /**
     * Generate embedding of a given text.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postEmbedding_providesContent_returnsEmbeddings() throws ApiException {
        final String testPrompt = "What is one good pickup line?";
        final PostEmbeddingRequest postEmbeddingRequest = new PostEmbeddingRequest().content(testPrompt);
        log.info("Attempting to generating embeddings for [{}]...", testPrompt);
        final PostEmbeddingResponse response = llamaEmbeddingClient.postEmbedding(postEmbeddingRequest);
        log.info("Generated embeddings for [{}]: [{}].", testPrompt, response.getEmbedding());
        assertNotNull(response);
        assertNotNull(response.getEmbedding());
        log.debug(response);
    }

    /**
     * For code infilling. Takes a prefix and a suffix and returns the predicted completion as stream.
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postInfill_providePrefixAndSuffix_returnsInfillContent() throws ApiException {
        final String testPrefix = "At the beginning";
        final String testSuffix = "the end.";
        final int numTokens = 10;
        PostInfillRequest postInfillRequest = new PostInfillRequest()
                .inputPrefix(testPrefix)
                .inputSuffix(testSuffix)
                .nPredict(10);
        log.info("Attempting to generate infill the next {} tokens for prefix [{}] and suffix [{}]...", numTokens,
                testPrefix, testSuffix);
        PostCompletionResponse response = llamaChatClient.postInfill(postInfillRequest);
        log.info("Generated the next {} token for prefix [{}] and suffix [{}]: [{}].", numTokens, testPrefix,
                testSuffix, response.getContent());
        assertNotNull(response);
        assertNotNull(response.getContent());
        ResponseValidators.validateGenerationSettings(response.getGenerationSettings());
        log.debug(response);
    }

    /**
     * Generate a streamed text completion
     *
     * @throws ApiException if the Api call fails
     */
    @Test
    public void postInfill_providePromptWithStreamEnabled_streamsResponse() throws ApiException, IOException {
        final String testPrefix = "At the beginning";
        final String testSuffix = "the end.";
        final int numTokens = 10;
        PostInfillRequest postInfillRequest = new PostInfillRequest()
                .inputPrefix(testPrefix)
                .inputSuffix(testSuffix)
                .nPredict(10);
        log.info("Attempting to stream infill of the next {} tokens for prefix [{}] and suffix [{}]...", numTokens,
                testPrefix, testSuffix);
        final Call streamedCall = llamaChatClient.postInfillCall(postInfillRequest, null);
        final InputStream rawStream = llamaChatClient.getApiClient().executeStream(streamedCall, null);
        final BufferedReader streamReader = new BufferedReader(new InputStreamReader(rawStream, StandardCharsets.UTF_8));
        final StringBuilder finalResponse = new StringBuilder();
        String inputStr;
        while ((inputStr = streamReader.readLine()) != null) {
            if (inputStr.isEmpty()) {
                continue;
            }
            final PostCompletionResponse responseChunk = JSON.deserialize(
                    inputStr.replace("data: ", ""), PostCompletionResponse.class);
            finalResponse.append(responseChunk.getContent());
        }
        streamReader.close();
        log.info("Streamed the next {} tokens for prefix [{}] and suffix [{}]: [{}].", numTokens, testPrefix,
                testSuffix, finalResponse);
    }
}
