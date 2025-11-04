package Exceptions;
import model.Gerente;
//Augusto da Silva de Sá - RA: 2564319
import javax.swing.JOptionPane;

public class QtdEquipesInvException extends Exception {


    public void impErroEqInv() {
        JOptionPane.showMessageDialog(
                    null,
                    "Quantidade de equipes gerenciadas não pode ser menor ou igual a zero.",
                    "Cadastro de Gerente",
                    JOptionPane.ERROR_MESSAGE
            );
    }

    public Gerente corrGerenteEquipes(Gerente ger) {
        try {
            String novaQtd = JOptionPane.showInputDialog(
                                    null,
                                    "Digite a nova quantidade de equipes gerenciadas:",
                                    "Atualizar dados",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
            ger.setQtdEquipesGerenc(Integer.parseInt(novaQtd));
        } catch (QtdEquipesInvException eie) {
            impErroEqInv();
            ger = corrGerenteEquipes(ger);
        } catch (NumberFormatException nfe) {
            System.out.println("Valor inválido. Deve ser um número inteiro.");
            ger = corrGerenteEquipes(ger);
        }
        return ger;
    }
}

