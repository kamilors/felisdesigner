/*
 * Çizimlerin Yapıldığı sınıftır. JPanel'den türetilmiştir.
 * Duruma göre Applet ya da JFrame içinde örneklenir.
 *
 */

package com.vizyon.felis.core;


import com.vizyon.felis.form.AddNewTableDialog;
import com.vizyon.felis.form.TableEditDialog;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.imageio.ImageIO;
import javax.print.PrintException;
import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.RepaintManager;

/**
 *
 * @author Kamil ÖRS
 */
public class Screen extends JPanel implements Printable {

    List<Table> tables; // Tablolar
    Table selectedTable;
    Point moveTo;

    //İliskiler
    Table fromTable;
    Table toTable;

    // Mouse
    boolean mouseNormal;
    boolean mouseHand;
    boolean mouseOneToOne;
    boolean mouseOneToMany;

   

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

        moveTo = new Point();

        fromTable = null;
        toTable = null;

        mouseNormal = true;
        mouseHand = false;
        mouseOneToMany = false;
        mouseOneToOne = false;


        // <editor-fold defaultstate="collapsed" desc="Mouse Listener">
        addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent e) {

                if(mouseNormal) {
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
                }
                else if(mouseOneToOne) {
                    if(fromTable == null) {
                        Table table = hasTable(e.getX(), e.getY());
                        if(table != null) {
                            fromTable = table;
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Bir Tablo Seçin!");
                        }
                    }
                    else if(toTable == null) {
                        Table table = hasTable(e.getX(), e.getY());
                        if(table != null) {
                            toTable = table;
                            
                            addOneToOneRelation();

                            fromTable = null;
                            toTable = null;
                            setMouseNormal(true);
                        }
                        else {
                            JOptionPane.showMessageDialog(null, "Bir Tablo Seçin!");
                        }
                    }
                }

                repaint();
            }

            public void mousePressed(MouseEvent e) {
                if(mouseHand) {
                    moveTo.x = e.getX();
                    moveTo.y = e.getY();
                    return;
                }

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

                if(mouseHand) {
                    if(getParent() != null) {
                        JScrollPane pane = (JScrollPane) getParent().getParent();
                        int x = -1 * (e.getX() - moveTo.x);
                        int y = -1 * (e.getY() - moveTo.y);

                        int sx = pane.getHorizontalScrollBar().getValue() + x;
                        int sy = pane.getVerticalScrollBar().getValue() + y;

                        pane.getHorizontalScrollBar().setValue(sx);
                        pane.getVerticalScrollBar().setValue(sy);
                        
                        repaint();
                    }
                    return;
                }

                if(selectedTable != null) {
                    int x = e.getX() - selectedTable.dragDrop.x;
                    int y = e.getY() - selectedTable.dragDrop.y;
                    
                    if(x < 0) x = 0;
                    if(y < 0) y = 0;
                    
                    selectedTable.getBox().setLeft(x);
                    selectedTable.getBox().setTop(y);
                    repaint();
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
                if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    setMouseHand(true);
                }
            }

            public void keyReleased(KeyEvent ke) {

                if(ke.getKeyCode() == KeyEvent.VK_SPACE) {
                    setMouseNormal(true);
                    return;
                }

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

        for(Table table : tables) {
            for(Relationship ship : table.getRelationships()) {
                ship.draw(g);
            }
        }

        //Tabloları Çiz
        for(Table table : tables) {
            table.draw(g);
        }
    }

    @Override
    public void repaint() {
        super.repaint();
        setPreferredSize(getMaxWidthAndHeight());

        if(getParent() != null) {
            JScrollPane pane = (JScrollPane)getParent().getParent();
            pane.updateUI();
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

    // <editor-fold defaultstate="collapsed" desc="Export To File">
    public void saveToPng() {
        try {
            JFileChooser fc = new JFileChooser();
            fc.setSelectedFile(new File("felisScreenShot.png"));
            int status = fc.showSaveDialog(this);
            
            if(status == JFileChooser.APPROVE_OPTION) {
                File file = fc.getSelectedFile();
                BufferedImage bi = new BufferedImage(getPreferredSize().width,getPreferredSize().height,BufferedImage.TYPE_INT_RGB);
                Graphics2D g2 = bi.createGraphics();
                paint(g2);
                ImageIO.write(bi, "png", file);
            }
            
        }
        catch(Exception e) {
            System.out.println("HATA: " + e.getMessage());
        }
    }
    //</editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Popup Menu">
    private class Menu extends JPopupMenu {

        public Menu() {
            initMenu();

        }



        public void initMenu() {

            JMenu actioner = new JMenu("İşlem");

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

            actioner.add(newTable);

            JMenuItem normal = new JMenuItem("Normal");
            normal.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setMouseNormal(true);
                }
            });
            actioner.add(normal);

            JMenuItem oneToOne = new JMenuItem("OneToOne ilişki ekle");
            oneToOne.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setMouseOneToOne(true);
                }
            });
            actioner.add(oneToOne);

            JMenuItem oneToMany = new JMenuItem("OneToMany ilişki Ekle");

            JMenuItem hand = new JMenuItem("Modeli Taşı");
            hand.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    setMouseHand(true);
                }
            });
            actioner.add(hand);



            add(actioner);

            

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

            JMenu export = new JMenu("Dışarı Aktar");
            JMenuItem exportPng = new JMenuItem("PNG Formatı");
            exportPng.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    saveToPng();
                }
            });
            export.add(exportPng);
            add(export);

            JMenuItem printer = new JMenuItem("Yazdır");
            printer.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    printOfPrinter();
                }
            });

            add(printer);

            JMenuItem reload = new JMenuItem("Yenile");
            reload.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent ae) {
                    reloadScreen();
                }
            });

            add(reload);
        }
    }


    //</editor-fold>


    //<editor-fold desc="getter and setter">
    public boolean isMouseHand() {
        return mouseHand;
    }

    public void setMouseHand(boolean mouseHand) {
        this.mouseHand = mouseHand;
        if(mouseHand) {
            mouseNormal = false;
            mouseOneToMany = false;
            mouseOneToOne = false;
            toTable = null;
            fromTable = null;
            setCursor(Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR));
        }
    }

    public boolean isMouseNormal() {
        return mouseNormal;
    }

    public void setMouseNormal(boolean mouseNormal) {
        this.mouseNormal = mouseNormal;
        if(mouseNormal) {
            mouseHand = false;
            mouseOneToMany = false;
            mouseOneToOne = false;
            toTable = null;
            fromTable = null;
            setCursor(Cursor.getDefaultCursor());
        }
    }

    public boolean isMouseOneToMany() {
        return mouseOneToMany;
    }

    public void setMouseOneToMany(boolean mouseOneToMany) {
        this.mouseOneToMany = mouseOneToMany;
        if(mouseOneToMany) {
            mouseHand = false;
            mouseNormal = false;
            mouseOneToOne = false;
            toTable = null;
            fromTable = null;
        }
    }

    public boolean isMouseOneToOne() {
        return mouseOneToOne;
    }

    public void setMouseOneToOne(boolean mouseOneToOne) {
        this.mouseOneToOne = mouseOneToOne;
        if(mouseOneToOne) {
            mouseHand = false;
            mouseNormal = false;
            mouseOneToMany = false;
            toTable = null;
            fromTable = null;
            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        }
    }
    //</editor-fold>


    // Tabloların konumlarına göre Max Width ve Hight getir.
    private Dimension getMaxWidthAndHeight() {

        Dimension dimension = new Dimension();

        if(tables == null) {
            dimension.setSize(0, 0);
            return dimension;
        }

        if (tables.size() <= 0) {
            dimension.setSize(0, 0);
            return dimension;
        }

        int[] width = new int[tables.size()];
        int[] heidth = new int[tables.size()];
        int i = 0;
        for (Table table : tables) {
            width[i] = table.getBox().getRight();
            heidth[i] = table.getBox().getBottom();
            i++;
        }
        Arrays.sort(width);
        Arrays.sort(heidth);
        dimension.setSize(width[tables.size()-1] + 25, heidth[tables.size()-1] + 25);

        return dimension;
    }

    

    private boolean addOneToOneRelation() {
        Field toPrimary = getPrimaryField(toTable);
        if(toPrimary == null) {
            JOptionPane.showMessageDialog(this, "Seçtiğiniz Tablonun Birincil Anahtarı Yok!");
            return false;
        }

        Field field = new Field();
        field.setName(toTable.getName() + "_" + toPrimary.getName());
        field.setType(new FieldType(toPrimary.getType().getType(), toPrimary.getType().getValue()));
        field.setNotNull(true);
        field.setTable(fromTable);

        fromTable.getFields().add(field);

        Relationship ship = new Relationship();
        ship.setField(field);
        ship.setToTable(toTable);
        ship.setType(Relationship.ONE_TO_ONE);
        ship.setName("fk_" + fromTable.getName() + "_" + toTable.getName() + "_" + toPrimary.getName());

        fromTable.getRelationships().add(ship);
        return true;
    }

    private Field getPrimaryField(Table table) {
        for(Field field : table.getFields()) {
            if(field.isPrimaryKey()) {
                return field;
            }
        }

        return null;
    }

    public void printOfPrinter() {
        PrinterJob printerJop = PrinterJob.getPrinterJob();
        printerJop.setPrintable(this);
        if(printerJop.printDialog()) {
            try {
                printerJop.print();
            }
            catch(PrinterException e) {
                System.out.println("PRINTER ERROR: " + e);
            }
        }
    }

    @Override
    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        if(pageIndex > 0) {
            return (NO_SUCH_PAGE);
        }

        Graphics2D g2 = (Graphics2D) g;
        g2.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
        disableDoubleBuffering(this);
        this.paint(g);
        enableDoubleBuffering(this);

        return(PAGE_EXISTS);
    }

    public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
    }

    public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }
}
