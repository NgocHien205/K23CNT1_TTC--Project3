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


public class nnhAuthor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long nnhId;
    String nnhCode;
    String nnhName;
    String nnhDescription;
    String nnhImgUrl;
    String nnhEmail;
    String nnhPhone;
    String nnhAddress;
    Boolean nnhActive;

    //Tao moi quan he voi bang nnhBook
    @ManyToMany(mappedBy = "nnhAuthor")
    List<nnhBook> nnhBook = new ArrayList<>();
}
