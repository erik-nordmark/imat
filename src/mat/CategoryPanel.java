package mat;

import javax.swing.BorderFactory;
import javax.swing.border.CompoundBorder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nordmark
 */
public class CategoryPanel extends javax.swing.JPanel {
    private String categoryName;
    
    /**
     * Creates new form CategoryPanel
     */
    public CategoryPanel() {
        initComponents();
    }
    
    public CategoryPanel(String categoryName) {
        this();
        this.categoryName = categoryName;
        catLabel.setText(categoryName);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        catLabel = new javax.swing.JLabel();

        setBackground(new java.awt.Color(80, 80, 80));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(40, 40, 40)));
        setForeground(new java.awt.Color(210, 210, 210));

        catLabel.setFont(new java.awt.Font("Avenir Next", 0, 24)); // NOI18N
        catLabel.setForeground(new java.awt.Color(240, 240, 240));
        catLabel.setText("Favoriter");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(catLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 156, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(catLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel catLabel;
    // End of variables declaration//GEN-END:variables

    public String getCategoryName(){
        return categoryName;
    }
    
    public void mousePressed(){
        // Ändra utseende för hur panelen ska se ut när den är nedtryckt.
        setBackground(new java.awt.Color(70,70,70));
    }
    
    public void mouseReleased(){
        // Ändra hur panelen ska se ut när användaren slutar trycka ned knappen.
        setBackground(new java.awt.Color(255,255,255));
    }
    
    public void mouseEntered(){
        // Ändra hur panelen ska se ut när användaren håller musen ovanför den.
        new CompoundBorder(
        BorderFactory.createMatteBorder(0, 5, 0, 0, new java.awt.Color(13,116,255)),
        BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(40, 40, 40)));
        catLabel.setForeground(new java.awt.Color(13,116,255));
    }
    
    public void mouseExited(){
        // Ändra hur panelen ska se ut när användaren tar bort musen från panelen.
        resetPanel();
    }
    
    @Override
    public String toString(){
        return "En panel för kategori med : " + categoryName;
    }
    
    public void resetPanel(){
        setBackground(new java.awt.Color(80, 80, 80));
        catLabel.setForeground(new java.awt.Color(240, 240, 240));
        setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(40, 40, 40)));
    }
}
