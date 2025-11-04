package model; // (ou o pacote onde suas classes de modelo estão)

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Projeto {

    // --- ATRIBUTO ADICIONADO ---
    private int idProjeto; // Chave primária do banco de dados
    
    private String nomeProjeto;
    private String status;
    private Date dataInicio;
    private Date prazoFinal;
    private Gerente gerenteResponsavel;
    private List<Funcionario> equipe;

    // Construtor
    public Projeto() {
        this.equipe = new ArrayList<>();
    }
    
    // Construtor Sobrecarregado
    public Projeto(String nomeProjeto, String status, Date dataInicio, Date prazoFinal, Gerente gerenteResponsavel) {
        this.nomeProjeto = nomeProjeto;
        this.status = status;
        this.dataInicio = dataInicio;
        this.prazoFinal = prazoFinal;
        this.gerenteResponsavel = gerenteResponsavel;
        this.equipe = new ArrayList<>();
    }

    // Método para adicionar membros à equipe
    public void adicionarMembroEquipe(Funcionario novoMembro) {
        if (novoMembro != null) {
            this.equipe.add(novoMembro);
        }
    }

    // --- GETTERS E SETTERS ADICIONADOS ---
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }
    
    // --- Getters e Setters existentes ---
    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getPrazoFinal() {
        return prazoFinal;
    }

    public void setPrazoFinal(Date prazoFinal) {
        this.prazoFinal = prazoFinal;
    }

    public Gerente getGerenteResponsavel() {
        return gerenteResponsavel;
    }

    public void setGerenteResponsavel(Gerente gerenteResponsavel) {
        this.gerenteResponsavel = gerenteResponsavel;
    }

    public List<Funcionario> getEquipe() {
        return equipe;
    }

    public void setEquipe(List<Funcionario> equipe) {
        this.equipe = equipe;
    }
}