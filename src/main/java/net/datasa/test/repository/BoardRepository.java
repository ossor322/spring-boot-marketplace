package net.datasa.test.repository;

import net.datasa.test.domain.entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 게시판 관련 repository
 */

@Repository
public interface BoardRepository extends JpaRepository<BoardEntity, Integer> {
    @Query("""
            SELECT e 
            FROM BoardEntity e 
            WHERE 
                e.category = :category 
                AND 
                (e.title LIKE %:keyword% OR e.contents LIKE %:keyword%)
            """)
    List<BoardEntity> findByCategoryAndTitleOrContents(@Param("category") String category, @Param("keyword") String keyword);
}
