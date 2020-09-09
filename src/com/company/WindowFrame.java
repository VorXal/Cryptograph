package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class WindowFrame extends JFrame {
    public WindowFrame(){
        super("Encryption Playfair");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 300);
        setLayout(new BorderLayout());

        JTextField inputEncryptText = new JTextField(30);
        Box inputEncryptBox = Box.createHorizontalBox();
        inputEncryptBox.add(new JLabel("Input Encrypt:"));
        inputEncryptBox.add(inputEncryptText);
        inputEncryptBox.add(Box.createHorizontalStrut(10));


        JButton encryptBtn = new JButton("Encrypt");
        JTextField keyText = new JTextField(10);
        Box keyBox = Box.createHorizontalBox();
        keyBox.add(new JLabel("Key:"));
        keyBox.add(keyText);
        keyBox.add(encryptBtn);


        Box outputEncryptBox = Box.createHorizontalBox();
        JTextField outputText = new JTextField(60);
        outputEncryptBox.add(new JLabel("Output:"));
        outputEncryptBox.add(outputText);
        outputEncryptBox.add(Box.createHorizontalStrut(10));


        JTextField inputDecryptText = new JTextField(30);
        Box inputDecryptBox = Box.createHorizontalBox();
        JButton decryptBtn = new JButton("Decrypt");
        inputDecryptBox.add(new JLabel("Input Decrypt:"));
        inputDecryptBox.add(inputDecryptText);
        inputDecryptBox.add(Box.createHorizontalStrut(10));
        inputDecryptBox.add(decryptBtn);


        encryptBtn.addActionListener(e -> {
            Cryptograph temp = new Cryptograph(inputEncryptText.getText(), keyText.getText());
            outputText.setText(temp.encryptWord());
        });

        decryptBtn.addActionListener(e -> {
            Cryptograph temp = new Cryptograph(inputDecryptText.getText(), keyText.getText());
            outputText.setText(temp.decryptWord());
        });


        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(inputEncryptBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(keyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(outputEncryptBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(inputDecryptBox);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
