package MODELO.PROXY;

import MODELO.INTERFACES.IEnvioMaterialService;

public class EnvioMaterialServiceReal implements IEnvioMaterialService{
    @Override
    public void enviarMaterial(String correo, String comentario) {
        System.out.println("Material enviado a: " + correo);
        System.out.println("Comentario del usuario: " + comentario);
    }
}
