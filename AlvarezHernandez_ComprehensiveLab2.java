import java.util.Random;
import java.util.Scanner;

public class AlvarezHernandez_ComprehensiveLab2{
    static int dungeonRows = 4;
    static int dungeonColumns = 4;
    static int lesserMonsters = 2;
    static int dragon = 1;

    public static int Hp = 5;
    public static boolean ShieldStatus = false;
    public static boolean SwordStatus = false;
    public static int Sword = 1;
    public static int Shield = 2;

    public static int i = 0;
    public static int e = 0;
    public static int LastRow = 0;
    public static int LastColumn = 0;

    public static boolean DragonStatus = true;
    public static int DragonValue = 3;


    public static void main(String[] args){

        //Your code STARTS HERE
        int[][] Arr = new int[4][4];
        int[][]randomArray = getMonstersLocation();

        Arr[randomArray[0][0]][randomArray[0][1]] = Sword;
        Arr[randomArray[1][0]][randomArray[1][1]] = Shield;
        Arr[randomArray[2][0]][randomArray[2][1]] = DragonValue;

        System.out.println("Oh no!");
        System.out.println("A dragon is killing our sheep and scaring our villagers, please help" + "\n" + "the knight put a stop to this nightmare");
        System.out.println();

        Moving(Arr);  
        //Your code ENDS HERE
    }

