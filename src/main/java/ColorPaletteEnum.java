import java.awt.*;

public enum ColorPaletteEnum {

    AZUL(100, 60, 250),
    AZULESCURO(53, 49, 101),
    ROSA(170, 60, 110),
    VERDE(143, 198, 113),
    WHITE(255, 255, 255);

    private Color color;
    ColorPaletteEnum(int r, int g, int b) {
        this.color = new Color(r, g, b);

        Color exemple = new Color(53, 49, 101);
    }

    public Color getColor() {
        return color;
    }
}
