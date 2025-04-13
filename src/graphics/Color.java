package graphics;

public enum Color {
    ROJO(255f, 0f, 0f),
    VERDE(0f, 255f, 0f),
    AZUL(0f, 0f, 255f),
    NEGRO(0f, 0f, 0f),
    BLANCO(255f, 255f, 255f),
    AMARILLO(255f, 255f, 0f),
    CIAN(0f, 255f, 255f),
    MAGENTA(255f, 0f, 255f),
    GRIS(128f, 128f, 128f),
    NARANJA(255f, 165f, 0f);

    public float r, g, b;

    Color(float r, float g, float b) {
        this.r = r;
        this.g = g;
        this.b = b;
    }
}
