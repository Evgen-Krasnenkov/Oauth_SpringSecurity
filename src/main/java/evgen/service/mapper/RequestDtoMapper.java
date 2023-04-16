package evgen.service.mapper;

public interface RequestDtoMapper<M, D> {
    M mapToModel(D dto);
}
