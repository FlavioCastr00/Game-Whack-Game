import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class WhackGame {
    int largura = 600;
    int altura = 650;

    JFrame janela = new JFrame("Whack Game");
    JLabel texto = new JLabel();
    JPanel painel = new JPanel();
    JPanel grid = new JPanel();

    JButton[] botões = new JButton[9];
    ImageIcon rato;
    ImageIcon planta;

    JButton casaRatoAtual;
    JButton casaPlantaAtual;

    Random rand = new Random();
    Timer setRatoTemp;
    Timer setPlantaTemp;

    int pontuação = 0;
    int recorde = 0;

    WhackGame(){
        //janela.setVisible(true);
        janela.setSize(largura, altura);
        janela.setLocationRelativeTo(null);
        janela.setResizable(false);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        janela.setLayout(new BorderLayout());

        texto.setFont(new Font("Arial", Font.PLAIN, 40));
        texto.setHorizontalAlignment(JLabel.CENTER);
        texto.setText("Recorde: " + recorde + " - Pontuação: 0");
        texto.setOpaque(true);

        painel.setLayout(new BorderLayout());
        painel.add(texto);
        janela.add(painel, BorderLayout.NORTH);

        grid.setLayout(new GridLayout(3, 3));
        //grid.setBackground(Color.black);
        janela.add(grid);

        //planta = new ImageIcon(getClass().getResource("./piranha.png"));
        Image plantaIMG = new ImageIcon(getClass().getResource("./piranha.png")).getImage();
        planta = new ImageIcon(plantaIMG.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

        Image ratoIMG = new ImageIcon(getClass().getResource("./monty.png")).getImage();
        rato = new ImageIcon(ratoIMG.getScaledInstance(150, 150, java.awt.Image.SCALE_SMOOTH));

        for (int i = 0; i < 9; i++){
            JButton casa = new JButton();
            botões[i] = casa;
            grid.add(casa);
            casa.setFocusable(false);
            //casa.setIcon(rato);
            casa.addActionListener(new ActionListener(){
                public void actionPerformed(ActionEvent ação){
                    JButton casa = (JButton) ação.getSource();
                    if (casa == casaRatoAtual){
                        pontuação += 10;
                        texto.setText("Recorde: " + Integer.toString(recorde) + " - Pontuação: " + Integer.toString(pontuação));
                    }
                    else if (casa == casaPlantaAtual){
                        texto.setText("GAME OVER: " + Integer.toString(pontuação));
                        setRatoTemp.stop();
                        setPlantaTemp.stop();
                        for (int i = 0; i < 9; i++){
                            botões[i].setEnabled(false);
                        }
                    }
                    
                }
            });
        }
        
        setRatoTemp = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent ação){
                if (casaRatoAtual != null){
                    casaRatoAtual.setIcon(null);
                    casaRatoAtual = null;
                }
                int num = rand.nextInt(9); //0-8
                JButton casa = botões[num]; 

                if (casaPlantaAtual == casa) return;

                casaRatoAtual = casa;
                casaRatoAtual.setIcon(rato);
            }
        });

        setPlantaTemp = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent ação){
                if (casaPlantaAtual != null){
                    casaPlantaAtual.setIcon(null);
                    casaPlantaAtual = null;
                }
                int num = rand.nextInt(9);
                JButton casa = botões[num];

                if (casaRatoAtual == casa) return;

                casaPlantaAtual = casa;
                casaPlantaAtual.setIcon(planta);
            }
        });

        setRatoTemp.start();
        setPlantaTemp.start();
        janela.setVisible(true);
    }
}
