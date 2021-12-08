package org.iot.myhome.controller;

import org.iot.myhome.model.Board;
import org.iot.myhome.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@RestController
@RequestMapping("/api")
class BoardApiController {

    @Autowired
    private BoardRepository repository;

    // Aggregate root
    // tag::get-aggregate-root[]
    @GetMapping("/boards")
    /*List<Board> all() {
        return repository.findAll();
    }*/
    List<Board> all(@RequestParam(required = false, defaultValue = "") String subject,
        @RequestParam(required = false, defaultValue = "") String content) {
        if(StringUtils.isEmpty(subject) && StringUtils.isEmpty(content)) {
            return repository.findAll();
        } else {
            return repository.findBySubjectOrContent(subject, content);
        }
    }
    // end::get-aggregate-root[]

    @PostMapping("/boards")
    Board newBoard(@RequestBody Board newBoard) {
        return repository.save(newBoard);
    }

    // Single item

    @GetMapping("/boards/{id}")
    Board one(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PutMapping("/boards/{id}")
    Board replaceBoard(@RequestBody Board newBoard, @PathVariable Long id) {

        return repository.findById(id)
                .map(board -> {
                    board.setSubject(newBoard.getSubject());
                    board.setContent(newBoard.getContent());
                    return repository.save(board);
                })
                .orElseGet(() -> {
                    newBoard.setId(id);
                    return repository.save(newBoard);
                });
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/boards/{id}")
    void deleteBoard(@PathVariable Long id) {
        repository.deleteById(id);
    }
}