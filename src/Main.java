/** Проект №3 - Коллекции, лямбды и Stream API
 * Необходимо написать программу, которая будет выполнять некоторый функционал:
 * •	Добавить квартиру в список для аренды.
 * •	Удалить квартиру из списка для аренды.
 * •	Отсортировать квартиры по цене (по возрастанию) и вывести.
 * •	Отсортировать квартиры по цене (по убыванию) и вывести.
 * •	Сгруппировать квартиры по станции метро и вывести.
 * •	Выбрать лучшую квартиру по оценке и выве]сти.
 * •	Вывести изначальный список квартир для аренды.
 * Такой же функционал должен быть для типа «коммерческое помещение»
 * Общий функционал:
 * •	Удалить всё из списков.
 * •	Вывести сгруппированные списки. (Квартиры – список, Коммерческие помещение - список).
 * Поля классов:
 * 	Название помещения
 * 	Станция метро
 * 	Адрес
 * 	Кол-во комнат (для квартир)
 * 	Тип помещения (для коммерческих помещений)
 * 	Метраж помещения
 * 	Цена
 * 	Средняя оценка
 *
 * Вывод при группировке, сортировке необходимо соблюсти формат (Название – цена - оценка)
 */

import javax.print.attribute.standard.DateTimeAtCompleted;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Main
{
    public int typeRealEstate;
    public boolean isPrint0, isPrint1;


    public static void main(String[] args) {
        Main obj = new Main();
        obj.typeRealEstate = 0;

        List<RealEstate> realEstates = null;
        try {
            realEstates = loadFromCSV("data/data.csv"); //загрузка данных из csv файла
        } catch (IOException e) {
            e.printStackTrace();
        }
        //realEstates.forEach(System.out::println);
        Comparator<RealEstate> comparatorPrice = Comparator.comparing(RealEstate::getPrice);

        int menuSelect = 0;
        while (menuSelect != 10) {
            menuSelect = getMenu(obj);
            switch (menuSelect) {
                case 0: // Сменить тип недвижимости
                    obj.typeRealEstate = (obj.typeRealEstate == 0 ? 1 : 0); break;
                case 1: // Добавить недвижимость в список для аренды
                    {
                        inputRecord(obj, realEstates);
                        break;
                    }
                case 2: // Удалить недвижимость из списка для аренды
                    {
                        System.out.println("\nУдаление записи из списка\n");
                        for(int i = 0 ; i < realEstates.stream().count(); i++) {
                            if (Math.signum(realEstates.get(i).getKind()) == obj.typeRealEstate)
                                System.out.println((i+1) + " = " + realEstates.get(i).toString());
                        }
                        System.out.print("Введите номер записи для удаления (0-отмена удаления): ");
                        int index = getNum(0, (int) realEstates.stream().count());
                        if (index > 0)
                            realEstates.remove(index-1);
                        break;
                    }
                case 3: // Отсортировать недвижимость по цене (по возрастанию) и вывести
                    {
                        System.out.println("\nСортировка по возрастанию цены\n");
                        realEstates.stream()
                                .filter(realEstate -> Math.signum(realEstate.getKind()) == obj.typeRealEstate)
                                .sorted(comparatorPrice)
                                .forEach(System.out::println);
                        System.out.println();
                        break;
                    }
                case 4: // Отсортировать недвижимость по цене (по убыванию) и вывести
                    {
                        System.out.println("\nСортировка по по убыванию цены\n");
                        realEstates.stream()
                            .filter(realEstate -> Math.signum(realEstate.getKind()) == obj.typeRealEstate)
                            .sorted(comparatorPrice.reversed())
                            .forEach(System.out::println);
                        System.out.println();
                        break;
                    }
                case 5: // Сгруппировать недвижимость по станции метро и вывести
                    {
                        System.out.println("\nГруппировка недвижимость по станции метро\n");
                        Map<String , List<RealEstate>> groupingByMetro = null;
                        groupingByMetro = realEstates.stream()
                                .filter(realEstate -> Math.signum(realEstate.getKind()) == obj.typeRealEstate)
                                .sorted(comparatorPrice)
                                .collect(Collectors.groupingBy(RealEstate::getMetro));
                        groupingByMetro.forEach((metro, realEstate)-> {
                            System.out.println("Метро: " + metro);
                            realEstate.forEach(re -> System.out.println("\t" + re));
                        });
                        System.out.println();
                        break;
                    }
                case 6: // Выбрать лучшую недвижимость по оценке и вывести"
                    {
                        System.out.println("\nЛучшая недвижимость по оценке\n");
                        System.out.println(realEstates.stream()
                                .filter(realEstate -> Math.signum(realEstate.getKind()) == obj.typeRealEstate)
                                .max(Comparator.comparing(RealEstate::getRating)));
                        System.out.println();
                        break;}
                case 7: // Вывести изначальный список недвижимость для аренды
                    {
                        System.out.println("\nИзначальный список недвижимости\n");
                        realEstates.clear();
                        try {
                            realEstates = loadFromCSV("data/data.csv"); //загрузка данных из csv файла
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        realEstates.stream()
                            .filter(realEstate -> Math.signum(realEstate.getKind()) == obj.typeRealEstate)
                            .forEach(System.out::println);
                        System.out.println();
                        break;
                    }
                case 8: // Удалить всё из списков
                    {
                        realEstates.clear();
                        System.out.println("\nКол-во элементов в списках: " + realEstates.stream().count()+"\n");
                        break;
                    }
                case 9: // Вывести сгруппированные списки
                    {
                        obj.isPrint0 = false;
                        obj.isPrint1 = false;
                        System.out.println("\nCгруппированные списки (Квартиры, Коммерческие помещения)\n");
                        Map<Integer, List<RealEstate>> groupingByKind =
                                realEstates.stream()
                                        .collect(Collectors.groupingBy(RealEstate::getKind));
                        groupingByKind.forEach((kind, realEstate)-> {
                            if (kind == 0) {
                                if (!obj.isPrint0) {
                                    System.out.println("Квартиры");
                                    obj.isPrint0 = true;
                                }
                            } else {
                                if (!obj.isPrint1) {
                                    System.out.println("Коммерческие помещения");
                                    obj.isPrint1 = true;
                                }
                            }
                            realEstate.forEach(re -> System.out.println("\t" + re));
                        });
                        System.out.println();
                        break;
                    }
                case 10: return;
                default: System.out.println("Неправильно выбран пункт меню. Повторите ввод"); break;
            }
        }

    }

    public static void printMenu(Main o) {
        String[] menu = {
            "0. Сменить тип недвижимости. Выбран тип - " + (o.typeRealEstate == 0 ? "квартира" : "коммерческое помещение"),
            "1. Добавить недвижимость в список для аренды",
            "2. Удалить недвижимость из списка для аренды",
            "3. Отсортировать недвижимость по цене (по возрастанию) и вывести",
            "4. Отсортировать недвижимость по цене (по убыванию) и вывести",
            "5. Сгруппировать недвижимость по станции метро и вывести",
            "6. Выбрать лучшую недвижимость по оценке и вывести",
            "7. Вывести изначальный список недвижимость для аренды",
            "8. Удалить всё из списков",
            "9. Вывести сгруппированные списки",
            "10. Выход"
        };
        for (int i = 0; i < menu.length; i++) {
            System.out.println(menu[i]);
        }
    }

    public static int getMenu(Main o) {
        printMenu(o);
        try {
            System.out.print("Выберите пункт меню цифрой [0-10]: ");
            return new Scanner(System.in).nextInt();
        } catch (Exception e) {
            return -1;
        }
    }

    public static int getNum(int lower, int upper) {
        Scanner sc = new Scanner(System.in);
        int result = -1;
        //boolean loop = true;
        //while (loop) {
            try {
                result = sc.nextInt();
                if (result < lower || result > upper) {
                    throw new NumberFormatException();
                }
        //        loop = false;
            } catch (Exception e) {
                System.out.println("Повторите ввод. Должно быть число от " + lower + " до " + upper + ".");
            }
        //}
        return result;
    }

    public static ArrayList<RealEstate> loadFromCSV(String filename) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(filename));

        // считываем построчно
        String line = null;
        Scanner sc = null;
        int index = 0;
        List<RealEstate> realEstates = new ArrayList<>();
        // считываем заголовок
        line = reader.readLine();
        // считываем данные
        while ((line = reader.readLine()) != null) {
            RealEstate realEstate = new RealEstate();
            sc = new Scanner(line);
            sc.useDelimiter(";");
            while (sc.hasNext()) {
                String data = sc.next();
                if (data.length() > 0) {
                    if (index == 0)
                        realEstate.setName(data);
                    else if (index == 1)
                        realEstate.setMetro(data);
                    else if (index == 2)
                        realEstate.setAddress(data);
                    else if (index == 3)
                        realEstate.setRooms(Integer.parseInt(data));
                    else if (index == 4)
                        realEstate.setKind(Integer.parseInt(data));
                    else if (index == 5)
                        realEstate.setSquare(Float.parseFloat(data));
                    else if (index == 6)
                        realEstate.setPrice(Float.parseFloat(data));
                    else if (index == 7)
                        realEstate.setRating(Float.parseFloat(data));
                    else
                        System.out.println("Некорректные данные для [" + index + "]: = <" + data + ">");
                }
                index++;
            }
            index = 0;
            realEstates.add(realEstate);
        }
        reader.close();
        return (ArrayList<RealEstate>) realEstates;
    }

    public static void inputRecord(Main o, List<RealEstate> res) {
        RealEstate realEstate = new RealEstate();
        System.out.println("\nВведите данные для добавления записи\n");
        Scanner sc = new Scanner(System.in);
        //String data = "";

        try {
            System.out.print("Название помещения: ");
            realEstate.setName(sc.nextLine());

            System.out.print("Станция метро: ");
            realEstate.setMetro(sc.nextLine());

            System.out.print("Адрес: ");
            realEstate.setAddress(sc.nextLine());

            if (o.typeRealEstate == 0) {
                System.out.print("Кол-во комнат (для квартир): ");
                realEstate.setRooms(sc.nextInt());
            }

            if (o.typeRealEstate == 0) {
                realEstate.setKind(0);
            } else {
                System.out.println("Тип помещения (введите цифру от 1 до 9)");
                System.out.print(
                    "1-офис, " +
                    "2-свободного назначения, " +
                    "3-торговая площадь, " +
                    "4-склад, " +
                    "5-производство, " +
                    "6-общепит, " +
                    "7-гостиница, " +
                    "8-автосервис, " +
                    "9-здание целиком: ");
                realEstate.setKind(sc.nextInt());
            }

            System.out.print("Метраж помещения: ");
            realEstate.setSquare(sc.nextFloat());

            System.out.print("Цена: ");
            realEstate.setPrice(sc.nextFloat());

            System.out.print("Средняя оценка: ");
            realEstate.setRating(sc.nextFloat());

            if (realEstate.getMetro() == null || realEstate.getMetro().length() == 0)
                realEstate.setMetro("неизвестно");
            res.add(realEstate);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка ввода данных. Повторите ввод, выбрать пункт добавления записи.\n");
            System.out.println("Нажмите <ENTER> для продожения.\n");
            Scanner sc2 = new Scanner(System.in);
            String s = sc2.nextLine();
        }

    }

}
