import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.io.IOException;

class Transforms2D extends JPanel {

    private class Display extends JPanel {
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2 = (Graphics2D) g;
            g2.translate(300, 300);  // Moves (0,0) to the center of the display.
            int whichTransform = transformSelect.getSelectedIndex();

            switch (whichTransform) {
                case 0:
                    g2.setPaint(Color.BLUE);
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                case 1:
                    g2.setPaint(Color.BLUE);
                    g2.scale(0.25, 0.25);
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                case 2:
                    g2.setPaint(Color.BLUE);
                    g2.rotate(Math.toRadians(45));
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                case 3:
                    g2.setPaint(Color.BLUE);
                    g2.rotate(Math.toRadians(180));
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                case 4:
                    AffineTransform p4 = new AffineTransform();
                    p4.translate(300, 300);
                    p4.shear(0, 0.5);
                    g2.setTransform(p4);

                    g2.setPaint(Color.BLUE);
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                case 5: {
                    AffineTransform p3 = new AffineTransform();
                    p3.translate(300, 150);
                    g2.setTransform(p3);

                    g2.setPaint(Color.BLUE);
                    g2.scale(1, 0.25);
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                }
                case 6: {
                    AffineTransform p3 = new AffineTransform();
                    p3.translate(300, 300);
                    p3.shear(0, 0.5);
                    g2.setTransform(p3);

                    g2.setPaint(Color.BLUE);
                    g2.rotate(Math.toRadians(180));
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                }
                case 7:
                    g2.setPaint(Color.BLUE);

                    g2.rotate(Math.toRadians(180), 1, -1);
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                case 8:

                    g2.setPaint(Color.BLUE);
                    g2.scale(1, 0.25);
                    g2.rotate(Math.toRadians(45));
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
                case 9:
                    AffineTransform p2 = new AffineTransform();
                    p2.translate(300, 300);
                    p2.shear(0, 0.5);
                    g2.setTransform(p2);

                    g2.setPaint(Color.BLUE);
                    g2.rotate(Math.toRadians(45 + 180));
                    g2.drawPolygon(polygon);
                    g2.fill(polygon);
                    break;
            }

        }
    }

    private Display display;
    private Polygon polygon = new Polygon();
    private JComboBox<String> transformSelect;

    private void drawPolygon(int anglesNumber, int radius) {
        for (int i = 0; i < anglesNumber; i++) {
            polygon.addPoint((int) Math.round(radius * Math.cos((Math.PI / 2 + 2 * Math.PI * i) / anglesNumber)),
                    (int) Math.round(radius * Math.sin((Math.PI / 2 + 2 * Math.PI * i) / anglesNumber)));
        }
    }

    private Transforms2D() {
        int anglesNumber = 9;
        int radius = 150;
        drawPolygon(anglesNumber, radius);
        display = new Display();
        display.setBackground(Color.YELLOW);
        display.setPreferredSize(new Dimension(600, 600));
        transformSelect = new JComboBox<>();
        transformSelect.addItem("None");
        for (int i = 1; i < 10; i++) {
            transformSelect.addItem("No. " + i);
        }
        transformSelect.addActionListener(e -> display.repaint());
        setLayout(new BorderLayout(3, 3));
        setBackground(Color.GRAY);
        setBorder(BorderFactory.createLineBorder(Color.GRAY, 10));
        JPanel top = new JPanel();
        top.setLayout(new FlowLayout(FlowLayout.CENTER));
        top.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));
        top.add(new JLabel("Transform: "));
        top.add(transformSelect);
        add(display, BorderLayout.CENTER);
        add(top, BorderLayout.NORTH);
    }


    static void main() {
        JFrame window = new JFrame("2D Transforms");
        window.setContentPane(new Transforms2D());
        window.pack();
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        window.setLocation((screen.width - window.getWidth()) / 2, (screen.height - window.getHeight()) / 2);
        window.setVisible(true);
    }

}