package br.ufac.sgcm.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class ConvenioDao implements IDao<Convenio>{
    private Connection conexao;
    private PreparedStatement ps;
    private ResultSet rs;

    public ConvenioDao(){
        conexao = ConexaoDB.getConexao();
    }

    @Override
    public int delete(Convenio objeto) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public List<Convenio> get() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Convenio get(Long id) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Convenio> get(String termoBusca) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public int insert(Convenio objeto) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int update(Convenio objeto) {
        // TODO Auto-generated method stub
        return 0;
    }
    
}
