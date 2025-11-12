package model;
import java.util.Date;
import java.util.Calendar;
import java.text.SimpleDateFormat;

// Classe Mae
public abstract class Funcionario {

    // Atributos
    private String nome;
    private long cpf;
    private double salario;
    private String email; 

    // Construtor Default
    public Funcionario() {
        nome = "";
        cpf = 0;
        email = "";
        salario = 0.0;
    }

    // Construtor com Sobrecarga - ATUALIZADO
    public Funcionario(String nome, long cpf, double salario,String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
        this.salario = salario;
    }

    // Método abstrato a ser sobrescrito nas classes filhas
    public abstract String gerarDescricaoCargo();

    // Getters e Setters
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }
    public void setCpf(long cpf) {
        this.cpf = cpf;
    }
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }
}