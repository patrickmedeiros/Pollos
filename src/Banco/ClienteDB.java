/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import models.Clientes;
import models.Pessoas;

/**
 *
 * @author gabrielrm
 */
public class ClienteDB {
    private Conector db;
    
    public ClienteDB(Conector db){
        this.db = db;
    }
    
    // Busca todos os clientes cadastrados no banco e retorna ArrayList
    public ArrayList<Clientes> listaclientesgeral(){
     try{
         String SQL = "SELECT * FROM clientes";
         PreparedStatement ps = db.getConnections().prepareStatement(SQL);
         ResultSet rs = ps.executeQuery();
         ArrayList<Clientes> lista = new ArrayList<Clientes>();
         while(rs.next()){
             Pessoas cli = new Clientes();
             cli.setId(rs.getInt("id"));
             cli.setCpf(rs.getString("cpf"));
             cli.setNome(rs.getString("nome"));
             cli.setEmail(rs.getString("email"));
             cli.setTelefone(rs.getString("telefone"));
             cli.setEndereco(rs.getString("endereco"));
             cli.setObs(rs.getString("observacoes"));
             lista.add((Clientes) cli);
         }
         ps.close();
         return lista;
     }catch(SQLException ex){
         System.err.println("Erro ao tentar recuperar os dados "+ex.getMessage());
     }catch(Exception ex){
         System.err.println("Erro geral ao buscar clientes");
     }
     return null;
    }

    // Cadastra Cliente
    public String cadastraCliente(String nome, String cpf, String telefone, String endereco, String obs, String email, int idade){
        //Chamamos a classe Pedidos e atribuimos o valor x
        Pessoas x = new Clientes();
        // Vamos setando os valores do nosso cliente conforme os valores que foram passados por parametro
        x.setNome(nome);
        x.setCpf(cpf);
        x.setTelefone(telefone);
        x.setEndereco(endereco);
        x.setObs(obs);
        x.setEmail(email);
        x.setIdade(idade);
        //if(idade <= 0 ){
           // return "Digite uma idade válida.";
       // }
        //if(cpf.length() != 11){
          //  return "CPF inválido!";
        //}
        String sql = "INSERT INTO clientes(cpf, nome, email, idade, telefone, endereco, observacoes) VALUES(?,?,?,?,?,?,?)";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql);    
            stmt.setString(1, x.getCpf());
            stmt.setString(2, x.getNome());    
            stmt.setString(3, x.getEmail());
            stmt.setInt(4, x.getIdade());
            stmt.setString(5, x.getTelefone());  
            stmt.setString(6, x.getEndereco());  
            stmt.setString(7, x.getObs());  
            stmt.execute(); //executa comando   
            stmt.close();
            return "Cadastro Realizado com sucesso!";
        } catch (SQLException u) {    
            throw new RuntimeException(u);    
        } 
    }
    
    // Busca cliente
    public Clientes buscaClienteUnico(int id){
        try{
            String SQL = "SELECT * FROM clientes WHERE id = ?";
            PreparedStatement ps = db.getConnections().prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Pessoas cli = new Clientes();
            cli.setId(rs.getInt("id"));
            cli.setIdade(rs.getInt("idade"));
            cli.setCpf(rs.getString("cpf"));
            cli.setNome(rs.getString("nome"));
            cli.setEmail(rs.getString("email"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setObs(rs.getString("observacoes"));
            ps.close();
            return (Clientes) cli;
        }catch(SQLException ex){
         System.err.println("Erro ao tentar recuperar os dados "+ex.getMessage());
     }catch(Exception ex){
         System.err.println("Erro geral ao buscar cliente unico");
     }
        
        return null;
    }
    
    /// Atualiza Cliente
    public String updateCliente(int id, String nome, String cpf, String telefone, String endereco, String obs, String email, int idade){
        //Chamamos a classe Pedidos e atribuimos o valor x
        Pessoas x = new Clientes();
        // Vamos setando os valores do nosso cliente conforme os valores que foram passados por parametro
        x.setNome(nome);
        x.setCpf(cpf);
        x.setTelefone(telefone);
        x.setEndereco(endereco);
        x.setObs(obs);
        x.setEmail(email);
        x.setIdade(idade);
        x.setId(id);
        String sql = "UPDATE clientes SET cpf =?, nome =?, email =?, idade =?, telefone =?, endereco =?, observacoes =? WHERE id = ?";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql);    
            stmt.setString(1, x.getCpf());
            stmt.setString(2, x.getNome());    
            stmt.setString(3, x.getEmail());
            stmt.setInt(4, x.getIdade());
            stmt.setString(5, x.getTelefone());  
            stmt.setString(6, x.getEndereco());  
            stmt.setString(7, x.getObs());
            stmt.setInt(8, x.getId());
            stmt.execute(); //executa comando   
            stmt.close();
            return "Atualização realizada com sucesso!";
        }catch(SQLException ex){
         System.err.println("Erro ao tentar recuperar os dados "+ex.getMessage());
        }catch(Exception ex){
         System.err.println("Erro geral ao buscar cliente unico");
        } 
        return null;
    }
    
    // Exclui cliente no banco
    public String deleteCliente(int id){
        //Chamamos a classe Pedidos e atribuimos o valor x
        Pessoas x = new Clientes();
        // Vamos setando os valores do nosso cliente conforme os valores que foram passados por parametro
        x.setId(id);
        String sql = "DELETE FROM clientes WHERE id = ?";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql); 
            stmt.setInt(1, x.getId());
            stmt.execute(); //executa comando   
            stmt.close();
            return "Exclusão realizada com sucesso!";
        }catch(SQLException ex){
         System.err.println("Erro ao tentar excluir os dados "+ex.getMessage());
        }catch(Exception ex){
         System.err.println("Erro geral ao excluir cliente");
        } 
        return null;
    }
    
}
