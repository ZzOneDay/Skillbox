public class Main {
    public static void main(String[] args) {
        int vasiaAge = 30;
        int katyAge = 25;
        int mishaAge = 20;

        int min = -1;
        int midl = -1;
        int max = -1;

        boolean haveClone = false;

        /**
         * Возможно, можно было сделать как то по умнее и по короче,
         * но зато, я думаю, этот код просто читается,
         * но я посидел подумал, я не скажу что это правильно, но, мой метод MyAgeApp кое как, но работает)
         * потому и пошел проходить курс еще раз, так как уже плохо помню все эти List...
         */

        if (vasiaAge == katyAge || vasiaAge == mishaAge || mishaAge == katyAge) {
            haveClone = true;
            if (vasiaAge == katyAge) {
                if (mishaAge > vasiaAge) {
                    max = mishaAge;
                    midl = min = vasiaAge;
                } else {
                    min = mishaAge;
                    midl = max = vasiaAge;
                }
            } else if (vasiaAge == mishaAge) {
                if (katyAge > vasiaAge) {
                    max = katyAge;
                    midl = min = vasiaAge;
                } else {
                    max = midl = vasiaAge;
                    min = katyAge;
                }
            } else
            //misha == katia
            {
                if (katyAge > vasiaAge) {
                    max = katyAge;
                    min = midl = vasiaAge;
                }
                {
                    max = midl = vasiaAge;
                    min = katyAge;
                }
            }

        } else if (vasiaAge == mishaAge && mishaAge == katyAge && vasiaAge == katyAge) {
            min = midl = max = vasiaAge;
        } else {
            if (vasiaAge > katyAge && vasiaAge > mishaAge) {
                max = vasiaAge;
                if (katyAge > mishaAge) {
                    midl = katyAge;
                    min = mishaAge;
                } else {
                    midl = mishaAge;
                    min = katyAge;
                }
            }

            if (katyAge > vasiaAge && katyAge > mishaAge) {
                max = katyAge;
                if (vasiaAge > mishaAge) {
                    midl = vasiaAge;
                    min = mishaAge;
                } else {
                    midl = mishaAge;
                    min = vasiaAge;
                }
            }
            if (mishaAge > vasiaAge && mishaAge > katyAge) {
                max = mishaAge;
                if (vasiaAge > katyAge) {
                    midl = vasiaAge;
                    min = katyAge;
                } else {
                    midl = katyAge;
                    min = vasiaAge;
                }
            }
        }

        if (haveClone) {
            System.out.println("Vasia " + vasiaAge + ";katia " + katyAge + ";misha " + mishaAge);
            System.out.println("min " + min + "; max " + max);
        } else {
            System.out.println("Vasia " + vasiaAge + ";katia " + katyAge + ";misha " + mishaAge);
            System.out.println("min " + min + "; midl " + midl + "; max " + max);

        }

        //===========================================================================================
        MyAgeApp myAgeApp = new MyAgeApp(vasiaAge,katyAge,mishaAge);
        System.out.println(myAgeApp.getStringLevelAge());

    }
}
