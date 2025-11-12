package model;
import Exceptions.NivelSenioridadeInvException;

public class Programador extends Funcionario{

    // Atributos
    private String linguagemP; //linguagem principal (predominante)
    private String nivelSen; //Nivel de Senioridade, ex: Junior, Pleno ou Senior

    // Construtor Default
    public Programador() {
        super();
        linguagemP = "";
        nivelSen = "";
    }

    //Construtor Sobrecarregado
    // Sobregarga
    public Programador(String nome, long cpf, double salario,String email, String linguagemP, String nivelSen) {
        super(nome, cpf, salario,email);
        this.linguagemP = linguagemP;
        this.nivelSen = nivelSen;
    }

    //Sobrescrita do método da classe mae
    public String gerarDescricaoCargo() {
        return "Nível " 
            + getNivelSen() 
            + ", especialista em " 
            + getLinguagemP() 
            + ".";
    }

    // Getters e Setters
    public String getLinguagemP() { 
        return linguagemP; 
    }
    public void setLinguagemP(String linguagemP) { 
        this.linguagemP = linguagemP; 
    }
    public String getNivelSen() { 
        return nivelSen; 
    }
    public void setNivelSen(String nivelSen) throws NivelSenioridadeInvException {
        if (nivelSen.equalsIgnoreCase("Junior") 
            || nivelSen.equalsIgnoreCase("Pleno") 
            || nivelSen.equalsIgnoreCase("Senior")) {
            this.nivelSen = nivelSen;
        } else {
            throw new NivelSenioridadeInvException();
        }
    }
    
    public String toString() {
        // O ComboBox vai chamar isso para exibir o nome
        return getNome() + " (Programador)";
    }
}



