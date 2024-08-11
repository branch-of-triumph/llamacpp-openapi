package com.branchoftriumph.llamacpp.openapi.utils;

import com.branchoftriumph.llamacpp.openapi.GenerationSettings;
import com.branchoftriumph.llamacpp.openapi.SlotsTimings;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ResponseValidators {
    public static void validateGenerationSettings(final GenerationSettings generationSettings) {
        assertNotNull(generationSettings);
        assertNotNull(generationSettings.getnCtx());
        assertNotNull(generationSettings.getnPredict());
        assertNotNull(generationSettings.getModel());
        assertNotNull(generationSettings.getSeed());
        assertNotNull(generationSettings.getTemperature());
        assertNotNull(generationSettings.getDynatempRange());
        assertNotNull(generationSettings.getDynatempExponent());
        assertNotNull(generationSettings.getTopK());
        assertNotNull(generationSettings.getTopP());
        assertNotNull(generationSettings.getMinP());
        assertNotNull(generationSettings.getTfsZ());
        assertNotNull(generationSettings.getTypicalP());
        assertNotNull(generationSettings.getRepeatLastN());
        assertNotNull(generationSettings.getRepeatPenalty());
        assertNotNull(generationSettings.getPresencePenalty());
        assertNotNull(generationSettings.getFrequencyPenalty());
        assertNotNull(generationSettings.getPenaltyPromptTokens());
        assertNotNull(generationSettings.getUsePenaltyPromptTokens());
        assertNotNull(generationSettings.getMirostat());
        assertNotNull(generationSettings.getMirostatTau());
        assertNotNull(generationSettings.getMirostatEta());
        assertNotNull(generationSettings.getPenalizeNl());
        assertNotNull(generationSettings.getStop());
        assertNotNull(generationSettings.getnKeep());
        assertNotNull(generationSettings.getnDiscard());
        assertNotNull(generationSettings.getIgnoreEos());
        assertNotNull(generationSettings.getStream());
        assertNotNull(generationSettings.getLogitBias());
        assertNotNull(generationSettings.getnProbs());
        assertNotNull(generationSettings.getMinKeep());
        assertNotNull(generationSettings.getGrammar());
        assertNotNull(generationSettings.getSamplers());
    }

    public static void validateSlotsTimings(final SlotsTimings slotsTimings) {
        assertNotNull(slotsTimings);
        assertTrue(slotsTimings.getSaveMs() != null || slotsTimings.getRestoreMs() != null);
    }
}
