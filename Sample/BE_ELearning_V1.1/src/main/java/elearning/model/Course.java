package elearning.model;

import elearning.model.base.BaseObject;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Course extends BaseObject {
	private String title;
	private String image;

	@Column(columnDefinition="LONGTEXT")
	private String description;
	private String subDescription;

	@OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
	private Set<Chapter> chapters = new HashSet<>();

}
