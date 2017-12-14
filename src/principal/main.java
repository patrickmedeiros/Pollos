/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import trabalholab1.principal;

/**
 *
 * @author gabrielrm
 */
public class main {
    
    public static void main(String[] args) {
        //Chamamos nosso jframe e atribuimos a form
        principal form = new principal();
        //Aqui setamos para abrir no centro quando aberto
        form.setLocationRelativeTo(null);
        //Deixamos o jframe visivel
        form.setVisible(true);
    }
}
