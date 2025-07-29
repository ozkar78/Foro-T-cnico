package com.aluracursos.Proyecto.Foro.domain.topic;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class TopicDTO {
    
    public record CreateTopicDTO(
        @NotBlank String title,
        @NotBlank String message,
        String category
    ) {}
    
    public record UpdateTopicDTO(
        Long id,
        String title,
        String message,
        String category
    ) {}
    
    public record TopicResponseDTO(
        Long id,
        String title,
        String message,
        String category,
        TopicStatus status,
        LocalDateTime creationDate,
        LocalDateTime lastModified,
        Integer views,
        String author
    ) {
        public TopicResponseDTO(Topic topic) {
            this(
                topic.getId(),
                topic.getTitle(),
                topic.getMessage(),
                topic.getCategory(),
                topic.getStatus(),
                topic.getCreationDate(),
                topic.getLastModified(),
                topic.getViews(),
                topic.getAuthor().getUsername()
            );
        }
    }
}