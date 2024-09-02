package com.chatbotapp.chatbotapp.dto;

import lombok.NonNull;

public record ModelResponse(
        UserPrompt user_prompt,
        String ai_response
) {
}
