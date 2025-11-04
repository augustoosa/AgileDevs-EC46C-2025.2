//Augusto da Silva de Sá - RA: 2564319
package model;
import Interfaces.Calc;
import Exceptions.QtdEquipesInvException;

public class Gerente extends Funcionario implements Calc{

    // Atributos
    private String departamento ; 
    private int qtdEquipesGerenc;      // Quantidade de equipes gerenciadas

    // Construtor Default
    public Gerente() {
        super();
        departamento = "";
        qtdEquipesGerenc = 0;
    }

    // Construtor com Sobrecarregado
    //Sobrecarga
    public Gerente(String nome, int cpf, double salario,String email,String departamento, String tipoGerencia, int qtdEquipesGerenc) {
        super(nome, cpf, salario,email);
        this.departamento = departamento;
        this.qtdEquipesGerenc = qtdEquipesGerenc;
    }

    // Sobrescrita do método da interface
    public double calcBonus() {
        return getSalario() * 0.2 * getQtdEquipesGerenc();
    }

    // Sobrescrita do método da classe mae
    public String gerarDescricaoCargo() {
        return "Departamento de " 
        + getDepartamento()
        + ", responsável por " 
        + getQtdEquipesGerenc() 
        + " equipes.";
    }

    // Getters e Setters
    //Tratamento de exception para quatidade de equipes menor ou igual a zero
    public void setQtdEquipesGerenc(int qtdEquipesGerenc) throws QtdEquipesInvException{
		if(qtdEquipesGerenc> 0){
		    this.qtdEquipesGerenc = qtdEquipesGerenc;
        }
		else{
			throw new QtdEquipesInvException();
		}
		
	}

    public String getDepartamento() {
        return departamento;
    }
    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public int getQtdEquipesGerenc() {
        return qtdEquipesGerenc;
    }
    
    public String toString() {
        // O ComboBox vai chamar isso para exibir o nome
        return getNome() + " (Gerente)";
    }
}
