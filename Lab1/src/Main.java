import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader(new InputStreamReader(System.in));
        //Ввод длины вектора(рисунок 1)
        System.out.print("Введите длину вектора: ");
        int length = Integer.parseInt(reader.readLine());
        var vector = new Vector(length);
        //Заполнение вектора(рисунок 1)
        System.out.println("Заполните вектор");
        for (int i = 0; i < length; i++)
        {
            double val = Double.parseDouble(reader.readLine());
            vector.set(i, val);
        }
        Boolean key = false;
        String menu;
        //пользовательское меню(рисунок 1)
        while (key == false) {
            System.out.print("Меню\n" +
                    "" +
                    "\n" +
                    "1) Поиск максимального и минимального элемента\n" +
                    "2) Доступ к элементу\n" +
                    "3) Длина вектора\n" +
                    "4) Умножение на число\n" +
                    "5) Сумма\n" +
                    "6) Норма\n" +
                    "7) Сортировка\n" +
                    "8) Скаляр\n" +
                    "0) Выход\n\n" +
                    "Выберите пункт меню: ");
            menu = reader.readLine();
            switch (menu) {
                //выход
                case "0": {
                    key = true;
                    break;
                }
                case "1": {
                    //Вывод max и min элементов(рисунок 2)
                    System.out.println("Минимальный элемент: " + vector.findMin());
                    System.out.println("Максимальный элемент: " + vector.findMax());
                    break;
                }
                case "2": {
                    //Доступ к элементу(рисунок 3)
                    System.out.print("Введите индекс элемента: ");
                    var x = Integer.parseInt(reader.readLine());
                    System.out.println("Элемент: " + vector.get(x));
                    System.out.print("Введите новое значение ");
                    var y = Integer.parseInt(reader.readLine());
                    vector.set(x, y);
                    vector.print();
                    break;
                }
                case "3": {
                    //Вывод длины(рисунок 4)
                    System.out.println("Длина вектора: " + vector.getLength());
                    break;
                }
                case "4": {
                    //Умножение вектора на число(рисунок 5)
                    System.out.print("Число для умножения: ");
                    var x = Double.parseDouble(reader.readLine());
                    Vector res = vector.multiple(x, vector);
                    res.print();
                    System.out.println();
                    break;
                }
                case "5":
                {
                    //Сумма векторов(рисунок 6)
                    Vector res = vector.sum(vector);
                    res.print();
                    System.out.println();
                    break;
                }
                case "6":
                {
                    //Вывод нормы(рисунок 7)
                    System.out.println("Евклидова норма: " + vector.norm());
                    break;
                }
                case "8":
                {
                    //Скалярное произведение(рисунок 8)
                    System.out.println("Скалярное произведение: " + vector.scalar(vector));
                    break;
                }
                case "7":
                {
                    //Сортировка вектора(рисунок 9)
                    System.out.print("До сортировки: ");
                    vector.print();
                    System.out.print("После сортировки: ");
                    vector.sort();
                    vector.print();
                    System.out.println();
                }
            }
        }
    }
}
