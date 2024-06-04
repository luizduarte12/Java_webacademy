package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Usuario;

public class UsuarioDao{
    Connection conexao;
    PreparedStatement ps;
    ResultSet rs;

    public UsuarioDao (){
        conexao = ConexaoDB.getConexao();
    }
    public Usuario getUsuario(String nomeUsuario, String senha) {
        Usuario usuario = null;
        String sql = "SELECT * FROM usuarios WHERE nomeUsuario = ? AND senha = ? AND ativo = true";
            try {
                ps = conexao.prepareStatement(sql);
                ps.setString(1, nomeUsuario);
                ps.setString(2, senha);
                rs = ps.executeQuery();
                if (rs.next()) {
                    usuario = new Usuario();
                    usuario.setId(rs.getLong("id"));
                    usuario.setNomeCompleto(rs.getString("nomeCompleto"));
                    usuario.setNomeUsuario(rs.getString("nomeUsuario"));
                    usuario.setSenha(rs.getString("senha"));
                    usuario.setPapel(rs.getString("papel"));
                    usuario.setAtivo(rs.getBoolean("ativo"));
                }
            }catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }
    
}
