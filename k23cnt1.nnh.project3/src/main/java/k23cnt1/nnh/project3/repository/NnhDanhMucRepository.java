package k23cnt1.nnh.project3.repository;

import k23cnt1.nnh.project3.entity.NnhDanhMuc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface NnhDanhMucRepository extends JpaRepository<NnhDanhMuc, Integer> {
}
