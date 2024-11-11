import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;
import javax.swing.*;

public class numbertoword extends JFrame implements ActionListener {
    private JTextField numField;
    private JTextArea wordArea;
    private JButton copyButton;

    public numbertoword() {
        setTitle("Number to Word Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setResizable(false);
        getContentPane().setBackground(Color.decode("#C6FFAB"));

ImageIcon appIcon = new ImageIcon("nwssulogo.png");
setIconImage(appIcon.getImage());

        ImageIcon logoIcon = new ImageIcon("nwssulogo.png");
        Image logoImage = logoIcon.getImage();
        int width = 50; 
        int height = 50; 
        Image newLogoImage = logoImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        ImageIcon newLogoIcon = new ImageIcon(newLogoImage);
        JLabel logoLabel = new JLabel(newLogoIcon);
        logoLabel.setBounds(150, 10, width, height);
        add(logoLabel);

String universityName = "Northwest Samar State University";
JLabel nameLabel = new JLabel(universityName);
nameLabel.setBounds(45, 70, 300, 20);
nameLabel.setFont(new Font("Calibre", Font.BOLD, 17));
add(nameLabel);

String universityAddress = "Ruede Street, Calbayog City, Samar";
JLabel addressLabel = new JLabel(universityAddress);
addressLabel.setBounds(78, 90, 300, 20);
addressLabel.setFont(new Font("calibre", Font.PLAIN, 12));
add(addressLabel);

        JLabel numLabel = new JLabel("Enter Number:");
        numLabel.setBounds(20, 129, 150, 20);
        numField = new JTextField();
        numField.setBounds(130, 130, 200, 20);
        numField.addActionListener(this);

        JLabel wordLabel = new JLabel("Words:");
        wordLabel.setBounds(20, 160, 100, 20);
        wordArea = new JTextArea();
        wordArea.setLineWrap(true);
        wordArea.setWrapStyleWord(true);
        wordArea.setPreferredSize(new Dimension(200, 100));
        wordArea.setBounds(130, 160, 200, 100);

        copyButton = new JButton("Copy to Clipboard");
        copyButton.setBounds(130, 280, 150, 20);
        copyButton.addActionListener(this);

        add(numLabel);
        add(numField);
        add(wordLabel);
        add(wordArea);
        add(copyButton);

        setSize(360, 360);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == copyButton) {
            StringSelection stringSelection = new StringSelection(wordArea.getText());
            Clipboard clipboard = null;
            try {
                clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                clipboard.setContents(stringSelection, null);
            } catch (HeadlessException ex) {
                System.err.println("Error copying to clipboard: " + ex.getMessage());
            }
        } else if (e.getSource() == numField) {
            System.out.println("Converting number to words");
            long number = Long.parseLong(numField.getText());
            String words = convertNumberToWord(number);
            System.out.println("Words: " + words);
            wordArea.setText(words);
        }
    }

    private static String convertNumberToWord(long number) {
        if (number == 0) {
            return "zero";
        }

        StringBuilder word = new StringBuilder();
        int i = 0;

        do {
            long num = number % 1000;
            if (num > 0) {
                String hundreds = convertHundredsToWord((int) num);
                if (i > 0) {
                    word.insert(0, thousands[i] + " ");
                }
                word.insert(0, hundreds + " ");
            }
            i++;
            number /= 1000;
        } while (number > 0);

        return word.toString().trim();
    }

    private static String convertHundredsToWord(int number) {
        StringBuilder word = new StringBuilder();

        if (number >= 100) {
            int hundred = number / 100;
            word.append(ones[hundred]).append(" hundred ");
            number %= 100;
        }

        if (number >= 20) {
            int tensDigit = number / 10;
            word.append(tens[tensDigit]).append(" ");
            number %= 10;
        }

        if (number > 0) {
            word.append(ones[number]).append(" ");
        }

        return word.toString().trim();
    }

    public static void main(String[] args) {
        new numbertoword();
    }

    private static final String[] ones = {"", "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"};
    private static final String[] tens = {"", "", "twenty", "thirty", "forty", "fifty", "sixty", "seventy", "eighty", "ninety"};
    private static final String[] thousands = {"", "thousand", "million", "billion", "trillion"};
}