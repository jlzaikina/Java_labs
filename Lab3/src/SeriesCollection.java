import java.io.*;

public class SeriesCollection implements SeriesI {
    private String nameOfSitcom; //поле строкового типа – название сериала
    private String[] seasonInSitcom; //строковый массив – сезоны сериала
    private int[] amountSeriesInSeason; //поле - массив – количество сериалов
    private int mainRoleInSeason; //поле целого типа – количество серий без главного героя

    public SeriesCollection()         //конструктор без параметров
    {
        this.seasonInSitcom = new String[1];
        this.amountSeriesInSeason = new int[this.seasonInSitcom.length];
        this.nameOfSitcom = "Название отстуствует";
        this.mainRoleInSeason = 1;
    }

    public SeriesCollection(int amountSeries, String nameOfSitcom, int mainRoleInSitcom) //конструктор с параметрами
    {
        this.amountSeriesInSeason = new int[amountSeries];
        this.nameOfSitcom = nameOfSitcom;
        this.mainRoleInSeason = mainRoleInSitcom;
        this.seasonInSitcom = new String[this.amountSeriesInSeason.length];
    }

    //доступ к полям класс
    public String getTitle() {
        return nameOfSitcom;
    }

    public void setTitle(String title) {
        nameOfSitcom = title;
    }

    public String getSeason(int i) {
        if (i >= 0 && i < seasonInSitcom.length)
            return seasonInSitcom[i];
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }

    public void setSeason(String season, int i) {
        if (i >= 0 && i < seasonInSitcom.length)
            seasonInSitcom[i] = season;
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }

    public int getAmountOfSeries() {
        return amountSeriesInSeason.length;
    }

    public int getSeries(int i) {
        if (i >= 0 && i < seasonInSitcom.length)
            return amountSeriesInSeason[i];
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }

    public void setSeries(int i, int amount) {
        if (i >= 0 && i < seasonInSitcom.length)
            amountSeriesInSeason[i] = amount;
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }

    public int getMainRole() {
        return mainRoleInSeason;
    }

    public void setMainRole(int amount) {
        mainRoleInSeason = amount;
    }

    public int getAmountOfSeason() {
        return seasonInSitcom.length;
    }

    //функциональный метод
    public int getAmountOfSeriesWithoutMainRole() throws AmountOfSeriesException {
        int sum = 0;

        for (int i = 0; i < amountSeriesInSeason.length; ++i) {
            if (amountSeriesInSeason[i] < mainRoleInSeason) {
                throw new AmountOfSeriesException("Ошибка. Общее количество серий не может быть меньше количествасерий без главного героя");
            }
            sum += amountSeriesInSeason[i];
        }

        return sum - mainRoleInSeason * amountSeriesInSeason.length;
    }

    //переопределение toString
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название сериала: ").append(nameOfSitcom).append('\n');
        stringBuilder.append("Количество сезонов в сериале: ").append(seasonInSitcom.length).append('\n');
        try {
            stringBuilder.append("Общее количество серий без учета серий без главного героя: ").append(getAmountOfSeriesWithoutMainRole()).append('\n');
            stringBuilder.append("Количество серий без главного героя: ").append(mainRoleInSeason).append('\n');
            stringBuilder.append("Информация о сериях:\n");

            for (int i = 0; i < seasonInSitcom.length; ++i) {
                stringBuilder.append((i + 1)).append(") Название: ").append(seasonInSitcom[i]).append(". Количество серий: ").append(amountSeriesInSeason[i]).append(".").append("\n");
            }
        } catch (AmountOfSeriesException var) {
            stringBuilder.append(var.getMessage()).append('\n');
        }
        return stringBuilder.toString();
    }

    //переопределение equals
    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            SeriesCollection newSeriesCollection = (SeriesCollection) o;
            boolean equals = false;
            if (!nameOfSitcom.equals(newSeriesCollection.getTitle())) {
                return false;
            } else if (mainRoleInSeason != newSeriesCollection.getMainRole()) {
                return false;
            } else {
                int i;
                for (i = 0; seasonInSitcom[i].equals(newSeriesCollection.getSeason(i)); ++i) {
                }

                if (i != seasonInSitcom.length) {
                    return false;
                } else {
                    int k;
                    for (k = 0; amountSeriesInSeason[k] == newSeriesCollection.getSeries(k); ++k) {
                    }

                    if (k == amountSeriesInSeason.length) {
                        equals = true;
                    }
                    return equals;
                }
            }
        } else {
            return false;
        }
    }

    // переопределение hashCode
    public int hashCode() {
        return super.hashCode();
    }

    // реализация метода записи в байтовый поток
    public void output(OutputStream out) {
        DataOutputStream outputStream = new DataOutputStream(out);
        try {
            outputStream.writeUTF(getClass().getName());
            outputStream.writeUTF(nameOfSitcom);
            outputStream.writeInt(seasonInSitcom.length);
            outputStream.writeInt(mainRoleInSeason);

            for (int i = 0; i < seasonInSitcom.length; i++) {
                outputStream.writeUTF(seasonInSitcom[i]);
                outputStream.writeInt(amountSeriesInSeason[i]);
            }
            outputStream.flush();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    // реализация метода записи в символьный поток
    public void write(Writer out) {
        PrintWriter printWriter = new PrintWriter(out);
        printWriter.print(getClass().getName());
        printWriter.print(" ");
        printWriter.print(nameOfSitcom);
        printWriter.print(" ");
        printWriter.print(seasonInSitcom.length);
        printWriter.print(" ");
        printWriter.print(" ");
        printWriter.print(mainRoleInSeason);
        printWriter.print(" ");

        for (int i = 0; i < seasonInSitcom.length; i++) {
            printWriter.print(seasonInSitcom[i]);
            printWriter.print(" ");
            printWriter.print(amountSeriesInSeason[i]);
            if (i + 1 != seasonInSitcom.length) {
                printWriter.print(" ");
            }
        }
        printWriter.println();
        printWriter.flush();
    }
}
