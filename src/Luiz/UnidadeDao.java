package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Unidade;

public class UnidadeDao {
    Connection conexao;
    PreparedStatement ps;
    ResultSet rs;

    public UnidadeDao(){
        conexao = new ConexaoDB().getConexao();
    }
    public List<Unidade> get(){
        List<Unidade> registros = new ArrayList<>();
        String sql = "SELECT * FROM unidade";
        try{
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
            Unidade registro = new Unidade();
                    registro.setId(rs.getLong("id"));
                    registro.setEnderco(rs.getString("endereco"));
                    registro.setNome(rs.getString("nome"));
                    registros.add(registro);
            }
        }catch(SQLException e){
            e.printStackTrace();
        }
        return registros;
    }
    public Unidade get(Long id){
        Unidade registro = new Unidade();

        String sql = "SELECT * FROM unidade WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
                if(rs.next()){
                    registro.setId(rs.getLong("id"));
                    registro.setNome(rs.getString("nome"));
                    registro.setEnderco(rs.getString("endereco"));
                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }

    public List<Unidade> get(String termoBusca){
        List<Unidade> registros = new ArrayList<>();
        String sql = "SELECT * FROM unidade WHERE nome LIKE ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + termoBusca + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Unidade registro = new Unidade();
                registro.setId(rs.getLong("id"));
                registro.setNome(rs.getString("nome"));
                registro.setEnderco(rs.getString("endereco"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    public int insert(Unidade objeto){
        int registrosAfetados = 0;
        String sql = "INSERT INTO unidade (nome, endereco) VALUES (?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEnderco());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }
    public int update(Unidade objeto){
        int registrosAfetados = 0;
        String sql = "UPDATE unidade SET nome = ?, endereco = ? WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getNome());
            ps.setString(2, objeto.getEnderco());
            ps.setLong(3, objeto.getId());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    public int delete(Unidade objeto){
        int registrosAfetados = 0;
        String sql = "DELETE FROM unidade WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, objeto.getId());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }
   

}
