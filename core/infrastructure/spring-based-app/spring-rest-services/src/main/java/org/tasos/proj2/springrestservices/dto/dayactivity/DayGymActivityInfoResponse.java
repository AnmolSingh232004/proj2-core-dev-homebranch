package org.tasos.proj2.springrestservices.dto.dayactivity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DayGymActivityInfoResponse {

    String loggedReps;
    String todoKg;
    String todoSets;
    String todoReps;
}
