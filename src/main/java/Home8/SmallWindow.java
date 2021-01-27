package Home8;


import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SmallWindow extends JFrame {

    private final BigWindow bigWindow;
    private static final int WIN_HEIGHT = 400;
    private static final int WIN_WIDTH = 400;
    private static final int MIN_WIN_LEN = 3;
    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_WIN_LEN = 4;
    private static final int MAX_FIELD_SIZE = 5;
    private static final String STR_WIN_LEN = "Победа длинною в : ";
    private static final String STR_FILED_SIZE = "Размер поля: ";

    private ButtonGroup gameMode = new ButtonGroup();

    private JSlider slFieldSize;
    private JSlider slWinLeght;


    public SmallWindow(BigWindow bigWindow) {
        this.bigWindow= bigWindow;
        setTitle("Параметры новой игры");
        setSize(WIN_WIDTH, WIN_HEIGHT);
        setLocationRelativeTo ( null );

        Rectangle bigWindowBounds = bigWindow.getBounds();
        int posX = (int) (bigWindowBounds.getCenterX() - WIN_WIDTH/2);
        int posY = (int) (bigWindowBounds.getCenterY() - WIN_HEIGHT/2);

        setLocation(posX, posY);
        setLayout(new GridLayout(10,1));

        addGameControlsFieldWinLen();

        JButton btnStartGame = new JButton("Начать игру");
        btnStartGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                btnStartGame();
            }
        });
        add(btnStartGame);
    }

    void btnStartGame() {
        int gameMode=0;

        int fieldSize = slFieldSize.getValue();
        int winLen = slWinLeght.getValue();
        bigWindow.startNewGame(gameMode, fieldSize, fieldSize, winLen);
        setVisible(false);
    }

    void addGameControlsFieldWinLen() {
        add(new JLabel("Выбрать размер игрового поля"));
        final JLabel lblFieldSize = new JLabel(STR_FILED_SIZE + MIN_FIELD_SIZE);
        add(lblFieldSize);

        slFieldSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_WIN_LEN);
        slFieldSize.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                int currentFieldSize = slFieldSize.getValue();
                lblFieldSize.setText(STR_FILED_SIZE + currentFieldSize);
                slWinLeght.setMaximum(currentFieldSize);
            }
        });
        add(slFieldSize);

        add(new JLabel("Выбрать длину победы: "));
        final JLabel lblWinLen = new JLabel(STR_WIN_LEN + MIN_WIN_LEN);
        add(lblWinLen);

        slWinLeght = new JSlider(MIN_WIN_LEN, MAX_WIN_LEN, MIN_WIN_LEN);
        slWinLeght.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                lblWinLen.setText(STR_WIN_LEN + slWinLeght.getValue());
            }
        });
        add(slWinLeght);
    }
}
