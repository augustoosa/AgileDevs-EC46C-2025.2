//Augusto da Silva de Sá - RA: 2564319
package model;
import Interfaces.Calc;
public class AnalistaDeDados extends Funcionario implements Calc{

    // Atributos específicos
    private String ferramenta;
    private int qtdRelatorios;

    // Construtor Default
    public AnalistaDeDados() {
        super();
        this.ferramenta = "";
        this.qtdRelatorios = 0;
    }

    // Sobrecarga
    public AnalistaDeDados(String nome, int cpf,String email, double salario,String ferramenta, int qtdRelatorios) {
        super(nome, cpf, salario,email);
        this.ferramenta = ferramenta;
        this.qtdRelatorios = qtdRelatorios;
    }

    // Sobrescrita do método da interface
    public double calcBonus() {
        return getSalario() * 0.1 * getQtdRelatorios();
    }

    // Sobrescrita do método da classe mae
    public String gerarDescricaoCargo() {
        return "Usa " + getFerramenta()
            + " e produz " + getQtdRelatorios() + " relatórios mensais.";
    }

    // Getters e Setters
    public String getFerramenta() {
        return ferramenta;
    }

    public void setFerramenta(String ferramenta) {
        this.ferramenta = ferramenta;
    }

    public int getQtdRelatorios() {
        return qtdRelatorios;
    }

    public void setQtdRelatorios(int qtdRelatorios) {
        this.qtdRelatorios = qtdRelatorios;
    }
    
    @Override
    public String toString() {
        return getNome() + " (Analista)";
    }
}