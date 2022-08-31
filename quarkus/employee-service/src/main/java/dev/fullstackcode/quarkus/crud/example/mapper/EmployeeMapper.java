package dev.fullstackcode.quarkus.crud.example.mapper;


import dev.fullstackcode.quarkus.crud.example.dto.EmployeeDto;
import dev.fullstackcode.quarkus.crud.example.entity.Employee;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "cdi")
public interface EmployeeMapper {
    EmployeeDto toEmployeeDto(Employee employee);
    Employee toEmployeeEntity(EmployeeDto dto);
    List<EmployeeDto> toEmployeeList(List<Employee> employee);
    void updateEmployeeEntityFromDto(EmployeeDto dto, @MappingTarget Employee employee);

}
