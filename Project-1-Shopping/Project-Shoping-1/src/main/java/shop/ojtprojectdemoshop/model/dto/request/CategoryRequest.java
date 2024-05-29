package shop.ojtprojectdemoshop.model.dto.request;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Builder
public class CategoryRequest {
    private String categoryName;
    private String description;

}
