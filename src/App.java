
// import de abstract window toolkit pour l'organisation et listeners
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// import de swing pour l'interface graphique
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

public class App extends JFrame implements ActionListener {

    // déclaration des composants
    private JTextField display;
    private JButton b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, bd;
    private JButton addBtn, subBtn, mulBtn, divBtn, equalBtn, clearBtn;
    String operator, num1, num2;

    public App() {

        // frame de la calculatrice
        setTitle("Calculatrice");
        setSize(400, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // pour organisation de la fenêtre
        setLayout(new BorderLayout());

        display = new JTextField(16);
        display.setEditable(false);
        display.setFont(new Font("Arial", Font.PLAIN, 36));
        // display en haut
        add(display, BorderLayout.NORTH);

        // panneau
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(5, 4, 10, 10));

        // panneau wrapper pour espacements
        JPanel panelWrapper = new JPanel(new BorderLayout());
        panelWrapper.setBorder(new EmptyBorder(10, 10, 10, 10));
        panelWrapper.add(panel, BorderLayout.CENTER);

        // placement panneau
        add(panelWrapper, BorderLayout.CENTER);

        // boutons numériques
        b0 = new JButton("0");
        b1 = new JButton("1");
        b2 = new JButton("2");
        b3 = new JButton("3");
        b4 = new JButton("4");
        b5 = new JButton("5");
        b6 = new JButton("6");
        b7 = new JButton("7");
        b8 = new JButton("8");
        b9 = new JButton("9");
        bd = new JButton(".");

        // boutons d'opérations
        addBtn = new JButton("+");
        subBtn = new JButton("-");
        mulBtn = new JButton("*");
        divBtn = new JButton("/");
        equalBtn = new JButton("=");
        clearBtn = new JButton("C");

        // couleur des boutons d'opérations
        addBtn.setBackground(Color.GRAY);
        subBtn.setBackground(Color.GRAY);
        mulBtn.setBackground(Color.GRAY);
        divBtn.setBackground(Color.GRAY);
        equalBtn.setBackground(Color.GREEN);
        clearBtn.setBackground(Color.PINK);

        // ajout des boutons dans le panneau
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(addBtn);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        panel.add(subBtn);
        panel.add(b7);
        panel.add(b8);
        panel.add(b9);
        panel.add(mulBtn);
        panel.add(bd);
        panel.add(b0);
        panel.add(clearBtn);
        panel.add(divBtn);

        panel.add(equalBtn);

        // listeners
        b0.addActionListener(this);
        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);
        b4.addActionListener(this);
        b5.addActionListener(this);
        b6.addActionListener(this);
        b7.addActionListener(this);
        b8.addActionListener(this);
        b9.addActionListener(this);
        addBtn.addActionListener(this);
        subBtn.addActionListener(this);
        mulBtn.addActionListener(this);
        divBtn.addActionListener(this);
        clearBtn.addActionListener(this);
        equalBtn.addActionListener(this);
        bd.addActionListener(this);

        // initialisation des variables pour les opérations
        num1 = "";
        num2 = "";
        operator = "";
    }

    public void actionPerformed(ActionEvent e) {
        // récupération du bouton cliqué
        String command = e.getActionCommand();

        // chaine de conditions pour les opérations
        // A - si un chiffre est cliqué
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {

            // et si un opérateur est présent ou non, on concatène sur la bonne opérande
            if (!operator.equals(""))
                num2 = num2 + command;
            else
                num1 = num1 + command;

            // affichage
            display.setText(num1 + operator + num2);

            // B - si c'est clear qui est cliqué remise à zéro
        } else if (command.charAt(0) == 'C') {
            num1 = operator = num2 = "";

            // affichage
            display.setText(num1 + operator + num2);

            // C- dans le cas d'un appui sur égal
        } else if (command.charAt(0) == '=') {

            double result;

            // on calcule selon l'opérateur avec conversion en double
            if (operator.equals("+"))
                result = (Double.parseDouble(num1) + Double.parseDouble(num2));
            else if (operator.equals("-"))
                result = (Double.parseDouble(num1) - Double.parseDouble(num2));
            else if (operator.equals("/"))
                result = (Double.parseDouble(num1) / Double.parseDouble(num2));
            else
                result = (Double.parseDouble(num1) * Double.parseDouble(num2));

            // affichage du résultat
            display.setText(String.valueOf(result));

            // qui devient num1
            num1 = Double.toString(result);

            // préparation pour le prochain calcul
            operator = num2 = "";

            // D - dans le cas d'un appui sur un opérateur
        } else {
            if (operator.equals("") || num2.equals(""))
                operator = command;

            // et qu'il y a déjà la seconde opérande, on calcule comme avec égal
            else {
                double result;

                if (operator.equals("+"))
                    result = (Double.parseDouble(num1) + Double.parseDouble(num2));
                else if (operator.equals("-"))
                    result = (Double.parseDouble(num1) - Double.parseDouble(num2));
                else if (operator.equals("/"))
                    result = (Double.parseDouble(num1) / Double.parseDouble(num2));
                else
                    result = (Double.parseDouble(num1) * Double.parseDouble(num2));

                num1 = Double.toString(result);

                operator = command;

                // nettoyage num2
                num2 = "";
            }

            display.setText(num1 + operator + num2);

        }
    }

    public static void main(String[] args) {
        // création et affichage de la fenêtre
        App calc = new App();
        calc.setVisible(true);
    }

}
