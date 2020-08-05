package example;

public enum Colors {

    ONE_COLOR("Одноцветные"),
    SMOKY("Дымчатые"),
    VEILED("Завуалированные"),
    SHADED("Затушеванные"),
    CHINCHILLA("Шиншилловые"),
    TABBY("Табби"),
    COLOR_POINT("Колор-пойнт"),
    TORTOISESHELL("Черепаховые");

    private String translation;

    Colors(String translation){
        this.translation = translation;
    }

    String getTranslation(){
        return translation;
    }

    @Override
    public String toString(){
        return translation;
    }

}
