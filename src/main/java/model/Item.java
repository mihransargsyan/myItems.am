package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Item {
    private int id;
    private String title;
    private double price;
    private Category category;
    private String picURL;
    private User user;
}