    //Your methods START HERE
    public static void Moving(int Arr[][]){
        Scanner scnr = new Scanner(System.in);
        String option = "";
        String [][] board = new String[4][4];
        do{
            while(i >= 0 && i < Arr.length){
                while(e >= 0 && e < Arr[0].length){
                    if(DragonStatus == false || Hp <= 0){
                        break;
                    }

                    System.out.println("You are in cell " + i + " , " + e);
                    LastRow = i;
                    LastColumn = e;

                    for(int j = 0; j < board.length; j++){
                        for(int k = 0; k < board[0].length; k++){
                            if(j == i && k == e){
                                board[j][k] = "C";
                            }
                            else if(board[j][k] == "C"){
                                board[j][k] = "E";
                            }
                            else if(board[j][k] == "E"){
                                board[j][k] = "E";
                            }
                            else{
                                board[j][k] = "?";
                            }
                            System.out.print(board[j][k] + " ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("Your remaining health points are: " + Hp);
                    System.out.println("Shield acquired: " + ShieldStatus);
					System.out.println("Sword acquired: " + SwordStatus);
					System.out.println ("Where would you like to move now?");
					System.out.println ("Use L to move left");
					System.out.println ("Use R to move right");
					System.out.println ("Use U to move up");
					System.out.println ("Use D to move down");
                    System.out.println("\n" + "If you want to exit enter E");

                    option = scnr.nextLine();
                    if(OptionEntered(option)){
                        if(option.equalsIgnoreCase("l")){
                            e = e - 1;
                            if(e < 0){
                                System.out.println("Enter a option in range");
                                e = e + 1;
                            }
                        }
                        else if(option.equalsIgnoreCase("r")){
                            e = e + 1;
                            if(e > Arr[0].length - 1){
                                System.out.println("Enter a option in range");
                                e = e - 1;
                            }
                        }
                        else if(option.equalsIgnoreCase("u")){
                            i = i - 1;
                            if( i < 0){
                                System.out.println("Enter a option in range");
                                i = i + 1;
                            }
                        }
                        else if(option.equalsIgnoreCase("d")){
                            i = i + 1;
                            if(i > Arr.length - 1){
                                System.out.println("Enter a option in range");
                                i = i - 1;
                            }
                        }
                        else if(option.equalsIgnoreCase("e")){
                            break;
                        }

                        EnterARoom(Arr);
                        System.out.println();
                    }
                    else{
                        System.out.println("Please enter a valid input");
                    }
                }
                break;
            }
            break;    
        }while (DragonStatus || Hp > 0 || !"e".equalsIgnoreCase(option));
    }

    public static boolean OptionEntered(String j){
        if(j.equalsIgnoreCase("l") || j.equalsIgnoreCase("r") || j.equalsIgnoreCase("u") || j.equalsIgnoreCase("d") || j.equalsIgnoreCase("e")){
            return true;
        }
        else{
            return false;
        }
    }

    public static void EnterARoom(int Arr[][]){
        int optionSword = 0;
        int optionShield = 0;
        int optionDragon = 0;

        if(Arr[i][e] == 0){
            System.out.println("You have entered an empty room, looks like there's nothing in here");
        }
        else if(Arr[i][e] == Sword || Arr[i][e] == Shield){
            String Item = "";

            if(Arr[i][e] == Sword){
                Item = "Sword";
                System.out.println("The mythical " + Item + " is located in this room!");
                System.out.println("A monster is guarding it.");

                optionSword = MonsterOption(Item);

                if(optionSword == 1){
                    SwordStatus = true;
                    Arr[i][e] = 0;
                    return;
                }
                else if(optionSword == 2){
                    i = LastRow;
                    e = LastColumn;
                    return;
                }
                else if(optionSword == 3){
                    return;
                }
            }
            else if(Arr[i][e] == Shield){
                Item = "Shield";
                System.out.println("The mythical " + Item + " is located in this room!");
                System.out.println("A monster is monster is guarding it.");

                optionShield = MonsterOption(Item);

                if(optionShield == 1){
                    ShieldStatus = true;
                    Arr[i][e] = 0;
                    return;
                }
                else if(optionShield == 2){
                    i = LastRow;
                    e = LastColumn;
                    return;
                }
                else if(optionShield == 3){
                    return;
                }
            }
        }
        else if(Arr[i][e] == DragonValue){
            System.out.println("The dragon is located in this room!");

            optionDragon = DragonFound();

            if(optionDragon == 1){
                DragonStatus = false;
                Arr[i][e] = 0;
                return;
            }
            else if(optionDragon == 2){
                i = LastRow;
                e = LastColumn;
                return;
            }
            else if(optionDragon == 3){
                return;
            }
        }
    }

    public static int MonsterOption(String Item){
        Scanner scnr = new Scanner(System.in);
        String option = "";
        do{
            System.out.println("What would you like to do:");
            System.out.println("a) Do nothing");
            System.out.println("b) Hit the enemy");
            System.out.println("c) Run away");
            option = scnr.nextLine();

            if(OptionEnemy(option)){
                if(option.equalsIgnoreCase("a")){
                    System.out.println("You have chosen to do nothing.");
                    System.out.println("The monster hit you.");
                    Hp = Hp - 1; // Coment: monsters take 1 of hp
                    System.out.println("Your remaining health point are: " + Hp);
                    if (Hp <= 0){
                        System.out.println("The monster killed you");
                        System.out.println("Game Over..."); //probably this can be outside the loop in the menu method bc everytime it goes out means end.
                        return 3;
                    }
                }
                else if(option.equalsIgnoreCase("b")){
                    System.out.println("You have chosen to hit the monster.");
                    System.out.println("The monster hit you."); // substract life and set remaining hp as in line 147-149
                    Hp = Hp - 1;
                    System.out.println("Your remaining health points are: " + Hp);
                    if(Hp <= 0){
                        System.out.println("The monster killed you");
                        System.out.println("Game over..."); //see line 151
                        return 3;
                    }
                    else{
                        System.out.println("You have defeated the monster and adquired the " + Item + "!");
                        return 1;
                    }
                }
                else if(option.equalsIgnoreCase("c")){
                    System.out.println("You have chosen to run away.");
                    return 2;
                }
            }
            else{
                System.out.println("Please enter a valid input");
            }
        }while(option.equalsIgnoreCase("a") || OptionEnemy(option) == false);
        return 0;
    }
    
    public static int DragonFound(){
        Scanner scnr = new Scanner(System.in);
        String option = "";
        do{
            System.out.println("What would you like to do:");
            System.out.println("a) Do nothing");
            System.out.println("b) Hit the enemy");
            System.out.println("c) Run away");
            option = scnr.nextLine();

            if(OptionEnemy(option)){
                if(option.equalsIgnoreCase("a")){
                    System.out.println("You have chosen to do nothing.");
                    if(SwordStatus == false || ShieldStatus == false){
                        if(SwordStatus == false && ShieldStatus == false){
                            System.out.println("You are missing the sword and shield");
                        }
                        else if(SwordStatus == false && ShieldStatus == true){
                            System.out.println("You are missing the sword.");
                        }
                        else if(SwordStatus == true && ShieldStatus == false){
                            System.out.println("You are missing the shield.");
                        }
                        System.out.println("The dragon killed you...");
                        Hp = 0;
                        System.out.println("Game over...");
                        return 3;
                    } //What happens if has the shield and sword and still does nothing
                    else if(SwordStatus == true && ShieldStatus == true){
                        System.out.println("The dragon hit you");
                        Hp = Hp - 2;
                        System.out.println("Your remaining health points are: " + Hp);
                        if(Hp <= 0){
                            System.out.println("The dragon killed you");
                            System.out.println("Game over...");
                            return 3;
                        }
                    }
                }
                else if(option.equalsIgnoreCase("b")){
                    System.out.println("You have chosen to hit the dragon.");
                    if(SwordStatus == false || ShieldStatus == false){
                        if(SwordStatus == false && ShieldStatus == false){
                            System.out.println("You are missing the sword and shield");
                        }
                        else if(SwordStatus == false && ShieldStatus == true){
                            System.out.println("You are missing the sword.");
                        }
                        else if(SwordStatus == true && ShieldStatus == false){
                            System.out.println("You are missing the shield.");
                        }
                        System.out.println("The dragon killed you...");
                        Hp = 0;
                        System.out.println("Game over...");
                        return 3;
                    }
                    else if(SwordStatus == true && ShieldStatus == true){
                        Hp = Hp - 2;

                        if(Hp <= 0){
                            System.out.println("The dragon hit you");
                            System.out.println("Your remaining health points are: " + Hp);
                            System.out.println("You have defeated the dragon!");
                            System.out.println("You have saved the village but died in the process");
                            System.out.println("Game over...");
                            return 1;
                        }
                        else{
                            System.out.println("The dragon hit you.");
                            System.out.println("Your remaining health points are: " + Hp);
                            System.out.println("You have defeated the dragon!");
                            System.out.println("Congratulations, you have survived this quest!");
                            System.out.println("Game over...");
                            return 1;
                        }
                    }
                }
                else if(option.equalsIgnoreCase("c")){
                    System.out.println("You have chosen to run away.");
                    return 2;
                }
            }
            else{
                System.out.println("Please enter a valid input");
            }
        }while(option.equalsIgnoreCase("a") || OptionEnemy(option) == false);
        return 0;
    }

    public static boolean OptionEnemy(String j){
        if(j.equalsIgnoreCase("a") || j.equalsIgnoreCase("b") || j.equalsIgnoreCase("c")){
            return true;
        }
        else{
            return false;
        }
    }
    //Your methods END HERE

    //Do not change anything below this comment
    /*
    *   The following method returns a 2-D array
    *   The first set of row,column is the location of the monster guarding the shield
    *   The second set of row,column is the location of the monster guarding the sword
    *   The third set of row,column is the location of the dragon
    *   The minimum size for the dungeon must be 4x4, this is set in the dungeonColumns and dungeonRows global variables
    */
    private static int[][] getMonstersLocation(){
        if(dungeonColumns < 4 || dungeonRows < 4){
            System.out.println("Minimum size for the dungeon must be 4x4");
            return null;
        }

        int[][] monstersLocation = new int[lesserMonsters + dragon][2];
        Random rand = new Random();

        for(int i = 0; i < lesserMonsters + dragon; i++){
            int row = rand.nextInt(dungeonRows);
            int column = rand.nextInt(dungeonColumns);
            if((row == 0 && column == 0) || (i != 0 && monsterLocationDuplicate(i + 1, row, column, monstersLocation))){
                int columnDuplicatedValue = column;
                while(column == columnDuplicatedValue || (i != 0 && monsterLocationDuplicate(i + 1, row, column, monstersLocation)))
                    column = rand.nextInt(dungeonColumns);
            }
            monstersLocation[i][0] = row;
            monstersLocation[i][1] = column;
        }
        return monstersLocation;
    }

    /**
     * Returns true if a monster is already placed in the current cell (row, column)
     */
    private static boolean monsterLocationDuplicate(int monsters, int row, int column, int[][] monstersLocation){
        for(int i = 0; i < monsters; i++){
            if(monstersLocation[i][0] == row && monstersLocation[i][1] == column)
                return true;
        }
        return false;
    }
}