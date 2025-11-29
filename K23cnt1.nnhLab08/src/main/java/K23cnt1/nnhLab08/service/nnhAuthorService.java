package K23cnt1.nnhLab08.service;

import K23cnt1.nnhLab08.entity.nnhAuthor;
import K23cnt1.nnhLab08.entity.nnhBook;
import K23cnt1.nnhLab08.repository.nnhAuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class nnhAuthorService {
    @Autowired
    private nnhAuthorRepository authorRepository;
    public List<nnhAuthor> getAllAuthors() {
        return authorRepository.findAll();
    }
    public nnhAuthor saveAuthor(nnhAuthor author) {
        return authorRepository.save(author);
    }
    public nnhAuthor getAuthorById(Long id) {
        return authorRepository.findById(id).orElse(null);
    }
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    public List<nnhAuthor> findAllById(List<Long> ids) {
        return authorRepository.findAllById(ids);
    }
}
