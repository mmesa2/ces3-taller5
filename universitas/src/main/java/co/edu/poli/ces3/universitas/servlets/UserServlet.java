package co.edu.poli.ces3.universitas.servlets;


import co.edu.poli.ces3.universitas.database.dao.User;
import co.edu.poli.ces3.universitas.database.repositories.UserRepository;
import com.google.gson.GsonBuilder;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.protobuf.AbstractMessage;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet(name = "userServlet", value = "/api/user")
public class UserServlet extends MyServlet {

    private UserRepository userRepository = new UserRepository();
    public static User userUpdate(int id, User updatedUser) {
        return updatedUser;
    };
    private GsonBuilder gsonBuilder;
    private Gson gson;

    public void init(){
        gsonBuilder = new GsonBuilder();
        gson = gsonBuilder.create();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        UserRepository repo = new UserRepository();
        try {
            out.print(gson.toJson(repo.get()));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        out.flush();
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        int id = Integer.parseInt(req.getParameter("id"));
        JsonObject userUpdate = this.getParamsFromPost(req);
        UserRepository repo = new UserRepository();
        try {
            User user = repo.update(userUpdate, id);
            out.println(gson.toJson(user));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();
        out.println("{message: 'hola!!!'}");
        out.flush();
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        PrintWriter out = resp.getWriter();

        try {
            // Leer el cuerpo de la solicitud
            BufferedReader reader = req.getReader();
            AbstractMessage objectMapper;
            objectMapper = null;
            BufferedReader reader1 = reader;
            BufferedReader updatedUser = (reader);

            // Obtener el parámetro idUser
            String idParam = req.getParameter("idUser");
            if (idParam == null || idParam.isEmpty()) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing or invalid idUser parameter");
                return;
            }

            int id = Integer.parseInt(idParam);
            User updated;
            updated = UserRepository.userUpdate(id,
                    updatedUser);
            if (updated != null) {
                out.println(objectMapper.toString());
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND, "User not found");
            }
        } catch (SQLException e) {
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, e.getMessage());
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid idUser parameter");
        } finally {
            out.flush();
        }
    }
}
