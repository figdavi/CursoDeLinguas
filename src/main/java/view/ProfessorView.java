/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package view;

import model.Lingua;
import java.util.List;
import javax.swing.JOptionPane;
import java.util.logging.Logger;
import controller.ProfessorController;

/**
 *
 * @author davis
 */
public class ProfessorView extends javax.swing.JFrame {
    
    private final ProfessorController professorController = new ProfessorController();
    private static final Logger logger = Logger.getLogger(ProfessorView.class.getName());

    /**
     * Creates new form ProfessorView
     */
    public ProfessorView() {
        initComponents();
        
        javax.swing.DefaultListModel<Lingua> model = new javax.swing.DefaultListModel<>();
        for (Lingua l : Lingua.values()) model.addElement(l);
        listLinguas.setModel(model);
        
        listLinguas.setSelectedIndex(-1);
        atualizarTabela(); // <- carrega os dados ao iniciar a tela
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbNome = new javax.swing.JLabel();
        lbEndereco = new javax.swing.JLabel();
        lbTelefone = new javax.swing.JLabel();
        lbMatricula = new javax.swing.JLabel();
        lbValorHora = new javax.swing.JLabel();
        lbLinguas = new javax.swing.JLabel();
        txtNome = new javax.swing.JTextField();
        txtEndereco = new javax.swing.JTextField();
        txtTelefone = new javax.swing.JTextField();
        txtMatricula = new javax.swing.JTextField();
        txtValorHora = new javax.swing.JTextField();
        btnCadastrar = new javax.swing.JButton();
        btnAtualizar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();
        btnLimpar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabelaProfessores = new javax.swing.JTable();
        btnVoltar = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        listLinguas = new javax.swing.JList();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Gerenciar Professores");

        lbNome.setText("Nome:");

        lbEndereco.setText("Endereço:");

        lbTelefone.setText("Telefone:");

        lbMatricula.setText("Matrícula:");

        lbValorHora.setText("Valor hora (R$):");

        lbLinguas.setText("Línguas:");

        btnCadastrar.setText("Cadastrar");
        btnCadastrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCadastrarActionPerformed(evt);
            }
        });

        btnAtualizar.setText("Atualizar");
        btnAtualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAtualizarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        btnLimpar.setText("Limpar");
        btnLimpar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimparActionPerformed(evt);
            }
        });

        tabelaProfessores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tabelaProfessores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabelaProfessoresMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabelaProfessores);

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVoltarActionPerformed(evt);
            }
        });

        listLinguas.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        listLinguas.setVisibleRowCount(3);
        jScrollPane3.setViewportView(listLinguas);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnCadastrar)
                        .addGap(27, 27, 27)
                        .addComponent(btnAtualizar)
                        .addGap(18, 18, 18)
                        .addComponent(btnExcluir)
                        .addGap(18, 18, 18)
                        .addComponent(btnLimpar)
                        .addGap(82, 82, 82)
                        .addComponent(btnVoltar))
                    .addComponent(lbMatricula)
                    .addComponent(lbEndereco)
                    .addComponent(lbLinguas)
                    .addComponent(lbNome)
                    .addComponent(lbValorHora)
                    .addComponent(lbTelefone)
                    .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorHora, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 705, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(lbNome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbEndereco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtEndereco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbTelefone)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTelefone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbMatricula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtMatricula, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(lbValorHora)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtValorHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(lbLinguas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCadastrar)
                    .addComponent(btnExcluir)
                    .addComponent(btnLimpar)
                    .addComponent(btnAtualizar)
                    .addComponent(btnVoltar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(40, 40, 40))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCadastrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCadastrarActionPerformed
         try {
            int matricula = Integer.parseInt(txtMatricula.getText().trim());
            String nome = txtNome.getText().trim();
            String endereco = txtEndereco.getText().trim();
            String telefone = txtTelefone.getText().trim();
            double valorHora = Double.parseDouble(txtValorHora.getText().trim());
            
            List<Lingua> linguas;
            try {
                linguas = listLinguas.getSelectedValuesList();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Língua(s) inválida(s). Use: " + Lingua.allToString());
                return;
            }

            String resultado = professorController.inserirProfessor(
                matricula, nome, endereco, telefone, valorHora, linguas);
            JOptionPane.showMessageDialog(this, resultado);
            
            if (resultado.contains("sucesso")) {
                atualizarTabela();
                limparCampos();
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Matrícula ou Valor/Hora inválidos.");
        }
    }//GEN-LAST:event_btnCadastrarActionPerformed

    private void btnAtualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAtualizarActionPerformed
       try {
            int matricula = Integer.parseInt(txtMatricula.getText().trim());
            String nome = txtNome.getText().trim();
            String endereco = txtEndereco.getText().trim();
            String telefone = txtTelefone.getText().trim();
            double valorHora = Double.parseDouble(txtValorHora.getText().trim());

            List<model.Lingua> linguas;
            try {
                linguas = listLinguas.getSelectedValuesList();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(this, "Língua(s) inválida(s). Use: " + Lingua.allToString());
                return;
            }

            String resultado = professorController.atualizarProfessor(
                matricula, nome, endereco, telefone, valorHora, linguas);

            JOptionPane.showMessageDialog(this, resultado);
            if (resultado.contains("sucesso")) {
                atualizarTabela();
                limparCampos();
                txtMatricula.setEditable(true);
            }

        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Matrícula ou Valor/Hora inválidos.");
        }
    }//GEN-LAST:event_btnAtualizarActionPerformed

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcluirActionPerformed
        int linha = tabelaProfessores.getSelectedRow();
        if (linha != -1) {
            int matricula = Integer.parseInt(tabelaProfessores.getValueAt(linha, 0).toString());
            int confirm = JOptionPane.showConfirmDialog(this, "Deseja excluir o professor selecionado?",
                    "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                String resultado = professorController.excluirProfessor(matricula);
                JOptionPane.showMessageDialog(this, resultado);
                if (resultado.contains("sucesso")) {
                    atualizarTabela();
                    limparCampos();
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um professor para excluir.");
        }
    }//GEN-LAST:event_btnExcluirActionPerformed

    private void btnLimparActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimparActionPerformed
        limparCampos();
    }//GEN-LAST:event_btnLimparActionPerformed

    private void tabelaProfessoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabelaProfessoresMouseClicked
        int linha = tabelaProfessores.getSelectedRow();

        if (linha != -1) {
            txtMatricula.setText(tabelaProfessores.getValueAt(linha, 0).toString());
            txtNome.setText(tabelaProfessores.getValueAt(linha, 1).toString());
            txtEndereco.setText(tabelaProfessores.getValueAt(linha, 2).toString());
            txtTelefone.setText(tabelaProfessores.getValueAt(linha, 3).toString());
            txtValorHora.setText(tabelaProfessores.getValueAt(linha, 4).toString());

            String[] linguasSelecionadas = tabelaProfessores.getValueAt(linha, 5).toString().split("\\s*,\\s*");
            
            javax.swing.ListModel<Lingua> model = listLinguas.getModel();
            List<Integer> indices = new java.util.ArrayList<>();

            // Comparar cada elemento da jList com as linguas selecionadas
            for (String lingua : linguasSelecionadas) {
                for (int i = 0; i < model.getSize(); i++) {
                    if (model.getElementAt(i).toString().equalsIgnoreCase(lingua)) {
                        // Adiciona a posição do elemento i da jList como indice a ser selecionado
                        indices.add(i);
                        break;
                    }
                }
            }
            // Trasforma List<Integer> para Array[int]
            int[] array = new int[indices.size()];
            for (int i = 0; i < indices.size(); i++) array[i] = indices.get(i);
            listLinguas.setSelectedIndices(array);

            txtMatricula.setEditable(false);
        }
    }//GEN-LAST:event_tabelaProfessoresMouseClicked

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVoltarActionPerformed
        this.dispose(); // Fecha a janela atual
    }//GEN-LAST:event_btnVoltarActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new ProfessorView().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAtualizar;
    private javax.swing.JButton btnCadastrar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JButton btnLimpar;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lbEndereco;
    private javax.swing.JLabel lbLinguas;
    private javax.swing.JLabel lbMatricula;
    private javax.swing.JLabel lbNome;
    private javax.swing.JLabel lbTelefone;
    private javax.swing.JLabel lbValorHora;
    private javax.swing.JList listLinguas;
    private javax.swing.JTable tabelaProfessores;
    private javax.swing.JTextField txtEndereco;
    private javax.swing.JTextField txtMatricula;
    private javax.swing.JTextField txtNome;
    private javax.swing.JTextField txtTelefone;
    private javax.swing.JTextField txtValorHora;
    // End of variables declaration//GEN-END:variables

    private void atualizarTabela() {
        List<model.Professor> professores = professorController.listarTodosProfessores();
        String[] colunas = { "Matrícula", "Nome", "Endereço", "Telefone", "Valor/Hora", "Línguas" };
        Object[][] dados = new Object[professores.size()][6];

        for (int i = 0; i < professores.size(); i++) {
            model.Professor p = professores.get(i);
            dados[i][0] = p.getMatricula();
            dados[i][1] = p.getNome();
            dados[i][2] = p.getEndereco();
            dados[i][3] = p.getTelefone();
            dados[i][4] = p.getValorHora();
            // Exibe como "INGLES, ESPANHOL"
            dados[i][5] = String.join(", ", p.getLinguas().stream().map(Enum::toString).toList());
        }

        tabelaProfessores.setModel(new javax.swing.table.DefaultTableModel(dados, colunas) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
    }

    private void limparCampos() {
        txtMatricula.setText("");
        txtNome.setText("");
        txtEndereco.setText("");
        txtTelefone.setText("");
        txtValorHora.setText("");
        listLinguas.clearSelection();
        txtMatricula.setEditable(true);
    }
}
