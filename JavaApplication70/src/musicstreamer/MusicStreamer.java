/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package musicstreamer;

import Controlador.Controlador;
import Modelo.MusicClient;
import Vista.*;

/**
 *
 * @author Lighthouse
 */
public class MusicStreamer {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        MusicClient modelo = new MusicClient("localhost",2060);
        modelo.init();
        Menu menu = new Menu();
        Registro registro = new Registro();
        Ingreso ingreso = new Ingreso();
        Reproductor reproductor = new Reproductor();
        modelo.addObserver(menu);
        modelo.addObserver(registro);
        modelo.addObserver(ingreso);
        modelo.addObserver(reproductor);
        
        Controlador controlador = new Controlador();
        controlador.setModelo(modelo);
        controlador.setMenu(menu);
        controlador.setRegistro(registro);
        controlador.setIngreso(ingreso);
        controlador.setReproductor(reproductor);
        //controlador.RecibirDatos();
        
    }

}
