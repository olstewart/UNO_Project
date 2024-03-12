public class Card {
    private String color;
    private String type;
    private int draw;

    // Constructor
    public Card(String cardColor, String cardType, int cardDraw){
        color = cardColor;
        type = cardType;
        draw = cardDraw;
    }

    // Functions

    public String getColor() {
        return color;
    }
    public String getType() {
        return type;
    }
    public int getDraw() {
        return draw;
    }
    public void setColor(String newColor){
        color = newColor;
    }
    public void setCard(Card otherCard){
        color = otherCard.getColor();
        type = otherCard.getType();
        draw = getDraw();
    }
    public String printCard(){
        String cardText;
        if(color.equals("wild")){cardText = type;}
        else{cardText = color + " " + type;}
        return cardText;
    }
}
