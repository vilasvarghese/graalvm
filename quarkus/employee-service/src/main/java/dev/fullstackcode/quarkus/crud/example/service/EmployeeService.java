package dev.fullstackcode.quarkus.crud.example.service;



import dev.fullstackcode.quarkus.crud.example.dto.EmployeeDto;
//import dev.fullstackcode.quarkus.crud.example.entity.Department;
import dev.fullstackcode.quarkus.crud.example.entity.Employee;
import dev.fullstackcode.quarkus.crud.example.mapper.EmployeeMapper;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class EmployeeService {

    @Inject
    EmployeeMapper employeeMapper;

    public EmployeeDto getEmployee(Long id) {
        return employeeMapper.toEmployeeDto(Employee.findById(id));
    }

    public List<EmployeeDto> getAllEmployees() {
        // return employeeMapper.toEmployeeList(Employee.listAll());
        return employeeMapper.toEmployeeList(Employee.findAll().list());
    }

    public List<EmployeeDto> getEmployeesByDepartment(String dept) {
        return employeeMapper.toEmployeeList(Employee.findEmployeesByDepartmentId(dept));
    }

    public List<EmployeeDto> searchEmpsByName(String name) {
        return employeeMapper.toEmployeeList(Employee.searchEmpsByName(name));
    }

    @Transactional
    public EmployeeDto createEmployee(EmployeeDto employee) {

        Employee entity = employeeMapper.toEmployeeEntity(employee);
        Employee.persist(entity);

        entity.persistAndFlush();
       // either use persistAndFlush or persist method
       // entity.persist();
        if(entity.isPersistent()) {
            Optional<Employee> optionalEmp = Employee.findByIdOptional(entity.id);
            entity = optionalEmp.orElseThrow(NotFoundException::new);
            return employeeMapper.toEmployeeDto(entity);
        } else {
            throw new PersistenceException();
        }

    }

    @Transactional
    public EmployeeDto updateEmployee(Long id, EmployeeDto employee) {
        Employee entity  = Employee.findById(id);
        if(entity == null) {
            throw new WebApplicationException("Employee with id of " + id + " does not exist.", 404);
        }

        // either use mapping
        employeeMapper.updateEmployeeEntityFromDto(employee,entity);

        // or update the entity with
//        entity.last_name = employee.getLast_name() ;
//        entity.first_name = employee.getFirst_name();
//        entity.birth_date = employee.getBirth_date();
//        entity.hire_date = employee.getHire_date();
//
//        if(entity.department != null && employee.getDepartment().id != null) {
//            entity.department.id = employee.getDepartment().id;
//        }
//
//        if(entity.department == null && employee.getDepartment().id != null) {
//            Department department = new Department();
//            department.id = employee.getDepartment().id;
//            entity.department = department;
//        }

        return employeeMapper.toEmployeeDto(entity);
    }

    @Transactional
    public EmployeeDto updateEmployee(EmployeeDto employee) {
        Employee entity  = Employee.findById(employee.getId());
        if(entity == null) {
            throw new WebApplicationException("Employee with id " + employee.getId() + " does not exist.", 404);
        }
        employeeMapper.updateEmployeeEntityFromDto(employee,entity);
        entity =  Employee.getEntityManager().merge(entity);
        return employeeMapper.toEmployeeDto(entity);
    }

    @Transactional
    public EmployeeDto updateEmpDepartment(Long empId, String deptId) {
        Employee entity  = Employee.findById(empId);
        if(entity == null) {
            throw new WebApplicationException("Employee with id " + empId + " does not exist.", 404);
        }

        Optional<String> department = Optional.of(deptId);
        if(department.isPresent()) {
            entity.department = department.get();
        }


        return employeeMapper.toEmployeeDto(entity);
    }


    @Transactional
    public Response deleteEmployee(Long id) {

//        Employee emp  = Employee.findById(id);
//        if(emp == null) {
//            throw new WebApplicationException("Employee with id of " + id + " does not exist.", 404);
//        }
//        emp.delete();
        boolean isEntityDeleted = Employee.deleteById(id);
        if(!isEntityDeleted) {
            throw new WebApplicationException("Employee with id of " + id + " does not exist.", 404);
        }

        return Response.status(204).build();

    }
}
