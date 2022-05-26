public class RealEstate {
    private String name; // Название помещения
    private String metro; // Станция метро
    private String address; // Адрес
    private int rooms; // Кол-во комнат (для квартир)
    private int kind; // Тип помещения (для коммерческих помещений), для квартир 0
    private Float square; // Метраж помещения
    private Float price;// Цена
    private Float rating; //Средняя оценка

    public RealEstate() {
    }

    public RealEstate(String name, String metro, String address, int rooms, int kind, Float square, Float price, Float rating) {
        this.name = name;
        this.metro = metro;
        this.address = address;
        this.rooms = rooms;
        this.kind = kind;
        this.square = square;
        this.price = price;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMetro() {
        return metro;
    }

    public void setMetro(String metro) {
        this.metro = metro;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getRooms() {
        return rooms;
    }

    public void setRooms(int rooms) {
        this.rooms = rooms;
    }

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    public String decodeKind(int kind) {
        String result = "";
        if (kind == 0)
            result = "квартира";
        else if (kind == 1)
            result = "офис";
        else if (kind == 2)
            result = "свободного назначения";
        else if (kind == 3)
            result = "торговая площадь";
        else if (kind == 4)
            result = "склад";
        else if (kind == 5)
            result = "производство";
        else if (kind == 6)
            result = "общепит";
        else if (kind == 7)
            result = "гостиница";
        else if (kind == 8)
            result = "автосервис";
        else if (kind == 9)
            result = "здание целиком";
        return result;
    }

    public Float getSquare() {
        return square;
    }

    public void setSquare(Float square) {
        this.square = square;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "RealEstate{" +
                "name='" + name + '\'' +
                ", metro='" + metro + '\'' +
                ", address='" + address + '\'' +
                (kind == 0 ? ", rooms=" + rooms : "") +
                ", kind='" + decodeKind(kind) + '\'' +
                ", square=" + square +
                ", price=" + price +
                ", rating=" + rating +
                '}';
    }
}
