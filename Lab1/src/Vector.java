public class Vector {
    private double[] array;

    //Конструктор с параметром
    public Vector(int length)
    {
        array = new double[length];
    }
    //Методы доступа и установки значения вектора
    public double get(int i)
    {
        return array[i];
    }
    public void set(int i, double value)
    {
        array[i] = value;
    }
    //Метод возвращающий длину вектора
    public int getLength()
    {
        return array.length;
    }
    //Методы поиска max и min элементов
    public double findMin()
    {
        var min = array[0];
        for (var item: array)
        {
            if (item < min)
            {
                min = item;
            }
        }
        return min;
    }
    public double findMax()
    {
        var max = array[0];
        for (var item: array)
        {
            if (item > max)
            {
                max = item;
            }
        }
        return max;
    }
    //Метод сортировки вектора
    public void sort()
    {
        for (int i = 0; i < array.length - 1; i++)
        {
            for (int j = 0; j < array.length - i - 1; j++)
            {
                if (array[j + 1] < array[j])
                {
                    double temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }
        }
    }
    //Метод возвращающий норму вектора
    public double norm()
    {
        double norm = 0.0;
        for(var item: array)
        {
            norm += item * item;
        }
        return Math.sqrt(norm);
    }
    //Метод умножения вектора на число
    public Vector multiple(double k, Vector vector) {
        Vector result = new Vector(vector.getLength());
        for(int i = 0; i < vector.getLength(); i++) {
            result.set(i, vector.get(i) * k);
        }
        return result;
    }
    //Метод для суммы векторов
    public Vector sum(Vector v) {
        if (this.array.length != v.array.length) {
            throw new IllegalArgumentException("Векторы разной длины!");
        }

        Vector result = new Vector(this.array.length);
        for (int i = 0; i < array.length; i++) {
            result.array[i] = this.array[i] + v.array[i];
        }
        return result;
    }
    //Метод возвращающий скаляр вектора
    public double scalar(Vector v)
    {
        double scalar = 0;
        for (int i = 0; i < array.length; i++)
        {
            scalar += v.get(i)*v.get(i);
        }
        return scalar;
    }
    //Метод вывода вектора на экран
    public void print()
    {
        for (int i = 0; i < array.length; i++)
        {
            System.out.print(array[i] + " ");
        }
    }
}
