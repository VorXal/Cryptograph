package com.company;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WindowFrame extends JFrame {
    public WindowFrame(){
        super("Encryption Playfair");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setBounds(300, 300, 300, 300);
        setLayout(new BorderLayout());

        JTextField inputText = new JTextField(30);
        Box inputBox = Box.createHorizontalBox();
        inputBox.add(new JLabel("Input:"));
        inputBox.add(inputText);
        inputBox.add(Box.createHorizontalStrut(10));


        JButton encryptBtn = new JButton("Encrypt");
        JTextField keyText = new JTextField(10);
        Box keyBox = Box.createHorizontalBox();
        keyBox.add(new JLabel("Key:"));
        keyBox.add(keyText);
        keyBox.add(encryptBtn);


        Box outputBox = Box.createHorizontalBox();
        JTextField outputText = new JTextField(60);
        outputBox.add(new JLabel("Output:"));
        outputBox.add(outputText);
        outputBox.add(Box.createHorizontalStrut(10));


        encryptBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Cryptograph temp = new Cryptograph(inputText.getText(), keyText.getText());
                outputText.setText(temp.encryptWord());
            }
        });

        add(inputBox);
        add(Box.createVerticalStrut(12));
        add(outputBox);
        pack();

        Box mainBox = Box.createVerticalBox();
        mainBox.setBorder(new EmptyBorder(12,12,12,12));
        mainBox.add(inputBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(keyBox);
        mainBox.add(Box.createVerticalStrut(12));
        mainBox.add(outputBox);
        setContentPane(mainBox);
        pack();
        setResizable(false);
        setVisible(true);
    }
}
