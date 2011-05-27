package org.open2jam.gui.parts;

import java.awt.Font;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;
import javax.swing.table.DefaultTableModel;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.open2jam.Config;
import org.open2jam.parser.Event;
import org.open2jam.render.lwjgl.TrueTypeFont;

/**
 *
 * @author CdK
 */
public class Configuration extends javax.swing.JPanel {

    private EnumMap<Event.Channel,Integer> kb_map;

    private HashMap<Integer, Event.Channel> table_map = new HashMap<Integer,Event.Channel>();
    
    /** Creates new form Configuration */
    public Configuration() {
        initComponents();
        
        loadTableKeys(Config.KeyboardType.K7);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bSave = new javax.swing.JButton();
        panel_keys = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        combo_keyboardConfig = new javax.swing.JComboBox();
        tKeys_scroll = new javax.swing.JScrollPane();
        tKeys = new javax.swing.JTable();

        bSave.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bSave.setText("Save");
        bSave.setMaximumSize(new java.awt.Dimension(65, 23));
        bSave.setMinimumSize(new java.awt.Dimension(65, 23));
        bSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bSaveActionPerformed(evt);
            }
        });

        jLabel1.setText("Select the keyboard configuration you want to edit:");

        combo_keyboardConfig.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "7 Keys", "5 Keys", "6 Keys", "4 Keys", "8 Keys" }));
        combo_keyboardConfig.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo_keyboardConfigActionPerformed(evt);
            }
        });

        tKeys.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Key", "Assign"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Object.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tKeys.setColumnSelectionAllowed(true);
        tKeys.getTableHeader().setReorderingAllowed(false);
        tKeys.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tKeysMouseClicked(evt);
            }
        });
        tKeys_scroll.setViewportView(tKeys);

        javax.swing.GroupLayout panel_keysLayout = new javax.swing.GroupLayout(panel_keys);
        panel_keys.setLayout(panel_keysLayout);
        panel_keysLayout.setHorizontalGroup(
            panel_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_keysLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(combo_keyboardConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(panel_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_keysLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(tKeys_scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 467, Short.MAX_VALUE)
                    .addContainerGap()))
        );
        panel_keysLayout.setVerticalGroup(
            panel_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel_keysLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panel_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel1)
                    .addComponent(combo_keyboardConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(306, Short.MAX_VALUE))
            .addGroup(panel_keysLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(panel_keysLayout.createSequentialGroup()
                    .addGap(48, 48, 48)
                    .addComponent(tKeys_scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(panel_keys, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panel_keys, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bSave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(152, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bSaveActionPerformed
        Config.KeyboardType kt;
        switch(combo_keyboardConfig.getSelectedIndex()) {
            case 0:kt = Config.KeyboardType.K7;break;
            case 1:kt = Config.KeyboardType.K5;break;
            case 2:kt = Config.KeyboardType.K6;break;
            case 3:kt = Config.KeyboardType.K4;break;
            case 4:kt = Config.KeyboardType.K8;break;
            default: return;
        }
        Config.setKeyboardMap(kb_map, kt);
}//GEN-LAST:event_bSaveActionPerformed

    private void tKeysMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tKeysMouseClicked
        int row = tKeys.getSelectedRow();
        if(tKeys.getValueAt(row, 0) == null) return;
        
        int code;
        int lastkey = Keyboard.getKeyIndex(tKeys.getValueAt(row, 1).toString());
        try {
            code = read_keyboard_key(lastkey);
        } catch(LWJGLException e) {
            // FML
            return;
        }
        if(kb_map.containsValue(code)) return; //check for duplicates, TODO something informing about the duplicate
        Event.Channel c = table_map.get(row);
        kb_map.put(c, code);
        tKeys.setValueAt(Keyboard.getKeyName(code), row, 1);
}//GEN-LAST:event_tKeysMouseClicked

    private void combo_keyboardConfigActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo_keyboardConfigActionPerformed
        // TODO get a message asking if you change, the changes will not be saved
        // Also, it would be a good idea to get the keys of the skin and only show those
        // or maybe put the names of the combo like "8 Keys (not supported)" when the keys don't match
        switch(combo_keyboardConfig.getSelectedIndex()) {
            case 0:loadTableKeys(Config.KeyboardType.K7);break;
            case 1:loadTableKeys(Config.KeyboardType.K5);break;
            case 2:loadTableKeys(Config.KeyboardType.K6);break;
            case 3:loadTableKeys(Config.KeyboardType.K4);break;
            case 4:loadTableKeys(Config.KeyboardType.K8);break;
        }
}//GEN-LAST:event_combo_keyboardConfigActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bSave;
    private javax.swing.JComboBox combo_keyboardConfig;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel panel_keys;
    private javax.swing.JTable tKeys;
    private javax.swing.JScrollPane tKeys_scroll;
    // End of variables declaration//GEN-END:variables

    private void loadTableKeys(Config.KeyboardType kt)
    {
        kb_map = Config.getKeyboardMap(kt).clone();
        DefaultTableModel dm = (DefaultTableModel)tKeys.getModel();
        dm.setRowCount(kb_map.size());

        int i = 0;
        for(Map.Entry<Event.Channel,Integer> entry : kb_map.entrySet())
        {
            tKeys.setValueAt(entry.getKey().toString(), i, 0);
            tKeys.setValueAt(Keyboard.getKeyName(entry.getValue()), i, 1);
            table_map.put(i, entry.getKey());
            i++;
        }
    }
    
    private static Font font = new Font("Tahoma", Font.BOLD, 14);

    private int read_keyboard_key(int lastkey) throws LWJGLException
    {
        String place = tKeys.getValueAt(tKeys.getSelectedRow(), 0).toString();
        if(Display.isCreated())throw new LWJGLException();
        Display.setDisplayMode(new DisplayMode(220,50));
        Display.setTitle(place);
        Display.setVSyncEnabled(true);
        Display.setIcon(null);
        Display.create();
        Display.setLocation(-1, -1);

        // enable textures since we're going to use these for our sprites
        GL11.glEnable(GL11.GL_TEXTURE_2D);

        // disable the OpenGL depth test since we're rendering 2D graphics
        GL11.glDisable(GL11.GL_DEPTH_TEST);

        // enable apha blending
        GL11.glEnable(GL11.GL_BLEND);
        GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

        GL11.glMatrixMode(GL11.GL_PROJECTION);
        GL11.glLoadIdentity();

        GL11.glOrtho(0, 220, 50, 0, -1, 1);

        GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
        GL11.glMatrixMode(GL11.GL_MODELVIEW);
        GL11.glLoadIdentity();

        TrueTypeFont trueTypeFont = new TrueTypeFont(font, false);

        int code;
        do{
            Display.update();
            trueTypeFont.drawString(10, 18, "Press a KEY for " + place, 1, -1);
            trueTypeFont.drawString(10, 34, "Last assign was " + Keyboard.getKeyName(lastkey), 1, -1);
            trueTypeFont.drawString(10, 50, "Press ESC or close to cancel", 1, -1);
            Keyboard.next();
            if(Display.isCloseRequested() || Keyboard.getEventKey() == Keyboard.KEY_ESCAPE)
                code = lastkey;
            else
                code = Keyboard.getEventKey();
        }
        while(code == Keyboard.CHAR_NONE);
        trueTypeFont.destroy();
        Display.destroy();
        return code;
    }
}
