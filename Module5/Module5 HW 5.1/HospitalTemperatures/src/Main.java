public class Main {

    //Создать массив с температурами 30-ти пациентов (от 32 до 40 градусов).
    //Написать код, рассчитывающий среднюю температуру по больнице
    //и количество здоровых пациентов (с температурой от 36,2 до 36,9).


    public static void main(String[] args) {
        double[] patients = new double[30];
        for (int i = 0; i < 30; i++)
        {
            //Температуру можно измерить только до Десятых.
            double patientTemperatures = 32.0 + (8.0 * Math.random());
            patients[i] = (double) Math.round(patientTemperatures * 10) / 10;
//            System.out.println(patients[i] + ";");
        }

        System.out.println("Средняя температура в больнице: " + averageTemperatures(patients));
        System.out.println("Количество здоровых человек: " + countHealthyPatients(patients));
    }

    private static double averageTemperatures (double[] patients)
    {
        double countAllTemperatures = 0;
        for (int i = 0; i < patients.length; i++)
        {
            countAllTemperatures += patients[i];
        }
        double average = countAllTemperatures/patients.length;

        return (double) Math.round(average * 10) / 10;
    }

    private static int countHealthyPatients (double[] patients)
    {
        int countHealthyPatients = 0;
        for (int i = 0; i < patients.length; i++)
        {
            if (patients[i] > 36.2 && patients[i] < 36.9)
            {
                countHealthyPatients+=1;
            }
        }
        return countHealthyPatients;
    }
}
