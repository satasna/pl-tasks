public class Main {
    public static void main(String[] args) {
        if (args.length > 1)
            if (!args[0].isEmpty() && !args[1].isEmpty()) {
                int n = Integer.parseInt(args[0]);
                int m = Integer.parseInt(args[1]);

                modularArithmetic(n, m);
            } else
                System.out.print("Uncorrected values");
        else
            System.out.print("Use 2 arguments: Numbers");
    }

    public static void modularArithmetic(int a, int b) {
        int i = 1;
        if (a != 0)
            while (true) {
                System.out.print(i);
                i = 1 + (i + b - 2) % a;
                if (i == 1)
                    break;
            }
    }
}