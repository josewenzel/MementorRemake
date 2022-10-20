package domain.model;

import java.util.UUID;

public record Employee(UUID id, PersonalInformation personalInformation, Employee mentor) {
}
