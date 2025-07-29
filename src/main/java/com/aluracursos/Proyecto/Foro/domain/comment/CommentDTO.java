package com.aluracursos.Proyecto.Foro.domain.comment;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;

public class CommentDTO {
    
    public record CreateCommentDTO(
        @NotBlank String content
    ) {}
    
    public record CommentResponseDTO(
        Long id,
        String content,
        LocalDateTime creationDate,
        String author
    ) {
        public CommentResponseDTO(Comment comment) {
            this(
                comment.getId(),
                comment.getContent(),
                comment.getCreationDate(),
                comment.getAuthor().getUsername()
            );
        }
    }
}