/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trabalholab1;

import Banco.Conector;
import Banco.PessoasDB;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import models.Clientes;
import models.Funcionarios;
import models.Pessoas;

/**
 *
 * @author Patrick
 */
public class listaPessoas extends javax.swing.JFrame {

    /**
     * Creates new form listaPessoas
     */
    public listaPessoas() {
        initComponents();
        setDefaultCloseOperation( DISPOSE_ON_CLOSE ); 
        populatabela();
    }
    public void populatabela(){
        Conector db = new Conector();
        PessoasDB pesso = new PessoasDB(db);
        ArrayList<Clientes> lista = pesso.listapessoasgeral();
        //Aqui começamos a montar um modelo padrão de tabela
        DefaultTableModel modelo = new DefaultTableModel();
        //Adicionamos as colunas da nossa tabela
        modelo.addColumn("Código");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");
        modelo.addColumn("E-mail");
        //Setamos quantas linhas vamos ter conforme o tamnho de nosso Array
        modelo.setRowCount(lista.size());
        // contador para coluna
        int cont = 0;
        if(lista != null){
            for(Clientes obj: lista){
                //Aqui começamos a setar os valores onde o parametro 1 (dado), parametro 2(linha), parametro 3(coluna)
                modelo.setValueAt(obj.getId(),cont,0);
                modelo.setValueAt(obj.getNome(),cont,1);
                modelo.setValueAt(obj.getCpf(),cont,2);
                modelo.setValueAt(obj.getTelefone(),cont,3);
                modelo.setValueAt(obj.getEmail() ,cont,4);
                // Acumulamos +1 no nosso contador para colocar os dados em nova linha
                cont++;
            }
            // Setamos o novo modelo de tabela na nossa tabela que chamamos de jTablePedidos
            jTablePessoas.setModel(modelo);
        }
//       
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButtonAtualizaPessoa = new javax.swing.JButton();
        jButtonExcluirPessoa = new javax.swing.JButton();
        jButtonCadastraNovaPessoa = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablePessoas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Lucida Grande", 0, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Lista Pessoas");

        jButtonAtualizaPessoa.setText("Atualizar cadastro");
        jButtonAtualizaPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAtualizaPessoaActionPerformed(evt);
            }
        });

        jButtonExcluirPessoa.setText("Excluir");
        jButtonExcluirPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonExcluirPessoaActionPerformed(evt);
            }
        });

        jButtonCadastraNovaPessoa.setText("Cadastrar nova pessoa");
        jButtonCadastraNovaPessoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonCadastraNovaPessoaActionPerformed(evt);
            }
        });

        jTablePessoas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jTablePessoas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 713, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jButtonCadastraNovaPessoa)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButtonAtualizaPessoa, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButtonExcluirPessoa)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButtonAtualizaPessoa)
                    .addComponent(jButtonExcluirPessoa)
                    .addComponent(jButtonCadastraNovaPessoa))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButtonAtualizaPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAtualizaPessoaActionPerformed
        // TODO add your handling code here:
        if (jTablePessoas.getSelectedRow() != -1) {
            Pessoas p = new Pessoas();
            p.setId((int) jTablePessoas.getValueAt(jTablePessoas.getSelectedRow(), 0));
            int idCli = (int) jTablePessoas.getValueAt(jTablePessoas.getSelectedRow(), 0);
            //Chamamos nosso jframe e atribuimos a form
            editaPessoas form = new editaPessoas(idCli);
            //Aqui setamos para abrir no centro quando aberto
            form.setLocationRelativeTo(null);
            //Deixamos o jframe visivel
            form.setVisible(true);
            dispose();

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma pessoa para atualizar.");
        }
    }//GEN-LAST:event_jButtonAtualizaPessoaActionPerformed

    private void jButtonExcluirPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonExcluirPessoaActionPerformed
        if (jTablePessoas.getSelectedRow() != -1) {
            Conector db = new Conector();
            PessoasDB pessoa = new PessoasDB(db);
            Pessoas p = new Pessoas();
            p.setId((int) jTablePessoas.getValueAt(jTablePessoas.getSelectedRow(), 0));
            int idPes = (int) jTablePessoas.getValueAt(jTablePessoas.getSelectedRow(), 0);
            String nome = (String) jTablePessoas.getValueAt(jTablePessoas.getSelectedRow(), 1);
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (null, "Deseja excluir a pessoa "+nome+"?","Warning",dialogButton);
            if(dialogResult == JOptionPane.YES_OPTION){
                String retorno = pessoa.deletePessoa(idPes);
                JOptionPane.showMessageDialog(null, retorno);
                populatabela();
            }

        } else {
            JOptionPane.showMessageDialog(null, "Selecione uma pessoa para excluir.");
        }
    }//GEN-LAST:event_jButtonExcluirPessoaActionPerformed

    private void jButtonCadastraNovaPessoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonCadastraNovaPessoaActionPerformed
        // TODO add your handling code here:
        //Chamamos nosso jframe e atribuimos a form
        cadastraPessoa form = new cadastraPessoa();
        //Aqui setamos para abrir no centro quando aberto
        form.setLocationRelativeTo(null);
        //Deixamos o jframe visivel
        form.setVisible(true);
        dispose();
    }//GEN-LAST:event_jButtonCadastraNovaPessoaActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(listaPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(listaPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(listaPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(listaPessoas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new listaPessoas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonAtualizaPessoa;
    private javax.swing.JButton jButtonCadastraNovaPessoa;
    private javax.swing.JButton jButtonExcluirPessoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTablePessoas;
    // End of variables declaration//GEN-END:variables
}
