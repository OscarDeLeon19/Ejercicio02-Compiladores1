package main;

import analizador_lexico.Analizador;
import java.io.IOException;
import java.io.StringReader;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;
import javax.swing.text.BadLocationException;

public class Ventana extends javax.swing.JFrame {

    /**
     * Constructor
     */
    public Ventana() {
        initComponents();
        setLocationRelativeTo(null);
        
        // Accion para tener el conteo de filas y columnas mientras se utiliza la aplicacion        
        areaTexto.addCaretListener(new CaretListener() {
            @Override
            public void caretUpdate(CaretEvent e) {
                int pos = e.getDot();
                try {
                    int fila = areaTexto.getLineOfOffset(pos) + 1;
                    int col = pos - areaTexto.getLineStartOffset(fila - 1) + 1;
                    etiqueta1.setText("Fila: " + fila);
                    etiqueta2.setText("Columna: " + col);
                } catch (BadLocationException exc) {
                    System.out.println(exc);
                }
            }
        });
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        areaTexto = new javax.swing.JTextArea();
        botonAnalizar = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        areaResultados = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        etiqueta1 = new javax.swing.JLabel();
        etiqueta2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Analizador");

        areaTexto.setColumns(20);
        areaTexto.setRows(5);
        jScrollPane1.setViewportView(areaTexto);

        botonAnalizar.setText("Analizar");
        botonAnalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAnalizarActionPerformed(evt);
            }
        });

        areaResultados.setColumns(20);
        areaResultados.setRows(5);
        areaResultados.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        areaResultados.setEnabled(false);
        jScrollPane2.setViewportView(areaResultados);

        jLabel1.setText("Area de Texto");

        etiqueta1.setText("Fila: ");

        etiqueta2.setText("Columna: ");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(etiqueta1)
                                    .addGap(251, 251, 251)
                                    .addComponent(botonAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 432, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(etiqueta2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(195, 195, 195)
                        .addComponent(jLabel1)))
                .addContainerGap(26, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(etiqueta1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(etiqueta2))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonAnalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Accion que se realiza al presionar el boton analizar.
     * Obtiene los resultados del analizador lexico para asi realizar el conteo de vocales numeros y valores no idetificados.
     * Despues de realizar el conteo, agrega los resultados a un area de texto.
     * @param evt 
     */
    private void botonAnalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAnalizarActionPerformed
        int[] palabras = new int[6];
        StringReader str = new StringReader(areaTexto.getText() + " ");
        Analizador analizador = new Analizador(str);
        areaResultados.setText(null);
        String texto = "";
        String textoError = "";
        try {
            boolean bandera = true;
            while (bandera) {
                Object objeto = analizador.yylex();
                if (objeto == null) {
                    bandera = false;
                }
                if (bandera == true) {
                    if (analizador.getTipo() == 1) {
                        contarPalabra(analizador.getCantidadVocales(), palabras);
                    } else if (analizador.getTipo() == 2) {
                        texto = texto + "Numero: " + analizador.yytext() + " | Fila: " + (analizador.getFila() + 1) + " | Columna: " + (analizador.getColumna() + 1) + "\n";
                        contarPalabra(analizador.getCantidadVocales(), palabras);
                    } else if (analizador.getTipo() == 3) {
                        textoError = textoError + "Valor no identificado: " + analizador.yytext() + "\n";
                        contarPalabra(analizador.getCantidadVocales(), palabras);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e);
        }

        areaResultados.append("Palabras con una vocal: " + palabras[0] + "\n");
        areaResultados.append("Palabras con dos vocales: " + palabras[1] + "\n");
        areaResultados.append("Palabras con tres vocales: " + palabras[2] + "\n");
        areaResultados.append("Palabras con cuatro vocales: " + palabras[3] + "\n");
        areaResultados.append("Palabras con cinco vocales: " + palabras[4] + "\n");
        areaResultados.append("Palabras con mas de cinco vocales: " + palabras[5] + "\n");
        areaResultados.append("\n");
        areaResultados.append("Enteros: \n");
        areaResultados.append(texto);
        areaResultados.append("\n");
        areaResultados.append("Errores: \n");
        areaResultados.append(textoError);


    }//GEN-LAST:event_botonAnalizarActionPerformed
    /**
     * Obtiene la cantidad de vocales de una palabra y la clasifica segun esta cantidad
     * @param cantidadVocales La cantidad de vocales
     * @param palabras El arreglo de clasificacion de palabras
     */
    public void contarPalabra(int cantidadVocales, int[] palabras) {
        if (cantidadVocales > 0 && cantidadVocales <= 5) {
            palabras[cantidadVocales - 1]++;
        } else if (cantidadVocales > 5) {
            palabras[5]++;
        }
    }

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
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ventana().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextArea areaResultados;
    private javax.swing.JTextArea areaTexto;
    private javax.swing.JButton botonAnalizar;
    private javax.swing.JLabel etiqueta1;
    private javax.swing.JLabel etiqueta2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    // End of variables declaration//GEN-END:variables
}
