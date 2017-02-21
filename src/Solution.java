import Connection.SettReader;

import java.util.Formatter;

/**
 * Created by Makc on 19.02.2017.
 */
public class Solution {

    public static void main(String[] args)
    {
        SettReader reader = new SettReader();
        int[] size = reader.readFile();
        System.out.println(new Formatter().format("Колонок: %d \n Строк: %d", size[0], size[1]));
    }
}
