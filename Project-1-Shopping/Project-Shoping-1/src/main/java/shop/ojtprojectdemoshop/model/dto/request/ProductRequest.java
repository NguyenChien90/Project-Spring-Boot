package shop.ojtprojectdemoshop.model.dto.request;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class ProductRequest {
    private String name;
    private String description;
    private Double price;
    private int quantity;
    private String image;
    private Long categoryId;
}
