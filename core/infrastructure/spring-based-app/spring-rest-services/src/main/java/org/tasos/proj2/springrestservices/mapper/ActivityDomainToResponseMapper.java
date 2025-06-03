package org.tasos.proj2.springrestservices.mapper;

import org.tasos.proj2.domain.activity.ActivityAggregate;
import org.tasos.proj2.springrestservices.dto.activity.ActivityResponse;
import org.tasos.proj2.springrestservices.dto.activity.ActivityTypeResponse;

public class ActivityDomainToResponseMapper {

//    public WorkLogResponse mapToWorkLogResponse(WorkLog workLog) {
//        return new WorkLogResponse(workLog.getWorkLogId(), workLog.getStartDate(), workLog.getTimeSpentInSeconds(), workLog.getAssociatedWorker(), workLog.getDescription());
//    }
//
//    public List<WorkLogResponse> mapToWorkLogResponseList(List<WorkLog> workLogList) {
//        return workLogList.stream().map((workLog) -> mapToWorkLogResponse(workLog)).collect(Collectors.toList());
//    }

    public ActivityResponse mapToActivityResponse(ActivityAggregate activity) {
        return new ActivityResponse(activity.getId(), activity.getTitle(), activity.getActivitySubType(),
                new ActivityTypeResponse(activity.getActivityTypeId(), activity.getActivityTypeTitle(), activity.getActivityTypeDescription()));
    }

}
