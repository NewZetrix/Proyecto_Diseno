/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package MODELO;

/**
 *
 * @author User
 */
public class InvocadorCommand {
    private ICommand comando;

    public void setCommand(ICommand comando) {
        this.comando = comando;
    }

    public void ejecutarComando() {
        if (comando != null) {
            comando.ejecutar();
        }
    }
}
