package br.com.insurance.challenge.insurance.thread.view;

import br.com.insurance.challenge.insurance.thread.domain.ThreadQeue;
import br.com.insurance.challenge.insurance.thread.service.ThreadQueueImplementation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimeThreadScreen extends JDialog {

    private final JPanel jPanel = new JPanel(new GridBagLayout()); // Componentes do painel

    private final JLabel descriptionTime = new JLabel("Nome");
    private final JTextField showTime = new JTextField();

    private final JLabel descriptionTime2 = new JLabel("Email");
    private final JTextField showTime2 = new JTextField();

    private final JButton jButton = new JButton("Apolice");
    private final JButton jButton2 = new JButton("Stop");

    private ThreadQueueImplementation queu = new ThreadQueueImplementation();

    public TimeThreadScreen() {
        setTitle("Minha tela de time com Thread");
        setSize(new Dimension(240, 240));
        setLocationRelativeTo(null);
        setResizable(false);

        GridBagConstraints gridBagConstraints = new GridBagConstraints();//Gerenciador de componentes
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.gridwidth = 2;
        gridBagConstraints.insets = new Insets(5, 10, 5, 5);
        gridBagConstraints.anchor = GridBagConstraints.WEST;

        descriptionTime.setPreferredSize(new Dimension(200, 25));
        jPanel.add(descriptionTime, gridBagConstraints);

        showTime.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(showTime, gridBagConstraints);

        descriptionTime2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(descriptionTime2, gridBagConstraints);

        showTime2.setPreferredSize(new Dimension(200, 25));
        gridBagConstraints.gridy++;
        jPanel.add(showTime2, gridBagConstraints);

        gridBagConstraints.gridwidth = 1;

        jButton.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridy++;
        jPanel.add(jButton, gridBagConstraints);

        jButton2.setPreferredSize(new Dimension(92, 25));
        gridBagConstraints.gridx++;
        jPanel.add(jButton2, gridBagConstraints);

        jButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) { //Executa o clique no botão

                if (queu == null) {
                    queu = new ThreadQueueImplementation();
                    queu.start();
                }

                for (int qtd = 0; qtd < 10; qtd++) { //Simuando 10 envios em massa
                    ThreadQeue filaThread = new ThreadQeue();
                    filaThread.setName(showTime.getText());
                    filaThread.setEmail(showTime2.getText() + " = " + (qtd + 1));

                    ThreadQueueImplementation.add(filaThread);
                }
            }
        });

        jButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                queu.stop();
                queu = null;
            }
        });

        queu.start();
        add(jPanel, BorderLayout.WEST);
        setVisible(true);//Torna a tela visivel para o usuario
    }
}
