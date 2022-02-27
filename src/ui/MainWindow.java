/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import app.PDFManager;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Dimension;
import java.io.File;
import java.io.IOException;
import java.lang.System.Logger;
import java.lang.System.Logger.Level;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ImageIcon;

/**
 *
 * @author Alex
 */
public class MainWindow extends javax.swing.JFrame {

    File selectedFilePath;

    /**
     * Creates new form MainWindow
     */
    public MainWindow() {
        initComponents();

        initComboBox();

        selectedFilePath = null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jFileChooser1 = new javax.swing.JFileChooser();
        jFileChooser2 = new javax.swing.JFileChooser();
        jScrollPane1 = new javax.swing.JScrollPane();
        logTextArea = new javax.swing.JTextArea();
        btnOpenFile = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        btnOpenFile1 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(null);
        setName("MainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(800, 800));

        logTextArea.setEditable(false);
        logTextArea.setColumns(1);
        logTextArea.setRows(5);
        jScrollPane1.setViewportView(logTextArea);

        btnOpenFile.setText("Seleccionar PDF");
        btnOpenFile.setAlignmentY(0.0F);
        btnOpenFile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnOpenFileMouseClicked(evt);
            }
        });

        jComboBox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jComboBox1ItemStateChanged(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jLabel1.setText("El mes de la nómina:");
        jLabel1.setAlignmentY(0.0F);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        btnOpenFile1.setText("Procesar");
        btnOpenFile1.setAlignmentY(0.0F);
        btnOpenFile1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnProcessDocumentMouseClicked(evt);
            }
        });

        jButton1.setText("Abrir Carpeta");
        jButton1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButtonAbrirCarpetaMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 286, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnOpenFile, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnOpenFile1, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE)
                            .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 354, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(btnOpenFile)
                .addGap(14, 14, 14)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addComponent(btnOpenFile1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addGap(34, 34, 34))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOpenFileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnOpenFileMouseClicked
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setPreferredSize(new Dimension(600, 500));
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.home") + "/Downloads"));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

        FileNameExtensionFilter pdfFilter = new FileNameExtensionFilter("PDF", "pdf");
        fileChooser.setFileFilter(pdfFilter);

        int result = fileChooser.showOpenDialog(this);

        // Guarda la Ruta del fichero seleccioando
        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFilePath = fileChooser.getSelectedFile();
        }
    }//GEN-LAST:event_btnOpenFileMouseClicked

    private void jComboBox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jComboBox1ItemStateChanged
        SimpleDateFormat dateM = new SimpleDateFormat("MM");
        int month = Integer.valueOf(dateM.format(new Date()));

        if (jComboBox1.getSelectedIndex() != (month - 1)) {
            jComboBox1.setBackground(Color.red);
        } else {
            jComboBox1.setBackground(Color.gray);
        }
    }//GEN-LAST:event_jComboBox1ItemStateChanged

    private void btnProcessDocumentMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnProcessDocumentMouseClicked
        if (selectedFilePath != null) {
            if (selectedFilePath.getName().contains(".pdf")) {
                // Separa las paginas del documento PDF en nuevos PDFs
                int num = splitPdfDocument(selectedFilePath);

                if (num > 0) {
                    logTextArea.append("\nPDF procesado con éxito. \nArchivos generados: " + num + "\n");
                } else {
                    logTextArea.append("\nError al procesar el PDF seleccionado.\n");
                }
            } else {
                logTextArea.append("\n¡ERROR! Solo se admiten archivos PDF." + "\n");
            }
        } else {
            logTextArea.append("\n¡ERROR! No hay ningún archivo seleccionado." + "\n");
        }

        selectedFilePath = null;
    }//GEN-LAST:event_btnProcessDocumentMouseClicked

    private void jButtonAbrirCarpetaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButtonAbrirCarpetaMouseClicked
        String userName = System.getProperty("user.name");
        String nameFolder = "C:\\Users\\" + userName + "\\Documents\\Nominas\\";
        File directorio = new File(nameFolder);

        // Si no existe el directorio, se crea
        if (!directorio.exists()) {
            directorio.mkdirs();
        }

        try {
            Desktop.getDesktop().open(directorio);
        } catch (IOException ex) {
            logTextArea.append("\n¡ERROR! "+ ex.getMessage() + "\n");
        }

    }//GEN-LAST:event_jButtonAbrirCarpetaMouseClicked

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
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWindow.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new MainWindow();
                frame.setLocationRelativeTo(null);
                frame.setTitle("Separador de nóminas");
                frame.setIconImage(new ImageIcon("src/img/invoice.png").getImage());
                frame.setVisible(true);
            }
        });
    }

    // Inicializa el ComboBox con los meses del año actual
    // El item seleccionado por defecto es el mes actual
    private void initComboBox() {
        SimpleDateFormat dateY = new SimpleDateFormat("YY");
        SimpleDateFormat dateM = new SimpleDateFormat("MM");
        String year = dateY.format(new Date());
        int month = Integer.valueOf(dateM.format(new Date()));

        jComboBox1.addItem("ene" + year);
        jComboBox1.addItem("feb" + year);
        jComboBox1.addItem("mar" + year);
        jComboBox1.addItem("abr" + year);
        jComboBox1.addItem("may" + year);
        jComboBox1.addItem("jun" + year);
        jComboBox1.addItem("jul" + year);
        jComboBox1.addItem("ago" + year);
        jComboBox1.addItem("sep" + year);
        jComboBox1.addItem("oct" + year);
        jComboBox1.addItem("nov" + year);
        jComboBox1.addItem("dic" + year);

        jComboBox1.setSelectedIndex(month - 1);
    }

    private int splitPdfDocument(File originalFilePath) {
        int numPages = 0;
        PDFManager pdfManager = new PDFManager();

        // Se genera el Nombre de Carpeta a partir del mes y año actual
        String userName = System.getProperty("user.name");
        SimpleDateFormat year = new SimpleDateFormat("yyyy");
        int index = jComboBox1.getSelectedIndex() + 1;
        String monthNum = "";

        if (index < 10) {
            monthNum = "0" + index + " ";
        } else {
            monthNum = "" + index + " ";
        }
        // Genera una cadena que empiza por el numero del mes seguido por el nombre del mes
        String month = monthNum + jComboBox1.getSelectedItem().toString().subSequence(0, 3).toString();

        String nameFolder = "C:\\Users\\" + userName + "\\Documents\\Nominas\\" + year.format(new Date()) + "\\" + month;
        File outputFilePath = new File(nameFolder);

        // Genera la cadena con el mes y año
        // Se usa al renombrar los arvhivos PDF separados
        String monthSelected = " " + jComboBox1.getSelectedItem().toString();

        try {
            // Divide el PDF
            numPages = pdfManager.splitPdfDocument(originalFilePath, outputFilePath, monthSelected);
        } catch (Exception ex) {
            logTextArea.append("\n¡ERROR! " + ex.getMessage() + "\n");
        }

        return numPages;
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOpenFile;
    private javax.swing.JButton btnOpenFile1;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JFileChooser jFileChooser1;
    private javax.swing.JFileChooser jFileChooser2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea logTextArea;
    // End of variables declaration//GEN-END:variables
}
