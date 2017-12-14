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
    
    public Funcionarios(String nome, String cpf, String endereco,String telefone, String email, String observacao, int idade,int id, int funcionarios) {
        super(nome, cpf, endereco, telefone, email, observacao, idade, id, funcionarios);
    }  

    public Funcionarios() {
    }
    
    
    @Override
    public double desconto(double desc){
        return this.desc = desc * 0.4;
    }
    
}
