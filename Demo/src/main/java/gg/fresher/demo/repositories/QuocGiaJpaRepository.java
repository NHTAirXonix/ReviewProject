package gg.fresher.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import gg.fresher.demo.entities.QuocGia;

public interface QuocGiaJpaRepository extends JpaRepository<QuocGia, Long> {
	
	@Query(value = "select * from quocgia where tenquocgia like %:tenquocgia%", nativeQuery = true)
    QuocGia findByName(@Param("tenquocgia") String tenquocgia);
}
