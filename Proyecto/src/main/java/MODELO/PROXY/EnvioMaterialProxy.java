package MODELO.PROXY;

import MODELO.INTERFACES.IEnvioMaterialService;

public class EnvioMaterialProxy implements IEnvioMaterialService{
    private final EnvioMaterialServiceReal servicioReal;
    private final boolean deseaRecibir;

    public EnvioMaterialProxy(EnvioMaterialServiceReal servicioReal, boolean deseaRecibir) {
        this.servicioReal = servicioReal;
        this.deseaRecibir = deseaRecibir;
    }

    @Override
    public void enviarMaterial(String correo, String comentario) {
        if (deseaRecibir) {
            servicioReal.enviarMaterial(correo, comentario);
        } else {
            System.out.println("Usuario no desea recibir materiales. Acción omitida.");
        }
    }
}
