package com.miforo.api.domain.topic;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;

public class TopicDTO {
    
    public record CreateTopicDTO(
        @NotBlank String title,
        @NotBlank String message
    ) {}
    
    public record UpdateTopicDTO(
        Long id,
        String title,
        String message
    ) {}
    
    public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        LocalDateTime creationDate,
        String author
    ) {
        public TopicResponseDTO(Topic topic) {
            this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCreationDate(),
                topic.getAuthor().getUsername()
            );
        }
    }
}