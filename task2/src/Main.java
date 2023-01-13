import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 1)
            if (!args[0].isEmpty() && !args[1].isEmpty()) {
                File file1 = new File(args[0]);
                File file2 = new File(args[1]);
                if ((file1.exists() && file1.isFile()) &&
                        file2.exists() && file2.isFile()) {
                    float x_circle = 0;
                    float y_circle = 0;
                    float r_circle = 0;

                    float o_sum = x_circle + y_circle;

                    int point_count = (int) Files.lines(Paths.get(file2.getPath())).count();
                    float[] x_point = new float[point_count];
                    float[] y_point = new float[point_count];
                    String st;

                    BufferedReader br = new BufferedReader(new FileReader(file1));
                    while ((st = br.readLine()) != null) {
                        String[] splited = st.split("\\s+");
                        if (splited.length == 2) {
                            x_circle = Float.parseFloat(splited[0]);
                            y_circle = Float.parseFloat(splited[1]);
                        } else
                            if (splited.length == 1)
                                r_circle = Float.parseFloat(splited[0]);
                    }

                    br = new BufferedReader(new FileReader(file2));
                    for (int i = 0; (st = br.readLine()) != null; i++) {
                        String[] splited = st.split("\\s+");
                        if (splited.length == 2) {
                            x_point[i] = Float.parseFloat(splited[0]);
                            y_point[i] = Float.parseFloat(splited[1]);
                        }
                    }

                    pythagoreanTheorem(point_count,x_point,y_point,o_sum,r_circle);
                } else
                    System.out.print("File not found");
            } else
                System.out.print("Uncorrected values");
        else
            System.out.print("Use 2 arguments: Paths in form \"C:\\...\"");
    }

    public static void pythagoreanTheorem (int p_cnt, float[] x_pnt, float[] y_pnt, float o_sm, float r_crcl) {
        for (int i = 0; i < p_cnt; i++) {
            double sqrt = Math.sqrt(Math.pow(x_pnt[i], 2) + Math.pow(y_pnt[i], 2));

            if ((o_sm + sqrt) == o_sm + r_crcl)
                System.out.println(0);
            else if ((o_sm + sqrt) <= o_sm + r_crcl)
                System.out.println(1);
            else
                System.out.println(2);
        }
    }
}