/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author gabrielrm
 */
public class Clientes extends Pessoas{
    public int id;
    
    public Clientes(String nome, String cpf, String endereco,String telefone, String email, String obs, int idade, int id) {
        super(nome, cpf, endereco, telefone, email, obs, idade, id);
    }

    public Clientes() {
    }
    
    @Override
    public void desconto(double desc){
        this.desc = desc * 0.2;
    }

}