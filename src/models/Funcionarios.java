/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

/**
 *
 * @author Patrick
 */
public class Funcionarios extends Pessoas{
    
    public Funcionarios(String nome, String cpf, String endereco,String telefone, String email, String observacao, int idade,int id) {
        super(nome, cpf, endereco, telefone, email, observacao, idade,id);
    }  

    public Funcionarios() {
    }
    
    
    @Override
    public void desconto(double desc){
        this.desc = desc * 0.3;
    }
    
}
