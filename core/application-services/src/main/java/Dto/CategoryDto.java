package Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private Long activityTypeId;
    private String activityTypeTitle;
    private String activityTypeDescription;
    private List<ActivityDto> activities;
}
