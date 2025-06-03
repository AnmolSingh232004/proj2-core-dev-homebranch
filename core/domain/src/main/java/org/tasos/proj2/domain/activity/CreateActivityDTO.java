package org.tasos.proj2.domain.activity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.tasos.proj2.domain.dayactivity.DayActivityAggregate;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CreateActivityDTO {

    private String actTypeId;
    private String newActName;
    private String selectedSubType;
    private String newSubType;
}
