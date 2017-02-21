package Connection;


import java.io.*;

/**
 * Created by Makc on 19.02.2017.
 */
public class SettReader {
    private BufferedReader reader = null;

    public int[] readFile()
    {
        try{
            reader = new BufferedReader(new FileReader(new File("C:\\project\\CheckRow\\file.txt")));
            if(reader.ready())
            {
                int columnes = Integer.parseInt(reader.readLine());
                int lines = Integer.parseInt(reader.readLine());
                int[] result = new int[] {columnes, lines};
                return result;
            }
            reader.close();
        }catch (FileNotFoundException e)
        {
            System.out.println("ФАЙЛ НЕ НАЙДЕН");
            e.printStackTrace();
        }catch (IOException e)
        {
            System.out.println("ОШИБКА");
        }
        return new int[]{ 0, 0};
    }
}
