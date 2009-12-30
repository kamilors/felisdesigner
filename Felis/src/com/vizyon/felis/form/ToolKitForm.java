/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * ToolKitForm.java
 *
 * Created on Dec 30, 2009, 8:29:13 PM
 */

package com.vizyon.felis.form;

import com.vizyon.felis.core.Screen;
import java.awt.Component;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

/**
 *
 * @author Kamil ÖRS
 */
public class ToolKitForm extends javax.swing.JFrame {

    JFrame parent;
    Screen screen;

    /** Creates new form ToolKitForm */
    public ToolKitForm() {
        initComponents();
    }

    public ToolKitForm(JFrame parent, Screen screen) {
        initComponents();
        this.parent = parent;
        this.screen = screen;
        LocationListener listener = new LocationListener();
        listener.start();
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        toolBar = new javax.swing.JToolBar();
        lock = new javax.swing.JToggleButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        normal = new javax.swing.JToggleButton();
        move = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        addNewTable = new javax.swing.JToggleButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        oneToOne = new javax.swing.JToggleButton();
        oneToMany = new javax.swing.JToggleButton();
        manyToMany = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        help = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("Araçlar");
        setAlwaysOnTop(true);
        setFocusable(false);
        setResizable(false);

        toolBar.setFloatable(false);
        toolBar.setRollover(true);

        lock.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/lock.png"))); // NOI18N
        lock.setSelected(true);
        lock.setToolTipText("Araç Barını Ana Pencereye Kitle");
        lock.setFocusable(false);
        lock.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lock.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(lock);
        toolBar.add(jSeparator4);

        normal.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/cursor.png"))); // NOI18N
        normal.setSelected(true);
        normal.setToolTipText("Normal");
        normal.setFocusable(false);
        normal.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        normal.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        normal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                normalMouseClicked(evt);
            }
        });
        toolBar.add(normal);

        move.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/hand.png"))); // NOI18N
        move.setToolTipText("Diyagramı Taşı");
        move.setFocusable(false);
        move.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        move.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        move.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveMouseClicked(evt);
            }
        });
        toolBar.add(move);
        toolBar.add(jSeparator1);

        addNewTable.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/add_new_table.png"))); // NOI18N
        addNewTable.setToolTipText("Yeni Tablo Ekle");
        addNewTable.setFocusable(false);
        addNewTable.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        addNewTable.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(addNewTable);
        toolBar.add(jSeparator2);

        oneToOne.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/add_one_to_one.png"))); // NOI18N
        oneToOne.setToolTipText("Bire Bir İlişki Ekle ( 1:1 )");
        oneToOne.setFocusable(false);
        oneToOne.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        oneToOne.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(oneToOne);

        oneToMany.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/add_one_to_many.png"))); // NOI18N
        oneToMany.setToolTipText("Bire Çok İlişki Ekle ( 1:n )");
        oneToMany.setFocusable(false);
        oneToMany.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        oneToMany.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(oneToMany);

        manyToMany.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/add_many_to_many.png"))); // NOI18N
        manyToMany.setToolTipText("Çoka Çok İlişki Ekle ( n:n )");
        manyToMany.setFocusable(false);
        manyToMany.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        manyToMany.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(manyToMany);
        toolBar.add(jSeparator3);

        help.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/vizyon/felis/icon/help.png"))); // NOI18N
        help.setToolTipText("Felis Hakkında");
        help.setFocusable(false);
        help.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        help.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        toolBar.add(help);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.DEFAULT_SIZE, 217, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(toolBar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void normalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_normalMouseClicked
        screen.setMouseNormal(true);
        setToggleSelection(normal);
    }//GEN-LAST:event_normalMouseClicked

    private void moveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveMouseClicked
        screen.setMouseHand(true);
        setToggleSelection(move);
    }//GEN-LAST:event_moveMouseClicked

    /**
    * @param args the command line arguments
    */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ToolKitForm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton addNewTable;
    private javax.swing.JButton help;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToggleButton lock;
    private javax.swing.JToggleButton manyToMany;
    private javax.swing.JToggleButton move;
    private javax.swing.JToggleButton normal;
    private javax.swing.JToggleButton oneToMany;
    private javax.swing.JToggleButton oneToOne;
    private javax.swing.JToolBar toolBar;
    // End of variables declaration//GEN-END:variables


    private class LocationListener extends Thread {
        
        public void run() {
            int width, height;
            while (true) {
                if (lock.isSelected()) {
                    try {
                        sleep(200);
                        width = parent.getLocation().x + parent.getWidth() - (getWidth() + 50);
                        height = parent.getLocation().y + getHeight();
                        setLocation(width, height);
                    } catch (Exception e) {
                        System.out.println("HATA: " + e);
                    }
                }
            }
        }
    }

    private void setToggleSelection(JToggleButton button) {

        for(Component component : toolBar.getComponents()) {
            if(component.getClass().equals(JToggleButton.class)) {
                JToggleButton toggle = (JToggleButton) component;
                if(toggle.equals(button)) {
                    toggle.setSelected(true);
                }
                else {
                    if(!toggle.equals(lock)) {
                        toggle.setSelected(false);
                    }
                }
            }
        }
    }

}
