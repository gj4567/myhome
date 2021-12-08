package org.iot.myhome.repository;

import org.iot.myhome.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findBySubject(String subject);
    List<Board> findBySubjectOrContent(String subject, String content);

    Page<Board> findBySubjectContainingOrContentContaining(String subject, String content, Pageable pageable);

}
