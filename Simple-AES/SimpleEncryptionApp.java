import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimpleEncryptionApp extends JFrame {
    private JTextField plaintextField; // 明文输入框
    private JTextField keyField; // 密钥输入框
    private JTextField ciphertextField; // 密文输出框
    private JTextField decryptKeyField; // 解密密钥输入框
    private JTextField decryptedField; // 解密后明文输出框
    

    public SimpleEncryptionApp() {
        setTitle("加密解密程序"); // 设置窗口标题
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // 设置窗口关闭时的操作
        setSize(700, 400); // 设置窗口大小
        setLocationRelativeTo(null); // 设置窗口居中显示
        setLayout(new GridBagLayout()); // 设置布局为网格包布局
        GridBagConstraints gbc = new GridBagConstraints(); // 创建网格包布局的约束对象
        gbc.insets = new Insets(5, 5, 5, 5); // 设置组件之间的间距

        //开始添加组件
        gbc.gridx = 0;
        gbc.gridy = 0;
        JRadioButton one = new JRadioButton("单密钥加密");
        JRadioButton two = new JRadioButton("双重加密");
        JRadioButton three = new JRadioButton("三重加密");
        ButtonGroup keys = new ButtonGroup();
        keys.add(one);
        keys.add(two);
        keys.add(three);
        add(one,gbc);
        gbc.gridx = 1;
        add(two,gbc);
        gbc.gridx = 2;
        add(three,gbc);

        gbc.gridx = 0; // 设置组件在网格中的列索引
        gbc.gridy = 1; // 设置组件在网格中的行索引
        add(new JLabel("明文:"), gbc); // 添加标签组件到窗口

        gbc.gridx = 1; // 更新列索引
        plaintextField = new JTextField(20); // 创建一个文本框组件
        add(plaintextField, gbc); // 添加文本框组件到窗口

        gbc.gridx = 2;
        JRadioButton option1 = new JRadioButton("16位二进制");
        JRadioButton option2 = new JRadioButton("字符串");
        // 创建按钮组，确保只能选择一个选项
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(option1);
        buttonGroup.add(option2);
        add(option1,gbc);
        gbc.gridx = 3;
        add(option2,gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(new JLabel("密钥（16位二进制数字）:"), gbc);

        gbc.gridx = 1;
        keyField = new JTextField(20);
        add(keyField, gbc);

        gbc.gridx = 2;
        JButton encryptButton = new JButton("加密");
        add(encryptButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(new JLabel("加密后密文:"), gbc);

        gbc.gridx = 1;
        ciphertextField = new JTextField(20);
        //ciphertextField.setEditable(false);
        add(ciphertextField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        add(new JLabel("解密密钥（16位二进制数字）:"), gbc);

        gbc.gridx = 1;
        decryptKeyField = new JTextField(20);
        add(decryptKeyField, gbc);

        gbc.gridx = 2;
        JButton decryptButton = new JButton("解密");
        add(decryptButton, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        add(new JLabel("解密后明文:"), gbc);

        gbc.gridx = 1;
        decryptedField = new JTextField(20);
        decryptedField.setEditable(false);
        add(decryptedField, gbc);


        //加密监听器
        encryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String plaintext = plaintextField.getText();
                String key = keyField.getText();
                if(option1.isSelected()) {
                    if (plaintext.length() != 16 || !isBinary(plaintext)) {
                        JOptionPane.showMessageDialog(SimpleEncryptionApp.this, "明文必须为16位二进制数字", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if(option2.isSelected()){
                    if(plaintext.equals("")){
                        JOptionPane.showMessageDialog(SimpleEncryptionApp.this,"明文不可为空！","错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else {
                    JOptionPane.showMessageDialog(SimpleEncryptionApp.this,"请选择明文格式","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                if(one.isSelected()) {
                    if (key.length() != 16 || !isBinary(key)) {
                        JOptionPane.showMessageDialog(SimpleEncryptionApp.this, "密钥必须为16位二进制数字", "错误", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if(two.isSelected()){
                    if(key.length() != 32 || !isBinary(key)){
                        JOptionPane.showMessageDialog(SimpleEncryptionApp.this,"双重密钥必须为32位二进制数字","错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                else if(three.isSelected()){
                    if(key.length() != 48 || !isBinary(key)){
                        JOptionPane.showMessageDialog(SimpleEncryptionApp.this,"三重密钥必须为48位二进制数字","错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                String ciphertext = new String();
                if(option1.isSelected()) {
                    if(one.isSelected()) {
                        ciphertext = Cipher.cipher(plaintext, key);
                    }
                    else if(two.isSelected()){
                        ciphertext = Cipher.cipher2(plaintext,key);
                    }
                    else if(three.isSelected()){
                        ciphertext = Cipher.cipher3(plaintext,key);
                    }
                }
                else if(option2.isSelected()){
                    if(one.isSelected()) {
                        ciphertext = ASCII.asciiEncipher(plaintext, key);
                    }
                    else if(two.isSelected()){
                        ciphertext = ASCII.asciiEncipher2(plaintext,key);
                    }
                    else if(three.isSelected()){
                        ciphertext = ASCII.asciiEncipher3(plaintext,key);
                    }
                }
                ciphertextField.setText(ciphertext);
            }
        });

        //解密监听器
        decryptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ciphertext = ciphertextField.getText();
                String decryptKey = decryptKeyField.getText();
                if(!option1.isSelected()&&!option2.isSelected()){
                    JOptionPane.showMessageDialog(SimpleEncryptionApp.this,"请选择明文格式","错误",JOptionPane.ERROR_MESSAGE);
                    return;
                }
                else {
                    if(one.isSelected()) {
                        if ((decryptKey.length() != 16 || !isBinary(decryptKey))) {
                            JOptionPane.showMessageDialog(SimpleEncryptionApp.this, "解密密钥必须为16位二进制数字", "错误", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else if(two.isSelected()) {
                        if ((decryptKey.length() != 32 || !isBinary(decryptKey))) {
                            JOptionPane.showMessageDialog(SimpleEncryptionApp.this, "解密密钥必须为32位二进制数字", "错误", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    else if(three.isSelected()) {
                        if ((decryptKey.length() != 48 || !isBinary(decryptKey))) {
                            JOptionPane.showMessageDialog(SimpleEncryptionApp.this, "解密密钥必须为48位二进制数字", "错误", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    }
                    if(ciphertext.equals("")){
                        JOptionPane.showMessageDialog(SimpleEncryptionApp.this,"密文不可为空","错误",JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                }
                String decryptedText = new String();
                if(option1.isSelected()) {
                    if(one.isSelected()) {
                        decryptedText = Decipher.decipher(ciphertext, decryptKey);
                    }
                    else if(two.isSelected()){
                        decryptedText = Decipher.decipher2(ciphertext, decryptKey);
                    }
                    else if(three.isSelected()){
                        decryptedText = Decipher.decipher3(ciphertext, decryptKey);
                    }
                }
                else if(option2.isSelected()){
                    if(one.isSelected()) {
                        decryptedText = ASCII.asciiDecipher(ciphertext, decryptKey);
                    }
                    else if(two.isSelected()){
                        decryptedText = ASCII.asciiDecipher2(ciphertext, decryptKey);
                    }
                    else if(three.isSelected()){
                        decryptedText = ASCII.asciiDecipher3(ciphertext, decryptKey);
                    }
                }
                decryptedField.setText(decryptedText);
            }
        });
        setVisible(true);
    }

    //判断是否是二进制数
    private boolean isBinary(String str) {
        for (char c : str.toCharArray()) {
            if (c != '0' && c != '1') {
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new SimpleEncryptionApp();
            }
        });
    }
}
