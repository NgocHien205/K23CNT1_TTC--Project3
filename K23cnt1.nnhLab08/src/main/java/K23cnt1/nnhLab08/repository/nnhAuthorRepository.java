package K23cnt1.nnhLab08.repository;

import K23cnt1.nnhLab08.entity.nnhAuthor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository

public interface  nnhAuthorRepository extends
        JpaRepository<nnhAuthor ,Long> {
}
