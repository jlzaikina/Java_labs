import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        SeriesI[] db = null;

        Scanner scan = new Scanner(System.in);
        String menu, str;
        int length;

        Boolean key = false;
//пользовательское меню(рисунок1)
        while (key == false)
        {
            System.out.print("Меню\n" +
                    "" +
                    "\n" +
                    "1) создать базу\n" +
                    "2) задание элемента базы\n" +
                    "3) создать и заполнить базу автоматически\n" +
                    "4) вывести полную информацию базы\n" +
                    "5) найти в базе объекты, функциональный метод которых возвращает одинаковый результат,\n" +
                    "   поместить такие объекты в другой массив\n" +
                    "6) разбить исходный массив на два массива, в которых будут храниться однотипные элементы\n" +
                    "0) выйти\n\n" +
                    "Выберите пункт меню: ");
            menu = scan.nextLine();
            switch (menu) {
                case "0":{
                    key = true;
                    break;
                }
//создание базы, заданного размера(рисунок 1)
                case "1": {
                    System.out.print("Введите размера базы: ");
                    str = scan.nextLine();
                    try {
                        length = Integer.parseInt(str);
                        if (length <= 0) {
                            System.out.print("Размер базы должен быть больше 0. По умолчанию будет создана база из 5 элементов.\n");
                            length = 5;
                        }
                        db = new SeriesI[length];
                        System.out.print("База данных создана.\n");
                    } catch (NumberFormatException e){
                        System.out.println("Неверный формат");
                    }
                    break;
                }
//Ввод элементов базы данных, заданных типов, и заполнение информации о них(рисунок 2)
                case "2": {
                    try {
                        if (db == null) {
                            System.out.println("Не существует.\n");
                        } else {
                            System.out.print("Введите индекс элемента, который хотите изменить: ");
                            str = scan.nextLine();
                            if (!str.matches("-?\\d+")) {
// одно из необъявляемых исключений(рисунок 10)

                                throw new NumberFormatException();
                            }
                            int index = (Integer.parseInt(str) - 1);
                            System.out.print("Задайте элемент с индексом " + (index + 1) + "\n: ");
                            do {
                                System.out.print("Выберите тип элемента:\n" +
                                        "1. SeriesCollection - Сборник сериалов\n" +
                                        "2. SeriesCartoon - Коллекция мультфильмов\n" +
                                        "Введите номер типа элемента: ");
                                str = scan.nextLine();
                                System.out.println();
                                if (str.equals("1")) {
                                    System.out.print("Введите название сериала: ");
                                    String title = scan.nextLine();
                                    System.out.print("Введите количество сезонов в сериале: ");
                                    str = scan.nextLine();
                                    if (!str.matches("-?\\d+")) {
                                        throw new NumberFormatException();
                                    }
                                    int amountSeason = Integer.parseInt(str);
                                    if (amountSeason <= 0) {
                                        System.out.print("Количество сезонов должно быть больше 0. По умолчанию количество сезонов будет 1.\n");
                                        amountSeason = 1;
                                    }
                                    System.out.print("Введите количество серий, где нет главного героя: ");
                                    str = scan.nextLine();
                                    if (!str.matches("-?\\d+")) {
                                        throw new NumberFormatException();
                                    }
                                    int amountRole = Integer.parseInt(str);
                                    if (amountRole <= 0) {
                                        System.out.print("Количество количество серий, где нет главного героя, должно быть больше 0. По умолчанию количество серий будет 1.\n");
                                        amountRole = 1;
                                    }
                                    try {
                                        db[index] = new SeriesCollection(amountSeason, title, amountRole);
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        System.out.println("Неверный индекс");
                                        break;
                                    }

                                    System.out.println("Сборник сериалов успешно создан.");
                                    System.out.print("Заполните сборник сериалов названиями сезонов и их количеством сериалов.\n");
                                    for (int i = 0; i < db[index].getAmountOfSeason(); i++) {
                                        System.out.print("Сезон с индексом  " + (i + 1) + '\n');
                                        System.out.print("Введите название: ");
                                        String gameTitle = scan.nextLine();
                                        db[index].setSeason(gameTitle, i);

                                        System.out.print("Введите количество серий: ");
                                        str = scan.nextLine();
                                        if (!str.matches("-?\\d+")) {
                                            throw new NumberFormatException();
                                        }
                                        int amountSeries = Integer.parseInt(str);
                                        if (amountSeries <= 1) {
                                            System.out.print("Количество серий в сезоне  должно быть больше 1. По умолчанию количество серий будет 2.\n");
                                            amountSeries = 2;
                                        }
                                        db[index].setSeries(i, amountSeries);
                                    }
                                    break;
                                } else if (str.equals("2")) {
                                    System.out.print("Введите название коллекции: ");
                                    String title1 = scan.nextLine();
                                    System.out.print("Введите количество сезонов в коллекции: ");
                                    str = scan.nextLine();
                                    if (!str.matches("-?\\d+")) {
                                        throw new NumberFormatException();
                                    }
                                    int amountSeries1 = Integer.parseInt(str);
                                    if (amountSeries1 <= 0) {
                                        System.out.print("Количество сезонов в коллекции должно быть больше 0. По умолчанию количество сезонов будет 1.\n");
                                        amountSeries1 = 1;
                                    }
                                    System.out.print("Введите количество серий, где нет главного героя: ");
                                    str = scan.nextLine();
                                    if (!str.matches("-?\\d+")) {
                                        throw new NumberFormatException();
                                    }
                                    int amountRole1 = Integer.parseInt(str);
                                    if (amountRole1 <= 0) {
                                        System.out.print("Количество серий, где нет главного героя должно быть больше 0. По умолчанию количество серий будет 1.\n");
                                        amountRole1 = 1;
                                    }
                                    try {
                                        db[index] = new SeriesCartoon(amountSeries1, title1, amountRole1);
                                    } catch (ArrayIndexOutOfBoundsException e) {
                                        System.out.println("Неверный индекс");
                                        break;
                                    }
                                    System.out.println("Коллекция мультфильмов успешно создан.");
                                    System.out.print("Заполните коллекцию названиями сезонов и их количеством серий.\n");
                                    for (int i = 0; i < db[index].getAmountOfSeason(); i++) {
                                        System.out.print("Сезон с индексом  " + (i + 1) + '\n');
                                        System.out.print("Введите название: ");
                                        String gameTitle = scan.nextLine();
                                        db[index].setSeason(gameTitle, i);

                                        System.out.print("Введите количество серий: ");
                                        str = scan.nextLine();
                                        if (!str.matches("-?\\d+")) {
                                            throw new NumberFormatException();
                                        }
                                        int amountOfMissions = Integer.parseInt(str);
                                        if (amountOfMissions <= 1) {
                                            System.out.print("Количество серий в сезоне должно быть больше 1. По умолчанию количество серий будет 2.\n");
                                            amountOfMissions = 2;
                                        }
                                        db[index].setSeries(i, amountOfMissions);
                                    }
                                    break;
                                } else {
                                    System.out.println("Выбран неверный пункт меню");
                                }
                            } while (true);

                            for (int i = 0; i < db.length; i++) {
                                System.out.print((i + 1) + ") ");
                                if (db[i] == null) {
                                    System.out.println("Элемент не задан");
                                } else {
                                    System.out.println('-' + db[i].getTitle() + '-');
                                }
                            }
                        }
                        System.out.println();
                    }catch(NumberFormatException e){
                        System.out.println("Неверный формат");
                    }
                    break;
                }
//создание и заполнение базы автоматически(рисунок 3)
                case "3": {
                    db = createAndFillDbAutomatically();
                    System.out.println("База создана и заполнена автоматически.");
                    break;
                }
//Вывод базы данных на консоль(рисунок 4-5)
                case "4": {
                    System.out.print("База данных: ");
                    if (db == null) {
                        System.out.println("Не существует.\n");
                    } else {
                        System.out.println();
                        for (int i = 0; i < db.length; i++) {
                            System.out.print((i+1) + ") ");
                            if (db[i] == null) {
                                System.out.println("Элемента не существует");
                            } else {
                                System.out.println('-' + db[i].getTitle() + '-');
                                System.out.println(db[i]);
                            }
                            System.out.println();
                        }
                    }
                    break;
                }
//Вывод элементов базы данных на консоль, функциональный метод которых возвращает одинаковый результат(рисунок 6)
                case "5": {

                    if (db == null) {
                        System.out.print("База данных не существует ");
                    } else if (db.length == 1) {
                        System.out.println(db[0]);
                    } else {
                        int sameElement = 0;
                        for (int i = 0; i < db.length; i++) {
                            for (int j = i + 1; j < db.length; j++) {
                                try{
                                    if (db[i].getAmountOfSeriesWithoutMainRole() == db[j].getAmountOfSeriesWithoutMainRole()) {
                                        sameElement++;
                                    }
// отлов объявляемого исключения (рисунок 9)
                                } catch (AmountOfSeriesException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        if (sameElement > 0) {
                            ArrayList<ArrayList<SeriesI>> arrayList = new ArrayList<ArrayList<SeriesI>>();
                            ArrayList<SeriesI> arr = new ArrayList<SeriesI>();
                            for (int i = 0; i < db.length; i++) {
                                for (int j = i + 1; j < db.length; j++) {
                                    try {
                                        if (db[i].getAmountOfSeriesWithoutMainRole() == db[j].getAmountOfSeriesWithoutMainRole()) {
                                            if(!arr.contains(db[i])){arr.add(db[i]);}
                                            if(!arr.contains(db[j])){arr.add(db[j]);}
                                        }
                                    } catch (AmountOfSeriesException e) {
                                        e.printStackTrace();
                                    }
                                }
                                if(arrayList.size() < sameElement) {
                                    arrayList.add(arr);
                                }
                            }

                            System.out.println("База успешно разделена");
                            System.out.println("База данных: ");
                            for(int j = 0; j < arrayList.size();j++) {
                                for (int i = 0; i < arr.size(); i++) {
                                    System.out.print("[" + (j+1) + "." + (i+1) + "] ");
                                    System.out.println('-' + arrayList.get(j).get(i).getTitle() + '-');
                                    System.out.println();
                                    System.out.println(arrayList.get(j).get(i));
                                    System.out.println();
                                }
                            }
                        } else
                            System.out.println("В базе нет объектов, функциональный метод которых возвращает одинаковый результат\n");

                    }
                    break;
                }
//Разделение базы данных по типам элементов(рисунок 7-8)
                case "6": {
                    SeriesI[] arrSeries, arrCollections;
                    if (db == null) {
                        System.out.print("База данных не существует ");
                    } else {

                        int countSeries = 0;
                        int countCollections = 0;
                        for (int i = 0; i < db.length; i++) {
                            if (db[i] instanceof SeriesCollection) {
                                countSeries++;
                            }
                            if (db[i] instanceof SeriesCartoon) {
                                countCollections++;
                            }
                        }
                        if (countSeries > 0) {
                            arrSeries = new SeriesCollection[countSeries];
                            int j = 0;
                            for (int i = 0; i < db.length; i++) {
                                if (db[i] instanceof SeriesCollection && j < arrSeries.length) {
                                    arrSeries[j] = db[i];
                                    j++;
                                }
                            }
                            System.out.println("Сформирован список сборников сериалов");
                            System.out.println("Список сборников сериалов: ");
                            for (int i = 0; i < arrSeries.length; i++) {
                                System.out.print((i+1)+") ");
                                System.out.println('-' + arrSeries[i].getTitle() + '-');
                                System.out.println();
                                System.out.println(arrSeries[i]);
                                System.out.println();
                            }
                        }
                        if (countCollections > 0) {
                            arrCollections = new SeriesCartoon[countCollections];
                            int j = 0;
                            for (int i = 0; i < db.length; i++) {
                                if (db[i] instanceof SeriesCartoon && j < arrCollections.length) {
                                    arrCollections[j] = db[i];
                                    j++;
                                }
                            }
                            System.out.println("Сформирован список коллекций мультфильмов");
                            System.out.println("Список коллекций мультфильмов: ");
                            for (int i = 0; i < arrCollections.length; i++) {
                                System.out.print((i+1)+") ");
                                System.out.println('-' + arrCollections[i].getTitle() + '-');
                                System.out.println();
                                System.out.println(arrCollections[i]);
                                System.out.println();
                            }
                        }
                        System.out.println();

                    }
                    break;
                }
                default:
                    System.out.println("Выбран неверный пункт меню");
                    break;
            }
            System.out.println();
        }
    }
    //Заготовка базы данных для автоматического создания
    public static SeriesI[] createAndFillDbAutomatically() {
        int length = 5;
        SeriesI[] autoDb = new SeriesI[length];
        autoDb[0] = new SeriesCartoon(3,"Доктор Хаус",  3);
        autoDb[0].setSeason( "Все лгут", 0);
        autoDb[0].setSeries(0, 22);
        autoDb[0].setSeason("Смирение", 1);
        autoDb[0].setSeries(1, 24);
        autoDb[0].setSeason("Смысл", 2);
        autoDb[0].setSeries(2, 24);

        autoDb[1] = new SeriesCollection(4, "Друзья", 2);
        autoDb[1].setSeason("The One Where Monica Gets a Roommate", 0);
        autoDb[1].setSeries(0, 24);
        autoDb[1].setSeason("The One with Ross's New Girlfriend", 1);
        autoDb[1].setSeries(1, 24);
        autoDb[1].setSeason("The One with the Princess Leia Fantasy", 2);
        autoDb[1].setSeries(2, 24);
        autoDb[1].setSeason("The One with the Jellyfish", 3);
        autoDb[1].setSeries(3, 25);

        autoDb[2] = new SeriesCollection(3, "Ривердэйл", 3);
        autoDb[2].setSeason("Глава первая: У края реки", 0);
        autoDb[2].setSeries(0, 22);
        autoDb[2].setSeason("Глава вторая: Славное будущее", 1);
        autoDb[2].setSeries(1, 24);
        autoDb[2].setSeason("Глава третья: День труда", 2);
        autoDb[2].setSeries(2, 24);

        autoDb[3] = new SeriesCartoon(3, "Кухня", 5);
        autoDb[3].setSeason("Кухня", 0);
        autoDb[3].setSeries(0, 20);
        autoDb[3].setSeason("Отель Гранд", 1);
        autoDb[3].setSeries(1, 20);
        autoDb[3].setSeason("Гранд Лион", 2);
        autoDb[3].setSeries(2, 22);

        autoDb[4] = new SeriesCollection(3,"Великолепный век", 4);
        autoDb[4].setSeason("Сезон 1", 0);
        autoDb[4].setSeries(0, 24);
        autoDb[4].setSeason("Сезон 2", 1);
        autoDb[4].setSeries(1, 39);
        autoDb[4].setSeason("Сезон 3", 2);
        autoDb[4].setSeries(2, 40);

        return autoDb;
    }
}
