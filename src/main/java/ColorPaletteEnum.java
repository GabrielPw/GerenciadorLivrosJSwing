import java.awt.*;

public enum ColorPaletteEnum {

    AZUL(100, 79, 185),
    AZULESCURO(53, 49, 101),
    ROSA(170, 60, 110),
    VERDE(143, 198, 113),
    VERDEESCURO(103, 155, 72),
    VERMELHO(213, 31, 54),
    VERMELHOESCURO(161, 30, 51),
    WHITE(255, 255, 255);

    private Color color;

    ColorPaletteEnum(int r, int g, int b) {
        this.color = new Color(r, g, b);

        Color exemple = new Color(161, 30, 51);
    }

    public Color getColor() {
        return color;
    }


}

