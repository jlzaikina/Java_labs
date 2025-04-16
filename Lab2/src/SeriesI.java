public interface SeriesI {
    //доступ к строковому полю – название сериала и сезоны
    String getTitle();
    void setTitle(String var1);
    String getSeason(int var1);
    void setSeason(String var1 , int var2);
    //доступ к полю типа int – количество сезонов и серии
    int getAmountOfSeries();
    int getSeries(int var1);
    void setSeries(int var1, int var2);
    int getMainRole();
    void setMainRole(int var1);
    int getAmountOfSeason();

    int getAmountOfSeriesWithoutMainRole() throws AmountOfSeriesException;

}
