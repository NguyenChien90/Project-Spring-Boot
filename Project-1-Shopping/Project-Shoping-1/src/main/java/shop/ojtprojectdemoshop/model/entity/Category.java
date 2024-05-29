package shop.ojtprojectdemoshop.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
public class Category extends BaseEntity {
    private String categoryName;
    private String description;
    @Column(columnDefinition = "BOOLEAN DEFAULT true")
    private boolean status = true;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    List<Product> products;

}
