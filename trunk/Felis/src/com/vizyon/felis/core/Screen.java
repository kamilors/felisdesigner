/*
 * Çizimlerin Yapıldığı sınıftır. JPanel'den türetilmiştir.
 * Duruma göre Applet ya da JFrame içinde örneklenir.
 *
 */

package com.vizyon.felis.core;


import com.vizyon.felis.form.AddNewTableDialog;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;

/**
 *
 * @author Kamil ÖRS
 */
public class Screen extends JPanel {

    private List<Table> tables; // Tablolar
    private Table selectedTable;

    public Screen() {
        initScreen();
        tables.add(new Table("User"));
    }

    // <editor-fold defaultstate="collapsed" desc="Initialize Screen">
    public void initScreen() {
        // Çizimlerde Göz Kırpma olayını ortadan kaldırır
        setDoubleBuffered(true);
        setBackground(Color.WHITE);
        tables = new ArrayList<Table>(0);
        selectedTable = null;
        setComponentPopupMenu(new Menu());


        // <editor-fold defaultstate="collapsed" desc="Mouse Listener">
        addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                selectTable(e.getX(), e.getY());
                if(e.getClickCount() == 2) {
                    // Tablo uzunluğu kısalt ya da arttır
                }
                repaint();
            }

            public void mousePressed(MouseEvent e) {
                if(selectTable(e.getX(), e.getY())) {
                    selectedTable.dragDrop.x = e.getX() - selectedTable.getBox().getLeft();
                    selectedTable.dragDrop.y = e.getY() - selectedTable.getBox().getTop();
                }
            }

            public void mouseReleased(MouseEvent e) {

            }

            public void mouseEntered(MouseEvent e) {

            }

            public void mouseExited(MouseEvent e) {
               
            }
        });
        //</editor-fold>

        // <editor-fold defaultstate="collapsed" desc="MouseMotion Listener">
        addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent e) {
                if(selectedTable != null) {
                    selectedTable.getBox().setLeft(e.getX() - selectedTable.dragDrop.x);
                    selectedTable.getBox().setTop(e.getY() - selectedTable.dragDrop.y);
                    repaint();
                }
            }

            public void mouseMoved(MouseEvent e) {

            }
        });
        //</editor-fold>
    }
    //</editor-fold>

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        // Izgarayı Çiz
        drawGrid(g);

        //Tabloları Çiz
        for(Table table : tables) {
            table.draw(g);
        }
    }

    // Grid Çiz
    private void drawGrid(Graphics g) {
        g.setColor(new Color(235, 235, 235));
        for(int i = 0; i<getWidth(); i=i+12) {
            g.drawLine(i, 0, i, getWidth());
        }

        for(int i = 0; i<getHeight(); i=i+12) {
            g.drawLine(0, i, getWidth(), i);
        }
    }

    private Table hasTable(int x, int y) {
        for(Table table : tables) {
            if(table.getBox().getShape().contains(x, y)) {
                return table;
            }
        }
        return null;
    }

    private boolean selectTable(int x, int y) {
        deSelectAllTable();
        selectedTable = hasTable(x, y);
        if(selectedTable != null) {
            selectedTable.setSelected(true);
            selectedTable.dragDrop.x = x - selectedTable.getBox().getLeft();
            selectedTable.dragDrop.y = y - selectedTable.getBox().getTop();
            return true;
        }
        return false;
    }

    private void deSelectAllTable() {
        for(Table table : tables) {
            table.setSelected(false);
        }
    }

    public void reload() {
        repaint();
    }

    // <editor-fold defaultstate="collapsed" desc="Popup Menu">
    private class Menu extends JPopupMenu {

        public Menu() {
            initMenu();

        }

        public void initMenu() {
            JMenuItem newTable = new JMenuItem("Yeni Tablo Ekle");
            newTable.addActionListener(new ActionListener() {

                public void actionPerformed(ActionEvent e) {
                    AddNewTableDialog tableDialog = new AddNewTableDialog(null, true);
                    Table table = tableDialog.createNewTable();
                    tables.add(table);
                    System.out.println("Menuye Geri Dondu");
                    reload();
                }
            });

            add(newTable);
        }
    }
    //</editor-fold>
}
