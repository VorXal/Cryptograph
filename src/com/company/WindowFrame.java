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

        Box firstPublicKeyBox = Box.createHorizontalBox();
        JTextField firstPublicKeyText = new JTextField(60);
        firstPublicKeyBox.add(new JLabel("First Public Key:"));
        firstPublicKeyBox.add(firstPublicKeyText);
        firstPublicKeyBox.add(Box.createHorizontalStrut(10));

        Box secondPublicKeyBox = Box.createHorizontalBox();
        JTextField secondPublicKeyText = new JTextField(60);
        secondPublicKeyBox.add(new JLabel("Second Public Key:"));
        secondPublicKeyBox.add(secondPublicKeyText);
        secondPublicKeyBox.add(Box.createHorizontalStrut(10));

        Box firstPrivateKeyBox = Box.createHorizontalBox();
        JTextField firstPrivateKeyText = new JTextField(60);
        firstPrivateKeyBox.add(new JLabel("My Private Key:"));
        firstPrivateKeyBox.add(firstPrivateKeyText);
        firstPrivateKeyBox.add(Box.createHorizontalStrut(10));

        Box firstPartialKeyBox = Box.createHorizontalBox();
        JTextField firstPartialKeyText = new JTextField(60);
        firstPartialKeyBox.add(new JLabel("My Partial Key:"));
        JButton generatePartialKeyBtn = new JButton("Generate Partial Key");
        firstPartialKeyBox.add(firstPartialKeyText);
        firstPartialKeyBox.add(Box.createHorizontalStrut(10));
        firstPartialKeyBox.add(generatePartialKeyBtn);

        Box secondPartialKeyBox = Box.createHorizontalBox();
        JTextField secondPartialKeyText = new JTextField(60);
        secondPartialKeyBox.add(new JLabel("Second Partial Key:"));
        secondPartialKeyBox.add(secondPartialKeyText);
        secondPartialKeyBox.add(Box.createHorizontalStrut(10));

        JCheckBox checkBox = new JCheckBox("Other");


        encryptBtn.addActionListener(e -> {
            keyText.setText(Protocol.generateFinalKey(
                    Protocol.generateFinalNumericKey(
                            secondPartialKeyText.getText(),
                            firstPrivateKeyText.getText(),
                            firstPublicKeyText.getText(),
                            checkBox.isSelected()
                    )
            ));
            Cryptograph temp = new Cryptograph(inputEncryptText.getText(), keyText.getText());
            outputText.setText(temp.encryptWord());
        });

        decryptBtn.addActionListener(e -> {
            Cryptograph temp = new Cryptograph(inputDecryptText.getText(), keyText.getText());
            outputText.setText(temp.decryptWord());
        });

        generatePartialKeyBtn.addActionListener(e -> {
            firstPartialKeyText.setText(Protocol.generatePartialKey(
                    firstPublicKeyText.getText(),
                    secondPublicKeyText.getText(),
                    Integer.parseInt(firstPrivateKeyText.getText()),
                    checkBox.isSelected()
                ));
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
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(firstPublicKeyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(firstPublicKeyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(secondPublicKeyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(firstPrivateKeyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(firstPartialKeyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(secondPartialKeyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(checkBox);

        setContentPane(mainBox);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
