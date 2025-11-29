package K23cnt1.nnhLab08.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class nnhBook {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long nnhId;
    String nnhCode;
    String nnhName;
    String nnhDescription;
    String nnhImgUrl;
    Integer nnhQuantity;
    Double nnhPrice;
    Boolean nnhActive;

    //Thiet ke moi quan he voi bang nnhAuthor
    @ManyToMany
    @JoinTable(
            name = "nnh_book_author",
            joinColumns = @JoinColumn(name = "nnhBookId"),
            inverseJoinColumns = @JoinColumn (name = "nnhAuthorId")
    )
    List<nnhAuthor> nnhAuthor = new ArrayList<>();
}
