package Exceptions;
import model.Programador;

//Augusto da Silva de Sá - RA: 2564319
import javax.swing.JOptionPane;

// Augusto da Silva de Sá - RA: 2564319
public class NivelSenioridadeInvException extends Exception {


    // Mensagem de erro personalizada
    public void impErroNivelInv() {
        JOptionPane.showMessageDialog(
                    null,
                    "Nível de senioridade inválido. Só são aceitos: Junior, Pleno ou Senior.",
                    "Cadastro de Programador",
                    JOptionPane.ERROR_MESSAGE
            );
    }

    // Método para corrigir o erro recursivamente
    public Programador corrNivelProgramador(Programador pro) {
        try {
            String novoNivel = JOptionPane.showInputDialog(
                                    null,
                                    "Informe o novo Nivel (Junior, Pleno ou Senior)",
                                    "Atualizar dados",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
            pro.setNivelSen(novoNivel);
        } 
        catch (NivelSenioridadeInvException nsie) {
            impErroNivelInv();
            pro = corrNivelProgramador(pro);
        }
        return pro;
    }
}
