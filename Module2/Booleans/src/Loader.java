public class Loader {


    public static void main(String[] args) {
        int milkAmount = 2000; // ml
        int powderAmount = 5000; // g
        int eggsCount = 30000; // items
        int sugarAmount = 5000; // g
        int oilAmount = 3000; // ml
        int appleCount = 8000;

        boolean haveIngredients = true;
        boolean createdAnyThing = true;

        String createdString = "";

        int pancakes = 0;
        int omelette = 0;
        int applePie = 0;

        while (haveIngredients) {
            createdAnyThing = false;
            //powder - 400 g, sugar - 10 g, milk - 1 l, oil - 30 ml
            if (powderAmount >= 400 && sugarAmount >= 10 && milkAmount >= 1 && oilAmount >= 30) {
                pancakes += 1;
                powderAmount -= 200;
                sugarAmount -= 10;
                milkAmount -= 1;
                oilAmount -= 30;
                createdAnyThing = true;
            }

            //milk - 300 ml, powder - 5 g, eggs - 5
            if (milkAmount >= 300 && powderAmount >= 5 && eggsCount >= 5) {
                omelette += 1;
                milkAmount -= 300;
                powderAmount -= 5;
                eggsCount -= 5;
                createdAnyThing = true;
            }


            //apples - 3, milk - 100 ml, powder - 300 g, eggs - 4
            if (appleCount >= 3 && milkAmount >= 100 && powderAmount >= 300 && eggsCount >= 4) {
                applePie += 1;
                appleCount -= 3;
                milkAmount -= 100;
                powderAmount -= 300;
                eggsCount -= 4;
                createdAnyThing = true;
            } else {
                haveIngredients = false;
            }
        }

        System.out.println(createdAnyThing ? "Из ваших ингридиентов можно приготовить:" : "Ничего не приготовить");
        if (pancakes > 0) {
            createdString = pancakes + " pancakes ";
        }
        if (omelette > 0) {
            createdString += omelette + " omelette ";

        }
        if (applePie > 0) {
            createdString += applePie + " apple pie ";
        }
        System.out.println(createdString);
    }
}