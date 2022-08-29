package com.quarkus.demo;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Path("/employeeObj")
public class EmployeeObjResource {

    public static List<Employee> employees = new ArrayList<Employee>();//Change 1: String to Employee. Create Employee class for this.

    @GET
    @Produces(MediaType.APPLICATION_JSON)//Change 2: Change type to JSON
    public Response getEmployees(){
        return Response.ok(employees).build();
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/count")
    public Integer countOfEmployees(){
        return employees.size();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)//Change 3: Change to JSON
    @Consumes(MediaType.APPLICATION_JSON)//Change 4: Change to JSON
    public Response createEmployee(Employee employee){
        employees.add(employee);
        return Response.ok(employees).build();
    }

    @PUT
    @Path("{empId}/{empName}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateEmployeeName(
            @PathParam("empId") String empId,
            @PathParam("empName") String empName){

        employees = employees.stream().map(
                employee -> {
                    if (employee.getId().equals(empId)){
                        employee.setName(empName);
                    }
                    return employee;
                }
        ).collect(Collectors.toList());
        return Response.ok(employees).build();
    }


    @DELETE
    @Path("{empId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteEmployee(
            @PathParam("empId") String empId ){
        Optional<Employee> employee = employees.stream().filter(myEmployee -> myEmployee.getId().equals(empId)).findFirst();
        boolean deleted = false;
        if (employee.isPresent()){
            deleted = employees.remove(employee.get());
        }
        if (deleted){
            return Response.noContent().build();
        }
        return Response.status(Response.Status.BAD_REQUEST).build();
    }
}
