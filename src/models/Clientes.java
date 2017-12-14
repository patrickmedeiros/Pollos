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
    
    public Clientes(String nome, String cpf, String endereco,String telefone, String email, String obs, int idade, int id, int funcionarios) {
        super(nome, cpf, endereco, telefone, email, obs, idade, id, funcionarios);
    }

    public Clientes() {
    }
    
    @Override
    public double desconto(double desc){
        return this.desc = desc * 0.2;
    }

}