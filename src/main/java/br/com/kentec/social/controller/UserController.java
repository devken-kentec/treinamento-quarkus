package br.com.kentec.social.controller;

import br.com.kentec.social.DTO.UserDTO;
import br.com.kentec.social.domain.User;
import br.com.kentec.social.service.UserService;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.*;

import java.util.List;


@Path("/user")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserController {
    @Inject
    private UserService us;

    @GET
    public Response listAllUser(){
        return  Response.ok(us.listarTodos()).build();
    }

    @GET
    @Path("/novalista")
    public Response listAllUsers(){
        PanacheQuery<User> query = us.listarUsers();
        return  Response.ok(query.list()).build();
    }

    @GET
    @Path("{id}")
    public Response buscarUm(@PathParam("id") Long id){
        return Response.ok(us.buscarUm(id)).build();
    }

    @GET
    @Path("/imp")
    public List<User> buscarTodosImp(){
        return us.buscarTodosImp();
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response deleteId(@PathParam("id") Long id){
        if(us.delete(id) == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @POST
    @Transactional
    public Response createUser( UserDTO userDTO ){
        User user = new User();
        user.setNome(userDTO.getNome());
        user.setIdade(userDTO.getIdade());
        us.createUser(user);
        return Response.ok(user).build();
    }

    @PUT
    @Transactional
    public Response updateUser(UserDTO userDTO){
        if(us.updateUser(userDTO) != null){
            return Response.ok(us.updateUser(userDTO)).build();
        }
        return Response.status(Response.Status.NO_CONTENT).build();
    }
}
