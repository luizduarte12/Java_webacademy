package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import br.ufac.sgcm.model.Especialidade;
import br.ufac.sgcm.model.Profissional;
import br.ufac.sgcm.model.Unidade;

public class ProfissionalDao {
    Connection conexao;
    PreparedStatement ps;
    ResultSet rs;

    public ProfissionalDao(){
        conexao = new ConexaoDB().getConexao();
    }
    public List<Profissional> get(){
        List<Profissional> registros = new ArrayList<>();
        String sql = "SELECT p.id, p.email, p.nome, p.registro_conselho, p.telefone, "
                   + "e.nome AS especialidade_nome, "
                   + "u.nome AS unidade_nome, u.endereco AS unidade_endereco "
                   + "FROM profissional p "
                   + "JOIN especialidade e ON p.especialidade_id = e.id "
                   + "JOIN unidade u ON p.unidade_id = u.id";
        try{
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Profissional registro = new Profissional();
                registro.setId(rs.getLong("id"));
                registro.setEmail(rs.getString("email"));
                registro.setNome(rs.getString("nome"));
                registro.setRegistro(rs.getString("registro_conselho"));
                registro.setTelefone(rs.getString("telefone"));
    
                Especialidade especialidade = new Especialidade();
                especialidade.setNome(rs.getString("especialidade_nome"));
                registro.setEspecialidade(especialidade);
    
                Unidade unidade = new Unidade();
                unidade.setNome(rs.getString("unidade_nome"));
                unidade.setEnderco(rs.getString("unidade_endereco"));
                registro.setUnidade(unidade);
    
                registros.add(registro);
            }
        } catch(SQLException e){
            e.printStackTrace();
        }
        return registros;

        
    }
    public Profissional get(Long id){
        Profissional registro = new Profissional();
        String sql = "SELECT p.id, p.email, p.nome, p.registro_conselho, p.telefone, "
               + "e.nome AS especialidade_nome, "
               + "u.nome AS unidade_nome, u.endereco AS unidade_endereco "
               + "FROM profissional p "
               + "JOIN especialidade e ON p.especialidade_id = e.id "
               + "JOIN unidade u ON p.unidade_id = u.id "
               + "WHERE p.id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
                if(rs.next()){
                    registro.setId(rs.getLong("id"));
                registro.setEmail(rs.getString("email"));
                registro.setNome(rs.getString("nome"));
                registro.setRegistro(rs.getString("registro_conselho"));
                registro.setTelefone(rs.getString("telefone"));

                Especialidade especialidade = new Especialidade();
                especialidade.setNome(rs.getString("especialidade_nome"));
                registro.setEspecialidade(especialidade);
    
                Unidade unidade = new Unidade();
                unidade.setNome(rs.getString("unidade_nome"));
                unidade.setEnderco(rs.getString("unidade_endereco"));
                registro.setUnidade(unidade);

                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }
    public List<Profissional> get(String termoBusca){
        List<Profissional> registros = new ArrayList<>();
        Profissional registro = new Profissional();
        String sql = "SELECT p.id, p.email, p.nome, p.registro_conselho, p.telefone, "
               + "e.nome AS especialidade_nome, "
               + "u.nome AS unidade_nome, u.endereco AS unidade_endereco "
               + "FROM profissional p "
               + "JOIN especialidade e ON p.especialidade_id = e.id "
               + "JOIN unidade u ON p.unidade_id = u.id "
               + "WHERE p.nome LIKE ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + termoBusca + "%");
            rs = ps.executeQuery();
                if(rs.next()){
                    registro.setId(rs.getLong("id"));
                registro.setEmail(rs.getString("email"));
                registro.setNome(rs.getString("nome"));
                registro.setRegistro(rs.getString("registro_conselho"));
                registro.setTelefone(rs.getString("telefone"));

                Especialidade especialidade = new Especialidade();
                especialidade.setNome(rs.getString("especialidade_nome"));
                registro.setEspecialidade(especialidade);
    
                Unidade unidade = new Unidade();
                unidade.setNome(rs.getString("unidade_nome"));
                unidade.setEnderco(rs.getString("unidade_endereco"));
                registro.setUnidade(unidade);

                registros.add(registro);

                }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }
    public int insert(Profissional objeto){
        int registrosAfetados = 0;
        String sql = "INSERT INTO profissional (email, nome, registro_conselho, telefone, especialidade_id, unidade_id) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getEmail());
            ps.setString(2, objeto.getNome());
            ps.setString(3, objeto.getRegistro());
            ps.setString(4, objeto.getTelefone());
            ps.setLong(5, objeto.getEspecialidade().getId()); 
            ps.setLong(6, objeto.getUnidade().getId());

            registrosAfetados = ps.executeUpdate();  
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
}


    
}