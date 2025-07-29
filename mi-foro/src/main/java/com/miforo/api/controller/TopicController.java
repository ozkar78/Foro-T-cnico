package com.miforo.api.controller;

import com.miforo.api.domain.topic.Topic;
import com.miforo.api.domain.topic.TopicDTO.*;
import com.miforo.api.domain.topic.TopicRepository;
import com.miforo.api.domain.user.User;
import jakarta.transaction.Transactional;
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
@RequestMapping("/topics")
public class TopicController {

    @Autowired
    private TopicRepository topicRepository;

    @GetMapping
    public ResponseEntity<Page<TopicResponseDTO>> listTopics(@PageableDefault(size = 10) Pageable pageable) {
        Page<TopicResponseDTO> topics = topicRepository.findByActiveTrue(pageable)
                .map(TopicResponseDTO::new);
        return ResponseEntity.ok(topics);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicResponseDTO> getTopicById(@PathVariable Long id) {
        Topic topic = topicRepository.getReferenceById(id);
        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @PostMapping
    public ResponseEntity<TopicResponseDTO> createTopic(
            @RequestBody @Valid CreateTopicDTO createTopicDTO,
            Authentication authentication,
            UriComponentsBuilder uriBuilder) {
        
        User user = (User) authentication.getPrincipal();
        Topic topic = new Topic(createTopicDTO.title(), createTopicDTO.message(), user);
        topicRepository.save(topic);

        URI uri = uriBuilder.path("/topics/{id}").buildAndExpand(topic.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicResponseDTO(topic));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<TopicResponseDTO> updateTopic(
            @PathVariable Long id,
            @RequestBody @Valid UpdateTopicDTO updateTopicDTO,
            Authentication authentication) {
        
        Topic topic = topicRepository.getReferenceById(id);
        User user = (User) authentication.getPrincipal();
        
        if (!topic.getAuthor().getId().equals(user.getId())) {
            return ResponseEntity.forbidden().build();
        }

        topic.update(updateTopicDTO.title(), updateTopicDTO.message());
        return ResponseEntity.ok(new TopicResponseDTO(topic));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> deleteTopic(@PathVariable Long id, Authentication authentication) {
        Topic topic = topicRepository.getReferenceById(id);
        User user = (User) authentication.getPrincipal();
        
        if (!topic.getAuthor().getId().equals(user.getId())) {
            return ResponseEntity.forbidden().build();
        }

        topic.deactivate();
        return ResponseEntity.noContent().build();
    }
}