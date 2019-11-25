public class Main {
    public static void main(String[] args) {
        String[][] strings = new String[7][7];

        for (int i = 0; i < strings.length; i++)
        {
            for (int j = 0; j < strings[i].length; j++)
            {
                strings[i][j] = " ";
            }
        }

        for (int i = 0; i < strings.length; i++) {
            strings[i][strings[i].length - i-1] = "X";
            strings[i][i] = "X";
        }

        for (int i = 0; i < strings.length; i++) {
            for (int j = 0; j < strings[i].length; j++) {
                System.out.print(strings[i][j]);
            }
            System.out.println();
        }
    }
}



//        for (int i = 0; i < strings.length; i++) {
//            for (int j = 0; j < strings[i].length; j++) {
//                strings[i][j] = " ";
//            }
//        }
//                if (i == j || i == strings[i].length-1-i || j == strings[j].length-1-i) {
//                    strings[i][j] = "X";
//                    strings[i][strings[i].length-1-i] = "X";
//                }
//                else
//                {
//                    strings[i][j] = " ";
//                }
//            }
//        }
