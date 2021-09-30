package dev.ruster.td2;

import org.jetbrains.annotations.NotNull;

import java.time.Month;
import java.util.Arrays;
import java.util.Scanner;

public class Main {

    private static final Scanner scan = new Scanner(System.in);

    public static void main(String[] args) {
        days_before_years_start();
        scan.close();
    }

    public static void poker() {
        System.out.print("A : ");
        int a = scan.nextInt();
        System.out.print("B : ");
        int b = scan.nextInt();
        System.out.print("C : ");
        int c = scan.nextInt();
        System.out.print("D : ");
        int d = scan.nextInt();

        if(a == b && b == c && c == d) {
            System.out.println("C'est un carré !");
        }/* else if(a == b && b == c && c != d) {
            System.out.println("C'est un brelin !");
        }*/
        int[] arr = {a, b, c, d};

        for(int i : arr) {
            if(Arrays.stream(arr).anyMatch(dist -> dist == i)) {
                System.out.println("C'est un brelan");
                break;
            }
        }
        if(a == b && b != c && c != d) {
            System.out.println("C'est une paire !");
        } else if(a == b && b != c /*&& c == d*/) {
            System.out.println("C'est une double paire !");
        } else if(a != b && b != c && c != d) {
            System.out.println("Pas de chance !");
        }
    }

    public static void even_numbers_count() {
        int n, count = 0;

        do {
            System.out.print("Saisir une valeur : ");
            n = scan.nextInt();

            if(n % 2 == 0) {
                count++;
            }
        } while(n != 0);

        System.out.println("Parmis les nombres que vous avez énuméré, il y a " + (count - 1) + " nombres paires");
    }

    public static void max_value() {
        int max = 0;

        for(int i = 0; i < 20; i++) {
            System.out.print((i + 1) + "° valeur : ");
            int n = scan.nextInt();

            if(n > max) {
                max = n;
            }
        }
        System.out.println("La plus grande valeur est » " + max);
    }

    public static void polynom_squares_degree_2() {
        System.out.print("Valeur de a : ");
        double a = scan.nextDouble();
        System.out.print("Valeur de b : ");
        double b = scan.nextDouble();
        System.out.print("Valeur de c : ");
        double c = scan.nextDouble();

        double disc = b * b - 4 * a * c;

        if(disc < 0) {
            System.out.println("Aucune solution réelle");
            return;
        }
        double t1 = (-b - Math.sqrt(disc)) / (2 * a);
        double t2 = (-b + Math.sqrt(disc)) / (2 * a);

        System.out.println("Les solutions de l'équation du second degré " +
                a + "x² + " + b + "x + " + c + " sont " +
                Math.round(t1 * 100.0) / 100.0 + " et " +
                Math.round(t2 * 100.0) / 100.0
        );
    }

    public static void polynom_squares_degree_3() {
        int count = 0;

        System.out.print("Valeur de a : ");
        double a = scan.nextDouble();
        System.out.print("Valeur de b : ");
        double b = scan.nextDouble();
        System.out.print("Valeur de c : ");
        double c = scan.nextDouble();
        System.out.print("Valeur de d : ");
        double d = scan.nextInt();

        System.out.println("Voici les solutions de " + a + "x^3 + " + b + "x^2 + " + c + "x + " + d + " :");
        for(int i = -100; i < 100; i++) {
            if(count >= 3) break;
            boolean equals = Math.pow(a, 3) + Math.pow(b, 2) + c + d == i;

            if(equals) {
                System.out.println("» " + i);
                count++;
            }
        }
    }

    public static void reading() {

    }

    public static void closer_sum() {
        boolean overflow = false;
        int sum = 0;
        int z = 0;
        int nb;

        do {
            System.out.print("nb : ");
            nb = scan.nextInt();
        } while(nb <= 0);

        while(!overflow) {
            z++;
            sum += z;

            if(sum > nb) {
                overflow = true;
                sum = nb;
            }
        }
        System.out.println("sum = " + sum);
    }

    public static void bisect_year() {
        System.out.print("Donne moi une année : ");
        int year = scan.nextInt();
        boolean bisect = year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
        System.out.println("Année " + (bisect ? "bissextile" : "non bissextile"));
    }

    public static boolean isBisectYear(int year) {
        return year % 4 == 0 && year % 100 != 0 || year % 400 == 0;
    }

    public static void date_validity() {
        System.out.print("Ecrire une date sous la forme (j, m, a) : ");
        String date = scan.nextLine();

        String[] content = date.replace("(", "").replace(")", "").split(", ");
        int[] values = new int[content.length];
        boolean correct = true;

        for(int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(content[i]);
        }
        Month month = Month.of(values[1]);

        if(values[0] > month.length(isBisectYear(values[2]))) {
            correct = false;
        }
        if(values[1] < 1 || values[1] > 12) {
            correct = false;
        }
        if(values[2] < 0) {
            correct = false;
        }
        System.out.println(correct ? "vrai" : "faux");
    }

    public static int @NotNull [] scan_date() {
        System.out.print("Ecrire une date sous la forme (j, m, a) : ");
        String date = scan.nextLine();

        String[] content = date.replace("(", "").replace(")", "").split(", ");
        int[] values = new int[content.length];

        for(int i = 0; i < values.length; i++) {
            values[i] = Integer.parseInt(content[i]);
        }
        return values;
    }

    public static void days_before_years_start() {
        int[] date = scan_date();
        int days = date[0];

        for(int i = 1; i < date[1]; i++) {
            days += Month.of(i).length(isBisectYear(date[2]));
        }
        System.out.println(days + " jours se sont écoulés depuis le début de l'année jusqu'au (" +
                Arrays.toString(date).replace("[", "").replace("]", "") + ")");
    }
}