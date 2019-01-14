package service;

import ch.efg.sample.api.IUserService;
import dao.UserDAO;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;
import java.util.Map;

@Path("")
public class UserService implements IUserService<User, String> {
    private final static Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    private UserDAO userDAO = new UserDAO();

    @GET
    @Path("/hello")
    @Produces("text/plain")
    public String hello() {
        LOGGER.info("[UserService] hello");
        return "Hello!!";
    }

    // URI: /{war}/api/users
    @GET
    @Path("/users")
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> findAll() {
        LOGGER.info("[UserService] call /users");
        return userDAO.findAll();
    }

    // URI: /{war}/api/users/{userId}
    @GET
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public List<User> findById(@PathParam("userId") String s) {
        LOGGER.info("[UserService] call /user/{userId}");
        return userDAO.findById(s);
    }

    // URI: /{war}/api/users/add
    @POST
    @Path("/users/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public <S extends User> List<S> saveAll(Iterable<S> var1) {
        LOGGER.info("[UserService] call /users/add");
        return userDAO.saveAll(var1);
    }

    // URI: /{war}/api/user/add
    @POST
    @Path("/user/add")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public <S extends User> S save(S var1) {
        LOGGER.info("[UserService] call /user/add");
        return userDAO.save(var1);
    }

    // URI: /{war}/api/user/{userId}
    @DELETE
    @Path("/user/{userId}")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public User delete(String var1) {
        LOGGER.info("[UserService] call /user/{userId}");
        return userDAO.delete(var1);
    }

    // URI: /{war}/api/groups
    @GET
    @Path("/groups")
    @Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
    public Map<String, List<User>> findAllGroupByGroupId() {
        return userDAO.findAllGroupByGroupId();
    }
}
