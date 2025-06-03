package org.tasos.proj2.repositoryinterface.dayactivity;

import org.tasos.proj2.domain.dayactivity.DayActivityProjection;

import java.time.LocalDate;
import java.util.List;

/**
 *  Spring Data custom repository for the DayActivity entity.
 *
 * @author aioannidis, PRODYNA SE
 * @version 0.1.0
 * @since 0.1.0
 */
public interface DayActivityRepositoryCustom {

    List<DayActivityProjection> findDayActivitiesByDateAndUserNameWithActivityTypeTitle(LocalDate date, String userName);

    List<DayActivityProjection> findDayActivitiesByDateAndUserNameWithActivityTypeTitleAndActTitle(LocalDate date, String userName);

    List<DayActivityProjection> findDayActivitiesByDateRangeAndActTypeAndUserNameWithSubtype(LocalDate start, LocalDate end, String actTypeId, String userName);

}