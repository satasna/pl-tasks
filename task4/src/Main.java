import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 0)
            if (!args[0].isEmpty()) {
                File file1 = new File(args[0]);

                if (file1.exists()) {
                    int[] num_array = new int[(int)Files.lines(Paths.get(file1.getPath())).count()];
                    int median;
                    int res = 0;

                    BufferedReader br = new BufferedReader(new FileReader(file1));
                    String st;
                    for (int i = 0; (st = br.readLine()) != null; i++) {
                        if (!st.isEmpty())
                            num_array[i] = Integer.parseInt(st);
                    }

                    Arrays.sort(num_array);
                    if (num_array.length % 2 == 0)
                        median = (int)Math.round((double)(num_array[num_array.length/2] + num_array[num_array.length/2 - 1])/2);
                    else
                        median = (int)Math.round((double)num_array[num_array.length/2]);

                    for (int i = 0; i < num_array.length; i++)
                        if (num_array[i] != median)
                            res += Math.abs(num_array[i] - median);

                    System.out.println(res);
                } else
                    System.out.print("File not found");
            } else
                System.out.print("Uncorrected values");
        else
            System.out.print("1 argument needed: Paths in form \"C:\\...\"");
    }
}