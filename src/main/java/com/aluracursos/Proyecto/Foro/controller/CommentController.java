package com.aluracursos.Proyecto.Foro.controller;

import com.aluracursos.Proyecto.Foro.domain.comment.Comment;
import com.aluracursos.Proyecto.Foro.domain.comment.CommentDTO.*;
import com.aluracursos.Proyecto.Foro.domain.comment.CommentRepository;
import com.aluracursos.Proyecto.Foro.domain.topic.Topic;
import com.aluracursos.Proyecto.Foro.domain.topic.TopicRepository;
import com.aluracursos.Proyecto.Foro.domain.user.User;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/topics/{topicId}/comments")
public class CommentController {

    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public ResponseEntity<Page<CommentResponseDTO>> getComments(
            @PathVariable Long topicId,
            @PageableDefault(size = 10) Pageable pageable) {
        
        Page<CommentResponseDTO> comments = commentRepository
                .findByTopicIdAndActiveTrue(topicId, pageable)
                .map(CommentResponseDTO::new);
        
        return ResponseEntity.ok(comments);
    }

    @PostMapping
    public ResponseEntity<CommentResponseDTO> createComment(
            @PathVariable Long topicId,
            @RequestBody @Valid CreateCommentDTO createCommentDTO,
            Authentication authentication,
            UriComponentsBuilder uriBuilder) {
        
        try {
            User user = (User) authentication.getPrincipal();
            Topic topic = topicRepository.getReferenceById(topicId);
            
            Comment comment = new Comment(createCommentDTO.content(), topic, user);
            commentRepository.save(comment);

            URI uri = uriBuilder.path("/topics/{topicId}/comments/{id}")
                    .buildAndExpand(topicId, comment.getId()).toUri();
            
            return ResponseEntity.created(uri).body(new CommentResponseDTO(comment));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<Void> deleteComment(
            @PathVariable Long topicId,
            @PathVariable Long commentId,
            Authentication authentication) {
        
        Comment comment = commentRepository.getReferenceById(commentId);
        User user = (User) authentication.getPrincipal();
        
        if (!comment.getAuthor().getId().equals(user.getId())) {
            return ResponseEntity.status(403).build();
        }
        
        comment.deactivate();
        commentRepository.save(comment);
        
        return ResponseEntity.noContent().build();
    }
}