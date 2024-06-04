package br.ufac.sgcm.controller;

import java.io.IOException;

import br.ufac.sgcm.dao.UsuarioDao;
import br.ufac.sgcm.model.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;
    private UsuarioDao usuarioDao;

    public void init() {
        usuarioDao = new UsuarioDao();
    }
     protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nomeUsuario = request.getParameter("username");
        String senha = request.getParameter("password");

        Usuario usuario = usuarioDao.getUsuario(nomeUsuario, senha);

        if (usuario != null) {
            HttpSession session = request.getSession();
            session.setAttribute("usuario", usuario);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("errorMessage", "Nome de usuário ou senha inválidos");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
