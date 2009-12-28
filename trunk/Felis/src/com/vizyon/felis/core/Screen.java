/*
 * Çizimlerin Yapıldığı sınıftır. JPanel'den türetilmiştir.
 * Duruma göre Applet ya da JFrame içinde örneklenir.
 *
 */

package com.vizyon.felis.core;


import com.vizyon.felis.form.AddNewTableDialog;
import com.vizyon.felis.form.TableEditDialog;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
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
    }

    // <editor-fold defaultstate="collapsed" desc="Initialize Screen">
    public void initScreen() {
        // Çizimlerde Göz Kırpma olayını ortadan kaldırır
        setDoubleBuffered(true);
        setBackground(Color.WHITE);
        tables = new ArrayList<Table>(0);
        selectedTable = null;
        setComponentPopupMenu(new Menu());
        setFocusable(true);
        setFocusTraversalKeysEnabled(true);


        // <editor-fold defaultstate="collapsed" desc="Mouse Listener">
        addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {
                selectTable(e.getX(), e.getY());
                if(e.getClickCount() == 2) {
                    if(selectedTable!=null) {
                        if(selectedTable.isClosed()) {
                            selectedTable.setClosed(false);
                        }
                        else {
                            selectedTable.setClosed(true);
                        }
                    }
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
                    if((e.getX() > 10 && e.getX() < getWidth() - 10) &&
                            (e.getY() > 10 && e.getY() < getHeight() - 10)) {
                        selectedTable.getBox().setLeft(e.getX() - selectedTable.dragDrop.x);
                        selectedTable.getBox().setTop(e.getY() - selectedTable.dragDrop.y);
                        repaint();
                    }
                }
            }

            public void mouseMoved(MouseEvent e) {

            }
        });
        //</editor-fold>

        // <editor-fold defaultstate="collapsed" desc="Key Listener">
        addKeyListener(new KeyListener() {
            public void keyTyped(KeyEvent ke) {
            }

            public void keyPressed(KeyEvent ke) {
            }

            public void keyReleased(KeyEvent ke) {
                if (selectedTable != null) {
                    if (ke.getKeyCode() == KeyEvent.VK_F2) {
                        TableEditDialog editDialog = new TableEditDialog(null, true, selectedTable);
                        editDialog.setVisible(true);
                        reloadScreen();
                    }
                    else if(ke.getKeyCode() == KeyEvent.VK_DELETE) {
                        tables.remove(selectedTable);
                        selectedTable = null;
                        reloadScreen();
                    }
                    else if(ke.getKeyCode() == KeyEvent.VK_ESCAPE) {
                        deSelectAllTable();
                        reloadScreen();
                    }
                    else if(ke.getKeyCode() == KeyEvent.VK_DOWN) {
                        selectedTable.getBox().setTop(selectedTable.getBox().getTop() + 15);
                        reloadScreen();
                    }
                    else if(ke.getKeyCode() == KeyEvent.VK_UP) {
                        selectedTable.getBox().setTop(selectedTable.getBox().getTop() - 15);
                        reloadScreen();
                    }
                    else if(ke.getKeyCode() == KeyEvent.VK_LEFT) {
                        selectedTable.getBox().setLeft(selectedTable.getBox().getLeft() - 15);
                        reloadScreen();
                    }
                    else if(ke.getKeyCode() == KeyEvent.VK_RIGHT) {
                        selectedTable.getBox().setLeft(selectedTable.getBox().getLeft() + 15);
                        reloadScreen();
                    }
                }
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
        selectedTable = null;
    }

    public void reloadScreen() {
        repaint();
    }

    public void saveToPng() {
        try {
            File file = new File("/home/kamil/Desktop/felis.png");
            BufferedImage bi = new BufferedImage(getWidth(),getHeight(),BufferedImage.TYPE_INT_RGB);
            Graphics2D g2 = bi.createGraphics();
            paint(g2);
            ImageIO.write(bi, "png", file);
        }
        catch(Exception e) {
            System.out.println("HATA: " + e.getMessage());
        }
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
                    if(table != null) {
                        tables.add(table);
                    }
                    reloadScreen();
                }
            });

            add(newTable);

            JMenu selected = new JMenu("Seçim");

            JMenuItem editTable = new JMenuItem("Tabloyu Düzenle");
            editTable.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selectedTable != null) {
                        TableEditDialog editDialog = new TableEditDialog(null, true, selectedTable);
                        editDialog.setVisible(true);
                        reloadScreen();
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Lütfen Düzenlemek İçin Bir Tablo Seçin!");
                    }
                }
            });

            selected.add(editTable);

            JMenuItem deleteTable = new JMenuItem("Tabloyu Sil");
            deleteTable.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    if(selectedTable != null) {
                        tables.remove(selectedTable);
                        selectedTable = null;
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Lütfen Silmek İçin Bir Tablo Seçin!");
                    }
                    reloadScreen();
                }
            });
            selected.add(deleteTable);

            add(selected);

            JMenuItem reload = new JMenuItem("Yenile");
            reload.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    saveToPng();
                    reloadScreen();
                }
            });

            add(reload);
        }
    }
    //</editor-fold>

}
