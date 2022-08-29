package com.quarkus.demo;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Path("/employees")
public class EmployeeResource {

    public static List<String> employees = new ArrayList<String>();

    @GET
    @Produces(MediaType.TEXT_PLAIN)
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
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response createEmployee(String employee){
        employees.add(employee);
        return Response.ok(employees).build();
    }

    @PUT
    @Path("{employeeToUpdate}")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response updateEmployee(
            @PathParam("employeeToUpdate") String employeeToUpdate,
            @QueryParam("newEmployee") String newEmployee){

        employees = employees.stream().map(
                employee -> {
                    if (employee.equals(employeeToUpdate)){
                        return newEmployee;
                    }
                    return employee;
                }
        ).collect(Collectors.toList());
        return Response.ok(employees).build();
    }


    @DELETE
    @Path("{employeeToDelete}")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response deleteEmployee(
            @PathParam("employeeToDelete") String employeeToDelete ){

        boolean deleted = employees.remove(employeeToDelete);
        return deleted? Response.noContent().build():
                Response.status(Response.Status.BAD_REQUEST).build();
    }
}
