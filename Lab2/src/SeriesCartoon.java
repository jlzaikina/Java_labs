public class SeriesCartoon implements SeriesI{
    private String nameOfCartoon; //поле строкового типа – название сериала
    private String[] seasonInCartoon; //строковый массив – сезоны сериала
    private int[] amountSeriesInSeason; //поле - массив – количество сериалов
    private int mainRoleInSeason; //поле целого типа – количество серий без главного героя



    public SeriesCartoon() //конструктор без параметров
    {
        this.seasonInCartoon = new String[1];
        this.amountSeriesInSeason = new int[this.seasonInCartoon.length];
        this.nameOfCartoon = "Название отстуствует";
        this.mainRoleInSeason = 1;
    }
    public SeriesCartoon(int amountSeries, String nameOfSitcom, int mainRoleInSitcom) //конструктор с параметрами
    {
        this.amountSeriesInSeason = new int[amountSeries];
        this.nameOfCartoon = nameOfSitcom;
        this.mainRoleInSeason = mainRoleInSitcom;
        this.seasonInCartoon = new String[this.amountSeriesInSeason.length];
    }
    //доступ к полям класс
    public String getTitle()
    {
        return nameOfCartoon;
    }
    public void setTitle(String title)
    {
        nameOfCartoon = title;
    }
    public String getSeason(int i)
    {
        if (i>=0 && i < seasonInCartoon.length)
            return seasonInCartoon[i];
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }
    public void setSeason(String season, int i)
    {
        if (i>=0 && i < seasonInCartoon.length)
            seasonInCartoon[i] = season;
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }
    public int getAmountOfSeries()
    {
        return amountSeriesInSeason.length;
    }
    public int getSeries(int i)
    {
        if (i>=0 && i < seasonInCartoon.length)
            return amountSeriesInSeason[i];
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }
    public void setSeries(int i, int amount)
    {
        if (i>=0 && i < seasonInCartoon.length)
            amountSeriesInSeason[i] = amount;
        else
            throw new ArrayIndexOutOfBoundsException("Задан неверный индекс");
    }
    public int getMainRole()
    {
        return mainRoleInSeason;
    }
    public void setMainRole(int amount)
    {
        mainRoleInSeason = amount;
    }
    public int getAmountOfSeason() {
        return seasonInCartoon.length;
    }
    //функциональный метод
    public int getAmountOfSeriesWithoutMainRole() throws AmountOfSeriesException
    {
        int sum = 0;

        for(int i = 0; i < amountSeriesInSeason.length; ++i)
        {
            if (amountSeriesInSeason[i] < mainRoleInSeason) {
                throw new AmountOfSeriesException("Ошибка. Общее количество серий не может быть меньше количествасерий без главного героя");
            }
            sum += amountSeriesInSeason[i];
        }

        return sum - mainRoleInSeason * amountSeriesInSeason.length;
    }
// переопределение toString

    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Название мультфильма: ").append(nameOfCartoon).append('\n');
        stringBuilder.append("Количество сезонов мультфильме: ").append(seasonInCartoon.length).append('\n');
        try{
            stringBuilder.append("Общее количество серий без учета серий без главного героя: ").append(getAmountOfSeriesWithoutMainRole()).append('\n');
            stringBuilder.append("Количество серий без главного героя: ").append(mainRoleInSeason).append('\n');
            stringBuilder.append("Информация о сериях:\n");

            for(int i = 0; i < seasonInCartoon.length; ++i) {
                stringBuilder.append((i+1)).append(") Название: ").append(seasonInCartoon[i]).append(". Количество серий: ").append(amountSeriesInSeason[i]).append(".").append("\n");
            }
        } catch (AmountOfSeriesException var) {
            stringBuilder.append(var.getMessage()).append('\n');
        }
        return stringBuilder.toString();
    }
// переопределение equals

    public boolean equals(Object o) {
        if (o != null && o.getClass() == this.getClass()) {
            SeriesCartoon newSeriesCartoon = (SeriesCartoon)o;
            boolean equals = false;
            if (!nameOfCartoon.equals(newSeriesCartoon.getTitle())) {
                return false;
            } else if (mainRoleInSeason != newSeriesCartoon.getMainRole()) {
                return false;
            } else {
                int i;
                for(i = 0; seasonInCartoon[i].equals(newSeriesCartoon.getSeason(i)); ++i) {
                }

                if (i != seasonInCartoon.length) {
                    return false;
                } else {
                    int k;
                    for(k = 0; amountSeriesInSeason[k] == newSeriesCartoon.getSeries(k); ++k) {
                    }

                    if (k == amountSeriesInSeason.length) {
                        equals = true;
                    }

                    return equals;
                }
            }
        }   else {
            return false;
        }
    }
    // переопределение hashCode
    public int hashCode() {
        return super.hashCode();
    }
}
