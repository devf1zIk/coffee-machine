package kz.devf1zIk.coffeemachine.service;

import kz.devf1zIk.coffeemachine.dto.PublicHolidayDto;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class OperationalService {

    private static final String URL="https://date.nager.at/api/v3/publicholidays/2024/KZ";
    private final Set<LocalDate> holidays = new HashSet<>();
    private final RestTemplate restTemplate;

    private boolean isCoffeeMachineOperational() {
        LocalDateTime now = LocalDateTime.now();
        LocalTime currentTime = now.toLocalTime();
        LocalDate currentDate = now.toLocalDate();

        if (currentTime.isBefore(LocalTime.of(8, 0)) || currentTime.isAfter(LocalTime.of(17, 0))) {
            return false;
        }

        if (isWeekend(currentDate)) {
            return false;
        }

        if (isHoliday(currentDate)) {
            return false;
        }

        return true;
    }

    private boolean isWeekend(LocalDate date) {
        return date.getDayOfWeek().getValue() >= 6;
    }

    private boolean isHoliday(LocalDate date) {
        if (holidays.isEmpty()) {
            fetchHolidays();
        }
        return holidays.contains(date);
    }

    @Cacheable("holidays")
    public void fetchHolidays() {

        PublicHolidayDto[] publicHolidays = restTemplate.getForObject(URL, PublicHolidayDto[].class);
        if (publicHolidays != null) {
            for (PublicHolidayDto holiday : publicHolidays) {
                holidays.add(holiday.getDate());
            }
        }
    }

    public boolean validate() {
        return isCoffeeMachineOperational();
    }
}
