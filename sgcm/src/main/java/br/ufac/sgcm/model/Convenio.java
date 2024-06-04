package br.ufac.sgcm.model;

import java.io.Serializable;

public class Convenio implements Serializable{
    private Long id;
    private String ativo;
    private String cnpj;
    private String email;
    private String nome;
    private String razao_social;
    private String representante;
    private String telefone;

    public long getId(){
        return this.id;
    }
    public void setId(Long id){
        this.id = id;
    }

    public String getAtivo(){
        return this.ativo;
    }
    public void setAtivo(String ativo){
        this.ativo = ativo;
    }

    public String getCnpj(){
        return this.cnpj;
    }
    public void setCnpj(String cnpj){
        this.cnpj = cnpj;
    }

    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }

    public String getNome(){
        return this.nome;
    }
    public void setNome(String nome){
        this.nome = nome;
    }

    public String getRazao_social(){
        return this.razao_social;
    }
    public void setRasao_social(String razao_social){
        this.razao_social = razao_social;
    }

    public String getRepresentante(){
        return this.representante;
    }
    public void setRepresentante(String representante){
        this.representante = representante;
    }

    public String getTelefone(){
        return this.telefone;
    }
    public void setTelefone(String telefone){
        this.telefone = telefone;
    }
}
