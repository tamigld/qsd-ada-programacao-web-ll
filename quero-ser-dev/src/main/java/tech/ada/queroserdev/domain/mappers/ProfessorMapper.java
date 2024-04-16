package tech.ada.queroserdev.domain.mappers;

import tech.ada.queroserdev.domain.dto.v1.ProfessorDto;
import tech.ada.queroserdev.domain.entities.Professor;

public class ProfessorMapper {

    public static Professor toEntity(ProfessorDto dto, String activity) {
        return Professor
                .builder()
                .nome(dto.getNome())
                .cpf(dto.getCpf())
                .eMail(dto.getEmail())
                .activity(activity)
                .build();
    }

    public static ProfessorDto toDto(Professor entity) {
        return new ProfessorDto(
                entity.getId(),
                entity.getNome(),
                entity.getCpf(),
                entity.getEMail(),
                entity.getActivity()
        );
    }

}
