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
import models.Pedidos;

/**
 *
 * @author gabrielrm
 */
public class PedidosDB {
        private Conector db;
    
    public PedidosDB(Conector db){
        this.db = db;
    }
    //Busca todos os pedidos na base de dados 
    public ArrayList<Pedidos> buscapedidos(){
     try{
         String SQL = "SELECT pedidos.id, pedidos.codCliente, pedidos.codProduto, pedidos.quantidade, pedidos.status, pedidos.observacoes, pedidos.valor, pedidos.observacoes, cl.nome FROM pedidos INNER JOIN clientes as cl ON cl.id = pedidos.codCliente";
         PreparedStatement ps = db.getConnections().prepareStatement(SQL);
         ResultSet rs = ps.executeQuery();
         ArrayList<Pedidos> lista = new ArrayList<Pedidos>();
         while(rs.next()){
             Pedidos ped = new Pedidos();
             ped.setId(rs.getInt("id"));
             ped.setCodCliente(rs.getInt("codCliente"));
             ped.setCodProduto(rs.getInt("codProduto"));
             ped.setNomeCliente(rs.getString("nome"));
             ped.setQuantidade(rs.getInt("quantidade"));
             ped.setValor(rs.getDouble("valor"));
             ped.setStatus(rs.getString("status"));
             ped.setObservacoes(rs.getString("observacoes"));
             lista.add(ped);
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
    
    //Cadastra pedido
    public String cadastraPedidos(int codCliente, int codProduto, int quantidade, String obs){
        //Chamamos a classe Pedidos e atribuimos o valor p
        Pedidos p = new Pedidos();
        // Var valor para armazenar o custo do pedido conforme a quantidade
        int valor;
        // Switch pelo codProduto, conforme o cod um valor diferente
        switch(codProduto){
            // Valores 28, 23 e 25 são os preços dos combos que estão na jframe cardapio
            case 1:
                valor = 28 * quantidade;
                break;
            case 2:
                valor = 23 * quantidade;
                break;
            case 3:
                valor = 25 * quantidade;
                break;
            // Caso o codProduto não seja um valor que tenha no cardapio, retorna mensagem de erro
            default:
                return("Código do produto e/ou quantidade foi informado incorretamente!");
                
        }
        // Vamos setando os valores do nosso pedido conforme os valores que foram passados por parametro
        p.setCodCliente(codCliente);
        p.setCodProduto(codProduto);
        p.setQuantidade(quantidade);
        p.setObservacoes(obs);
        p.setValor(valor);
        p.setStatus("pendente");
        // Aqui adicionamos o pedido ao banco
        String sql = "INSERT INTO pedidos(codCliente, codProduto, quantidade, valor, status, observacoes) VALUES(?,?,?,?,?,?)";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql);    
            stmt.setInt(1, p.getCodCliente());
            stmt.setInt(2, p.getCodProduto());    
            stmt.setInt(3, p.getQuantidade());
            stmt.setDouble(4, p.getValor());
            stmt.setString(5, p.getStatus());  
            stmt.setString(6, p.getObservacoes());  
            stmt.execute(); //executa comando   
            stmt.close();
            return "Cadastro de pedido realizado com sucesso!";
        } catch (SQLException u) {    
            throw new RuntimeException(u);    
        }
    }
    
    public Pedidos buscaPedidoUnico(int id){
        try{
            String SQL = "SELECT pedidos.id, pedidos.codCliente, pedidos.codProduto, pedidos.quantidade, pedidos.status, pedidos.observacoes, pedidos.valor, pedidos.observacoes, cl.nome FROM pedidos INNER JOIN clientes as cl ON cl.id = pedidos.codCliente WHERE pedidos.id = ?";
            PreparedStatement ps = db.getConnections().prepareStatement(SQL);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            rs.next();
            Pedidos ped = new Pedidos();
            ped.setId(rs.getInt("id"));
            ped.setCodCliente(rs.getInt("codCliente"));
            ped.setCodProduto(rs.getInt("codProduto"));
            ped.setNomeCliente(rs.getString("nome"));
            ped.setQuantidade(rs.getInt("quantidade"));
            ped.setValor(rs.getDouble("valor"));
            ped.setStatus(rs.getString("status"));
            ped.setObservacoes(rs.getString("observacoes"));
            ps.close();
            return ped;
        }catch(SQLException ex){
         System.err.println("Erro ao tentar recuperar os dados "+ex.getMessage());
     }catch(Exception ex){
         System.err.println("Erro geral ao buscar cliente unico");
     }
        
        return null;
    }
    
    
     public String updatePedido(int id,  int codigoProduto,  int quantidadeCombo, String status, String observacoes){
        
        Pedidos p = new Pedidos();
        // Var valor para armazenar o custo do pedido conforme a quantidade
        int valor;
        // Switch pelo codProduto, conforme o cod um valor diferente
        switch(codigoProduto){
            // Valores 28, 23 e 25 são os preços dos combos que estão na jframe cardapio
            case 1:
                valor = 28 * quantidadeCombo;
                break;
            case 2:
                valor = 23 * quantidadeCombo;
                break;
            case 3:
                valor = 25 * quantidadeCombo;
                break;
            // Caso o codProduto não seja um valor que tenha no cardapio, retorna mensagem de erro
            default:
                return("Código do produto e/ou quantidade foi informado incorretamente!");
                
        }
        // Vamos setando os valores do nosso pedido conforme os valores que foram passados por parametro
        p.setCodProduto(codigoProduto);
        p.setQuantidade(quantidadeCombo);
        p.setObservacoes(observacoes);
        p.setValor(valor);
        p.setStatus(status);
        p.setId(id);
        String sql = "UPDATE Pedidos SET codProduto =?, quantidade =?, status =?, valor =?, observacoes =? WHERE id = ?";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql);    
            stmt.setInt(1, p.getCodProduto());
            stmt.setInt(2, p.getQuantidade());    
            stmt.setString(3, p.getStatus());
            stmt.setDouble(4, p.getValor());
            stmt.setString(5, p.getObservacoes());
            stmt.setInt(6, p.getId());
            stmt.execute(); //executa comando   
            stmt.close();
            return "Atualização realizada com sucesso!";
        }catch(SQLException ex){
         System.err.println("Erro ao tentar atualizar os dados "+ex.getMessage());
        }catch(Exception ex){
         System.err.println("Erro geral ao atualizar unico");
        } 
        return null;
    }
    
     public String deletePedido(int id){
        //Chamamos a classe Pedidos e atribuimos o valor x
        Pedidos x = new Pedidos();
        // Vamos setando os valores do nosso cliente conforme os valores que foram passados por parametro
        x.setId(id);
        String sql = "DELETE FROM pedidos WHERE id = ?";    
        try {    
            PreparedStatement stmt = db.getConnections().prepareStatement(sql); 
            stmt.setInt(1, x.getId());
            stmt.execute(); //executa comando   
            stmt.close();
            return "Exclusão realizada com sucesso!";
        }catch(SQLException ex){
         System.err.println("Erro ao tentar excluir os dados "+ex.getMessage());
        }catch(Exception ex){
         System.err.println("Erro geral ao excluir pedido");
        } 
        return null;
    }
    
}
