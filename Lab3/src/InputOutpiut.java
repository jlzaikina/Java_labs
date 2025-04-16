import java.io.*;
import java.util.Scanner;

public class InputOutpiut {
    public static void outputSeriesI(SeriesI o, OutputStream out) {
        o.output(out);
    }
    // статический метод чтения из байтового потока
    public static SeriesI inputSeriesI(InputStream in) {
        SeriesI o;
        DataInputStream inputStream = new DataInputStream(in);

        try {
            String className = inputStream.readUTF();
            String title = inputStream.readUTF();
            int amountOfSeazon = inputStream.readInt();
            int amountOfMainRole = inputStream.readInt();

            if (className.equals(SeriesCollection.class.getName())) {
                o = new SeriesCollection(amountOfSeazon, title, amountOfMainRole);
            } else if (className.equals(SeriesCartoon.class.getName())) {
                o = new SeriesCartoon(amountOfSeazon, title, amountOfMainRole);
            } else o = null;
            if (o != null) {
                final int len = o.getAmountOfSeason();
                String seazon;
                for (int i = 0; i < len; i++) {
                    seazon = inputStream.readUTF();
                    int amountOfSeries = inputStream.readInt();

                    o.setSeason(seazon, i);
                    o.setSeries(i, amountOfSeries);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            o = null;
        }
        return o;
    }
    // статический метод записи в символьный поток – делегирует вызов соответствующему методу интерфейса
    public static void writeSeriesI(SeriesI o, Writer out) {
        o.write(out);
    }
    // статический метод чтения из символьного потока
    public static SeriesI readSeriesI(BufferedReader reader){
        SeriesI o = null;
        String line;
        String[] words;
        try {
            if ((line = reader.readLine()) != null) {
                words = line.split("  ");
                String className = words[0];
                String title = words[1];
                int amountOfSeazon = Integer.parseInt(words[2]);
                int amountOfMainRole = Integer.parseInt(words[3]);
                if (className.equals(SeriesCollection.class.getName())) {
                    o =  new SeriesCollection(amountOfSeazon, title, amountOfMainRole);
                } else if (className.equals(SeriesCartoon.class.getName())) {
                    o = new SeriesCartoon(amountOfSeazon, title, amountOfMainRole);
                } else o = null;
                if (o != null) {
                    final int len = o.getAmountOfSeason();
                    String seazon;
                    int startIndex = 4;
                    for (int i = 0; i < len; i++) {
                        seazon = words[startIndex+i*2];
                        int amountOfSeries = Integer.parseInt(words[startIndex+i*2+1]);
                        o.setSeason(seazon, i);
                        o.setSeries(i, amountOfSeries);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            o = null;
        }
        return o;
    }
    // вывод сериализованных объектов
    public static void serializeSeriesI (SeriesI o, OutputStream out){
        ObjectOutputStream serializer;
        try {
            serializer = new ObjectOutputStream(out);
            serializer.writeObject(o);
            serializer.flush();
        } catch (IOException exc) {
            System.out.println(exc.getMessage());
        }
    }
    // ввод десериализованных объектов
    public static SeriesI deserializeSeriesI(InputStream in){
        SeriesI o;
        ObjectInputStream deserializer;
        try {
            deserializer = new ObjectInputStream(in);
            o = (SeriesI) deserializer.readObject();
        } catch (IOException | ClassNotFoundException exc) {
            System.out.println(exc.getMessage());
            o = null;
        }
        return o;
    }
    // форматный вывод
    public static void writeFormatSeriesI(SeriesI[] o, Writer out)
    {
        PrintWriter printWriter = new PrintWriter(out);
        for(int i = 0; i < o.length; i++)
        {
            String title = o[i].getTitle();
            int seazon = o[i].getAmountOfSeason();
            int mainRole = o[i].getMainRole();
            String cl = o[i].getClass().getName();
            printWriter.printf("Класс: %s\n Название:%s\n Сезоны: %d\n Без главных: %d\n", cl, title, seazon, mainRole);
            for (int j = 0; j < o[i].getAmountOfSeason(); j++)
            {
                String series = o[i].getSeason(j);
                int temp = o[i].getSeries(j);
                printWriter.printf("Сезон: %s\n Серии: %d\n", series, temp);
            }
        }
    }
    // форматный ввод
    public static SeriesI readFormatSeriesI(Scanner in) {
        SeriesI o = null;
        if(in.hasNext()) {
            in.next();
            String className = in.findInLine("Se....");
            className+=in.nextLine();
            in.next();
            String title = in.nextLine();
            in.next();
            int amountOfSeazon = in.nextInt();
            in.next();
            in.next();
            int amountOfMainRole = in.nextInt();
            if (className.equals(SeriesCollection.class.getName())) {
                o = new SeriesCollection(amountOfSeazon, title, amountOfMainRole);
            } else if (className.equals(SeriesCartoon.class.getName())) {
                o = new SeriesCartoon(amountOfSeazon, title, amountOfMainRole);
            } else o = null;
            if (o != null) {
                final int len = o.getAmountOfSeason();
                String seazon;
                for (int i = 0; i < len; i++) {
                    in.nextLine();
                    in.next();
                    seazon = in.nextLine();
                    in.next();
                    int amountOfSeries = in.nextInt();
                    o.setSeason(seazon, i);
                    o.setSeries(i, amountOfSeries);
                }
            }
        }
        return o;
    }
}
