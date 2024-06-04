package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import br.ufac.sgcm.model.Convenio;
import br.ufac.sgcm.model.Unidade;

public class ConvenioDao implements IDao<Convenio>{
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public ConvenioDao(){
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public int delete(Convenio objeto) {
         int registrosAfetados = 0;
        String sql = "DELETE FROM convenio WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, objeto.getId());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public List<Convenio> get() {
        List<Convenio> registros = new ArrayList<>();
        String sql = "SELECT * FROM convenio";
        try {
            ps = conexao.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Convenio registro = new Convenio();
                registro.setId(rs.getLong("id"));
                registro.setAtivo(rs.getString("ativo"));
                registro.setCnpj(rs.getString("cnpj"));
                registro.setEmail(rs.getString("email"));
                registro.setNome(rs.getString("nome"));
                registro.setRasao_social(rs.getString("razao_social"));
                registro.setRepresentante(rs.getString("representante"));
                registro.setTelefone(rs.getString("telefone"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public Convenio get(Long id) {
        Convenio registro = new Convenio();
        String sql = "SELECT * FROM convenio WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setLong(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                registro.setId(rs.getLong("id"));
                registro.setAtivo(rs.getString("ativo"));
                registro.setCnpj(rs.getString("cnpj"));
                registro.setEmail(rs.getString("email"));
                registro.setNome(rs.getString("nome"));
                registro.setRasao_social(rs.getString("razao_social"));
                registro.setRepresentante(rs.getString("representante"));
                registro.setTelefone(rs.getString("telefone"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registro;
    }

    @Override
    public List<Convenio> get(String termoBusca) {
        List<Convenio> registros = new ArrayList<>();
        String sql = "SELECT * FROM convenio WHERE nome LIKE ? OR cnpj LIKE ? OR email LIKE ? OR razao_social LIKE ? OR representante LIKE ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, "%" + termoBusca + "%");
            ps.setString(2, "%" + termoBusca + "%");
            ps.setString(3, "%" + termoBusca + "%");
            ps.setString(4, "%" + termoBusca + "%");
            ps.setString(5, "%" + termoBusca + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                Convenio registro = new Convenio();
                registro.setId(rs.getLong("id"));
                registro.setAtivo(rs.getString("ativo"));
                registro.setCnpj(rs.getString("cnpj"));
                registro.setEmail(rs.getString("email"));
                registro.setNome(rs.getString("nome"));
                registro.setRasao_social(rs.getString("razao_social"));
                registro.setRepresentante(rs.getString("representante"));
                registro.setTelefone(rs.getString("telefone"));
                registros.add(registro);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registros;
    }

    @Override
    public int insert(Convenio objeto) {
        int registrosAfetados = 0;
        String sql = "INSERT INTO convenio (ativo,cnpj,email,nome,razao_social,representante,telefone) VALUES (?,?,?,?,?,?,?)";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getAtivo());
            ps.setString(2, objeto.getCnpj());
            ps.setString(3, objeto.getEmail());
            ps.setString(4, objeto.getNome());
            ps.setString(5, objeto.getRazao_social());
            ps.setString(6, objeto.getRepresentante());
            ps.setString(7, objeto.getTelefone());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }

    @Override
    public int update(Convenio objeto) {
        int registrosAfetados = 0;
        String sql = "UPDATE convenio SET ativo = ?, cnpj = ?, email = ?, nome = ?, razao_social = ?, representante = ?, telefone = ?" +
                "WHERE id = ?";
        try {
            ps = conexao.prepareStatement(sql);
            ps.setString(1, objeto.getAtivo());
            ps.setString(2, objeto.getCnpj());
            ps.setString(3, objeto.getEmail());
            ps.setString(4, objeto.getNome());
            ps.setString(5, objeto.getRazao_social());
            ps.setString(6, objeto.getRepresentante());
            ps.setString(7, objeto.getTelefone());
            ps.setLong(8, objeto.getId());
            registrosAfetados = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrosAfetados;
    }
    
}
