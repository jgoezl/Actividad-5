package actividad5.pkg1;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.swing.JOptionPane;


public class Formulario extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Formulario.class.getName());

    public Formulario() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnCreate = new javax.swing.JButton();
        btnRead = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnClear = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtNumber = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Name");

        jLabel2.setText("Number");

        btnCreate.setText("Create");
        btnCreate.addActionListener(this::btnCreateActionPerformed);

        btnRead.setText("Read");
        btnRead.addActionListener(this::btnReadActionPerformed);

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(this::btnUpdateActionPerformed);

        btnDelete.setText("Delete");
        btnDelete.addActionListener(this::btnDeleteActionPerformed);

        btnClear.setText("Clear");
        btnClear.addActionListener(this::btnClearActionPerformed);

        btnExit.setText("Exit");
        btnExit.addActionListener(this::btnExitActionPerformed);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnCreate)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 54, Short.MAX_VALUE)
                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnRead)
                                .addGap(18, 18, 18)
                                .addComponent(btnUpdate)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnDelete))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                                .addComponent(txtNumber, javax.swing.GroupLayout.Alignment.LEADING))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(76, 76, 76)
                        .addComponent(btnClear)
                        .addGap(45, 45, 45)
                        .addComponent(btnExit)))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(46, 46, 46)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNumber, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnCreate)
                    .addComponent(btnRead)
                    .addComponent(btnUpdate)
                    .addComponent(btnDelete))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnClear)
                    .addComponent(btnExit))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void btnCreateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {
        
            String newName = String.valueOf(txtName.getText());
            long newNumber = Long.parseLong(txtNumber.getText());
        
            String nameNumberString;
            String name;
            long number;
            int index;
                      
            File file = new File("friendsContact.txt");
            
            if (!file.exists()) {
                file.createNewFile();
            }
            
            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;
            
            while (raf.getFilePointer() < raf.length()) {
                nameNumberString = raf.readLine();
                String[] lineSplit = nameNumberString.split("!");
                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);
                
                if (name.equals(newName) && number == newNumber) {
                    found = true;
                    JOptionPane.showMessageDialog(null, " Attention " + newName + " the record exists");
                    break;
                }
            
            }
            
            if (found == false) {
                raf.seek(raf.length());
                raf.writeBytes(newName + "!" + newNumber);
                raf.writeBytes(System.lineSeparator());
                JOptionPane.showMessageDialog(null, "The friend " + newName + " was added.");
                raf.close();
            }
            else {
                raf.close();
            }
        } 
        catch (IOException ioe) {
            
        } 
        catch (NumberFormatException nef){
        
        }
        
    }                                         

    private void btnReadActionPerformed(java.awt.event.ActionEvent evt) {                                        
        try {

            String nameNumberString;
            String name;
            
            String newName;
            newName = String.valueOf(txtName.getText());
            
            long number;
            int index;

            File file = new File("friendsContact.txt");

            if (!file.exists()) {

                file.createNewFile();
            }


            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;
            
            while (raf.getFilePointer() < raf.length()) {

                // reading line from the file.
                nameNumberString = raf.readLine();


                String[] lineSplit = nameNumberString.split("!");

                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                
                if (name.equals(newName)) {
                    
                    txtName.setText(String.valueOf(name));
                    txtNumber.setText(String.valueOf(number));
                    found = true;
                    
                }
                else if (!name.equals(newName)){
                    JOptionPane.showMessageDialog(null, " The friend " + newName + " does not exits.");
                
                }
                found = false;
                
            }

            

        }catch (IOException ioe){

                System.out.println(ioe);
        } catch (NumberFormatException nef){

                System.out.println(nef);
            }

    }                                       

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {

            String newName = String.valueOf(txtName.getText());

            long newNumber = Long.parseLong(String.valueOf(txtNumber.getText()));

            String nameNumberString;
            String name;
            long number;
            int index;

            File file = new File("friendsContact.txt");

            if (!file.exists()) {

                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {

                nameNumberString = raf.readLine();

                String[] lineSplit = nameNumberString.split("!");

                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName) || number == newNumber) {
                    found = true;
                    break;
                }
            }

            if (found == true) {

                File tmpFile = new File("temp.txt");

                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {

                    nameNumberString = raf.readLine();

                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(0, index);

                    if (name.equals(newName)) {

                        nameNumberString = name + "!" + String.valueOf(newNumber);
                    }

                    tmpraf.writeBytes(nameNumberString);

                    tmpraf.writeBytes(
                        System.lineSeparator());
                }

                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());

                tmpraf.close();
                raf.close();

                tmpFile.delete();

                JOptionPane.showMessageDialog(null, " The friend´s number of " + newName + " was updated. ");
            }

            else {

                raf.close();

                System.out.println(" Input name" + " does not exists. ");
            }
        }

        catch (IOException ioe) {
            System.out.println(ioe);
        }

        catch (NumberFormatException nef) {
            System.out.println(nef);
        }
    }                                         

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {                                          
        try {

            String newName = String.valueOf(txtName.getText());

            String nameNumberString;
            String name;
            long number;
            int index;

            File file = new File("friendsContact.txt");

            if (!file.exists()) {

                file.createNewFile();
            }

            RandomAccessFile raf = new RandomAccessFile(file, "rw");
            boolean found = false;

            while (raf.getFilePointer() < raf.length()) {

                nameNumberString = raf.readLine();

                String[] lineSplit = nameNumberString.split("!");

                name = lineSplit[0];
                number = Long.parseLong(lineSplit[1]);

                if (name.equals(newName)) {
                    found = true;
                    break;
                }
            }

            if (found == true) {

                File tmpFile = new File("temp.txt");

                RandomAccessFile tmpraf = new RandomAccessFile(tmpFile, "rw");

                raf.seek(0);

                while (raf.getFilePointer() < raf.length()) {

                    nameNumberString = raf.readLine();

                    index = nameNumberString.indexOf('!');
                    name = nameNumberString.substring(0, index);

                    if (name.equals(newName)) {

                        continue;
                    }

                    tmpraf.writeBytes(nameNumberString);

                    tmpraf.writeBytes(
                        System.lineSeparator());
                }
                raf.seek(0);
                tmpraf.seek(0);

                while (tmpraf.getFilePointer() < tmpraf.length()) {
                    raf.writeBytes(tmpraf.readLine());
                    raf.writeBytes(System.lineSeparator());
                }

                raf.setLength(tmpraf.length());


                tmpraf.close();
                raf.close();

                tmpFile.delete();

                JOptionPane.showMessageDialog(null, " The friend´s number of " + newName + " was deleted. ");
            }

            else {

                raf.close();

                System.out.println(" Input name" + " does not exists. ");
            }
        }

        catch (IOException ioe) {
            System.out.println(ioe);
        }
    }                                         

    private void btnClearActionPerformed(java.awt.event.ActionEvent evt) {                                         
        txtNumber.setText("");
        txtName.setText("");
    }                                        

    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {                                        
        System.exit(0);
    }                                       


    public static void main(String args[]) {

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

        java.awt.EventQueue.invokeLater(() -> new Formulario().setVisible(true));
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton btnClear;
    private javax.swing.JButton btnCreate;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnRead;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtName;
    private javax.swing.JTextField txtNumber;
    // End of variables declaration                   
}
