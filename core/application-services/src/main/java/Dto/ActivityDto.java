package Dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ActivityDto {
    private Long activityId;
    private String activityTitle;
    private String activitySubtype;
    private ActivityTypeDto activityTypeDto;
}
