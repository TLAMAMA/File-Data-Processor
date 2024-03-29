package restfulApi.File.Data.Processor.entity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Long> {
    Files findByName(String name);
}
