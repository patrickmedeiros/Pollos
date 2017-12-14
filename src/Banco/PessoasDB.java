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
import models.Funcionarios;
import models.Pessoas;

/**
 *
 * @author gabrielrm
 */

public class PessoasDB {
    private Conector db;
    
    public PessoasDB(Conector db){
        this.db = db;
    }
    
    // Busca todos as pessoas cadastrados no banco e retorna ArrayList
    public ArrayList<Clientes> listapessoasgeral(){
     try{
         String SQL = "SELECT * FROM pessoas";
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
    public String cadastraPessoa(String nome, String cpf, String telefone, String endereco, String obs, String email, int idade, int funcionario){
   
        //Chamamos a classe Pedidos e atribuimos o valor x
        Pessoas x = new Pessoas();
        // Vamos setando os valores do nosso cliente conforme os valores que foram passados por parametro
        x.setNome(nome);
        x.setCpf(cpf);
        x.setTelefone(telefone);
        x.setEndereco(endereco);
        x.setObs(obs);
        x.setEmail(email);
        x.setFuncionario(funcionario);
        x.setIdade(idade);
        
        if(cpf.length() != 11){
            return "CPF inválido!";
        }
        String sql = "INSERT INTO pessoas (cpf, nome, email, idade, telefone, endereco, observacoes, funcionario) VALUES(?,?,?,?,?,?,?,?)";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql);    
            stmt.setString(1, x.getCpf());
            stmt.setString(2, x.getNome());    
            stmt.setString(3, x.getEmail());
            stmt.setInt(4, x.getIdade());
            stmt.setString(5, x.getTelefone());  
            stmt.setString(6, x.getEndereco());  
            stmt.setString(7, x.getObs()); 
            stmt.setInt(8, x.getFuncionario());
            stmt.execute(); //executa comando   
            stmt.close();
            return "Cadastro Realizado com sucesso!";
        } catch (SQLException u) {    
            throw new RuntimeException(u);    
        } 
    }
    // Busca Pessoa
    public Pessoas buscaClienteUnico(int id){
        try{
            String SQL = "SELECT * FROM pessoas WHERE id = ?";
            PreparedStatement ps = db.getConnections().prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Pessoas cli = new Pessoas();
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
         System.err.println("Erro ao tentar recuperar os dados cliente "+ex.getMessage());
     }catch(Exception ex){
         System.err.println("Erro geral ao buscar cliente unico");
     }
        
        return null;
    }
    // Atualiza Pessoa
    public String updatePessoa(int id, String nome, String cpf, String telefone, String endereco, String obs, String email, int idade, int funcionario){
        //Chamamos a classe Pedidos e atribuimos o valor x
        Pessoas x = new Pessoas();
        // Vamos setando os valores do nosso cliente conforme os valores que foram passados por parametro
        x.setNome(nome);
        x.setCpf(cpf);
        x.setTelefone(telefone);
        x.setEndereco(endereco);
        x.setObs(obs);
        x.setEmail(email);
        x.setIdade(idade);
        x.setFuncionario(funcionario);
        x.setId(id);
        String sql = "UPDATE pessoas SET cpf =?, nome =?, email =?, idade =?, telefone =?, endereco =?, funcionario =?, observacoes =? WHERE id = ?";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql);    
            stmt.setString(1, x.getCpf());
            stmt.setString(2, x.getNome());    
            stmt.setString(3, x.getEmail());
            stmt.setInt(4, x.getIdade());
            stmt.setString(5, x.getTelefone());  
            stmt.setString(6, x.getEndereco());  
            stmt.setInt(7, x.getFuncionario());
            stmt.setString(8, x.getObs());
            stmt.setInt(9, x.getId());
            stmt.execute(); //executa comando   
            stmt.close();
            return "Atualização realizada com sucesso!";
        }catch(SQLException ex){
         System.err.println("Erro ao tentar recuperar os dados "+ex.getMessage());
        }catch(Exception ex){
         System.err.println("Erro geral ao dar Update pessoas");
        } 
        return null;
    }
    // Exclui Pessoa no banco
    public String deletePessoa(int id){
        //Chamamos a classe Pedidos e atribuimos o valor x
        Pessoas x = new Pessoas();
        // Vamos setando os valores do nosso cliente conforme os valores que foram passados por parametro
        x.setId(id);
        String sql = "DELETE FROM pessoas WHERE id = ?";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql); 
            stmt.setInt(1, x.getId());
            stmt.execute(); //executa comando   
            stmt.close();
            return "Exclusão realizada com sucesso!";
        }catch(SQLException ex){
         System.err.println("Erro ao tentar excluir os dados "+ex.getMessage());
        }catch(Exception ex){
         System.err.println("Erro geral ao excluir pessoa");
        } 
        return null;
    }
    // busca Funcionario Unico
    public Pessoas buscaFuncionarioUnico(int id){
        try{
            String SQL = "SELECT * FROM pessoas WHERE id = ?";
            PreparedStatement ps = db.getConnections().prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Pessoas cli = new Pessoas();
            cli.setId(rs.getInt("id"));
            cli.setIdade(rs.getInt("idade"));
            cli.setCpf(rs.getString("cpf"));
            cli.setNome(rs.getString("nome"));
            cli.setEmail(rs.getString("email"));
            cli.setTelefone(rs.getString("telefone"));
            cli.setEndereco(rs.getString("endereco"));
            cli.setObs(rs.getString("observacoes"));
            cli.setFuncionario(rs.getInt("funcionario"));
            ps.close();
            return (Pessoas) cli;
        }catch(SQLException ex){
         System.err.println("Erro ao tentar recuperar os dados funcionario"+ex.getMessage());
     }catch(Exception ex){
         System.err.println("Erro geral ao buscar cliente unico");
     }
        
        return null;
    }
    // busca todos os clientes
    public ArrayList<Clientes> listaclientesgeral(){
     try{
         String SQL = "SELECT * FROM pessoas WHERE funcionario = 0";
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
    // busca todos funcionarios
    public ArrayList<Funcionarios> listafuncionariosgeral(){
     try{
         String SQL = "SELECT * FROM pessoas WHERE funcionario = 1";
         PreparedStatement ps = db.getConnections().prepareStatement(SQL);
         ResultSet rs = ps.executeQuery();
         ArrayList<Funcionarios> lista = new ArrayList<Funcionarios>();
         while(rs.next()){
             Pessoas cli = new Funcionarios();
             cli.setId(rs.getInt("id"));
             cli.setCpf(rs.getString("cpf"));
             cli.setNome(rs.getString("nome"));
             cli.setEmail(rs.getString("email"));
             cli.setTelefone(rs.getString("telefone"));
             cli.setEndereco(rs.getString("endereco"));
             cli.setObs(rs.getString("observacoes"));
             lista.add((Funcionarios) cli);
         }
         ps.close();
        return lista;
     }catch(SQLException ex){
         System.err.println("Erro ao tentar recuperar os dados "+ex.getMessage());
     }catch(Exception ex){
         System.err.println("Erro geral ao buscar funcionarios");
     }
     return null;
    }
    
}
