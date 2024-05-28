package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Profissional;
import br.ufac.sgcm.model.Unidade;

public class ProfissionalDao implements IDao<Profissional> {

    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;
    private EspecialidadeDao eDao;
    private UnidadeDao uDao;

    public ProfissionalDao() {
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public int delete(Profissional objeto) {
        int regAfetado = 0;
        String sql = "DELETE FROM profissional WHERE id = ?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, objeto.getId());
            regAfetado = ps.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
        return regAfetado;
    }

    @Override
    public List<Profissional> get() {
        List<Profissional> registros = new ArrayList<>();
        String sql = "SELECT * FROM profissional";
        String sql2 = "SELECT p.id, p.nome, p.email, " +
                "p.registro_conselho, p.telefone, e.nome, u.nome " +
                "FROM profissional AS p" +
                "JOIN especialidade AS e ON p.especialidade_id=e.id" +
                "JOIN unidade AS u ON p.unidade_id=u.id";
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public Profissional get(Long id) {
        Profissional reg = new Profissional();
        String sql = "SELECT * FROM profissional WHERE id = ?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()){
                reg.setId(rs.getLong("id"));
                reg.setNome(rs.getString("nome"));
                reg.setEmail(rs.getString("email"));
                reg.setRegistro(rs.getString("registro_conselho"));
                reg.setTelefone(rs.getString("telefone"));
                eDao = new EspecialidadeDao();
                reg.setEspecialidade(eDao.get(rs.getLong("especialidade_id")));
                uDao = new UnidadeDao();
                Unidade u = uDao.get(rs.getLong("unidade_id"));
                reg.setUnidade(u);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return reg;
    }

    @Override
    public List<Profissional> get(String termoBusca) {
        List<Profissional> regs = new ArrayList<>();
        String sql = "SELECT * FROM profissional WHERE nome LIKE ? OR email LIKE ? OR registro_conselho LIKE ? OR telefone LIKE ?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%"+termoBusca+"%");
            ps.setString(2, "%"+termoBusca+"%");
            ps.setString(3, "%"+termoBusca+"%");
            ps.setString(4, "%"+termoBusca+"%");
            rs = ps.executeQuery();
            while (rs.next()){
                Profissional reg = new Profissional();
                reg.setId(rs.getLong("id"));
                reg.setNome(rs.getString("nome"));
                reg.setEmail(rs.getString("email"));
                reg.setRegistro(rs.getString("registro_conselho"));
                reg.setTelefone(rs.getString("telefone"));
                eDao = new EspecialidadeDao();
                reg.setEspecialidade(eDao.get(rs.getLong("especialidade_id")));
                uDao = new UnidadeDao();
                Unidade u = uDao.get(rs.getLong("unidade_id"));
                reg.setUnidade(u);
                regs.add(reg);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }

        return regs;
    }

    @Override
    public int insert(Profissional objeto) {
        int registrosAfetados = 0;
        String sql = "INSERT INTO profissional (email,nome,registro_conselho,telefone,especialidade_id,unidade_id) VALUES (?,?,?,?,?,?)";
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

    @Override
    public int update(Profissional objeto) {
        int regAfetado = 0;
        String sql = "UPDATE profissional SET email=?,nome=?,registro_conselho=?,telefone=?,especialidade_id=?,unidade_id=? WHERE id = ?";
        try{
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getEmail());
            ps.setString(2, objeto.getNome());
            ps.setString(3, objeto.getRegistro());
            ps.setString(4, objeto.getTelefone());
            ps.setLong(5, objeto.getEspecialidade().getId());
            ps.setLong(6, objeto.getUnidade().getId());
            ps.setLong(7, objeto.getId());
            regAfetado = ps.executeUpdate();
        }catch(SQLException e){
            e.printStackTrace();
        }
        return regAfetado;
    }

}
