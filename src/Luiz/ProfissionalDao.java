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

public class ProfissionalDao implements IDao<Profissional>{
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;
    private EspecialidadeDao eDao;
    private UnidadeDao uDao;

    public ProfissionalDao(){
        conexao = ConexaoDB.getConexao();
    }
    // public List<Profissional> get(){
    //     List<Profissional> registros = new ArrayList<>();
    //     String sql = "SELECT p.id, p.email, p.nome, p.registro_conselho, p.telefone, "
    //                + "e.nome AS especialidade_nome, "
    //                + "u.nome AS unidade_nome, u.endereco AS unidade_endereco "
    //                + "FROM profissional p "
    //                + "JOIN especialidade e ON p.especialidade_id = e.id "
    //                + "JOIN unidade u ON p.unidade_id = u.id";
    //     try{
    //         ps = conexao.prepareStatement(sql);
    //         rs = ps.executeQuery();
    //         while(rs.next()){
    //             Profissional registro = new Profissional();
    //             registro.setId(rs.getLong("id"));
    //             registro.setEmail(rs.getString("email"));
    //             registro.setNome(rs.getString("nome"));
    //             registro.setRegistro(rs.getString("registro_conselho"));
    //             registro.setTelefone(rs.getString("telefone"));
    
    //             Especialidade especialidade = new Especialidade();
    //             especialidade.setNome(rs.getString("especialidade_nome"));
    //             registro.setEspecialidade(especialidade);
    
    //             Unidade unidade = new Unidade();
    //             unidade.setNome(rs.getString("unidade_nome"));
    //             unidade.setEnderco(rs.getString("unidade_endereco"));
    //             registro.setUnidade(unidade);
    
    //             registros.add(registro);
    //         }
    //     } catch(SQLException e){
    //         e.printStackTrace();
    //     }
    //     return registros;

        
    // }

    public List<Profissional> get(){
        List<Profissional> registros = new ArrayList<>();
        String sql = "SELECT * from profissional";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profissional registro = new Profissional();
                registro.setId(rs.getLong("id"));
                registro.setNome(rs.getString("nome"));
                registro.setEmail(rs.getString("email"));
                registro.setRegistro(rs.getString("registro_conselho"));
                registro.setTelefone(rs.getString("telefone"));
                eDao = new EspecialidadeDao();
                registro.setEspecialidade(eDao.get(rs.getLong("especialidade_id")));
                uDao = new UnidadeDao();
                Unidade u = uDao.get(rs.getLong("unidade_id"));
                registro.setUnidade(u);
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
    public List<Profissional> get(String termoBusca) {
        List<Profissional> registros = new ArrayList<>();
        String sql = "SELECT p.id, p.email, p.nome, p.registro_conselho, p.telefone, "
                   + "e.nome AS especialidade_nome, "
                   + "u.nome AS unidade_nome, u.endereco AS unidade_endereco "
                   + "FROM profissional p "
                   + "JOIN especialidade e ON p.especialidade_id = e.id "
                   + "JOIN unidade u ON p.unidade_id = u.id "
                   + "WHERE p.nome LIKE ? "
                   + "OR p.email LIKE ? "
                   + "OR p.registro_conselho LIKE ? "
                   + "OR p.telefone LIKE ? "
                   + "OR e.nome LIKE ? "
                   + "OR u.nome LIKE ? "
                   + "OR u.endereco LIKE ?";
        
        try (PreparedStatement ps = conexao.prepareStatement(sql)) {
            String searchPattern = "%" + termoBusca + "%";
            for (int i = 1; i <= 7; i++) {
                ps.setString(i, searchPattern);
            }
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
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
   public int update(Profissional objeto){
        int registrosAfetados = 0;
        String sql = "UPDATE profissional SET email = ?, nome = ?, registro_conselho = ?, telefone = ?, especialidade_id = ?, unidade_id = ? WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getEmail());
            ps.setString(2, objeto.getNome());
            ps.setString(3, objeto.getRegistro());
            ps.setString(4, objeto.getTelefone());
            ps.setLong(5, objeto.getEspecialidade().getId()); 
            ps.setLong(6, objeto.getUnidade().getId());
            ps.setLong(7, objeto.getId());
            registrosAfetados = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();

        }
        return registrosAfetados;
   }
   public int delete(Profissional objeto){
        int registrosAfetados = 0;
        String sql = "DELETE FROM profissional WHERE id = ?";
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
