import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class compiler {

    public static String machine_code = "0000";

    public static void main(String[] args) {
        ArrayList<String> code = new ArrayList<String>();
        if(args.length!=0){
            File file = new File(args[0]); 
        
            try(Scanner reader = new Scanner(file)) {
                while(reader.hasNextLine()) {
                    code.add(reader.nextLine());
                }
            } catch(FileNotFoundException e) {
                System.out.println("File not found :(");
            }
        }

        int index = 0;
        ArrayList<Integer> number_lines = new ArrayList<Integer>();
        number_lines.add(1);
        for(String line: code) {
            number_lines.add(0);
            index++;
            String[] temp = line.split(" ");

            if(temp[0].compareTo("add")==0) {
                machine_code+=" ";
                machine_code+="5"+temp[2]+"00";
                machine_code+=" 1"+temp[1]+"00";
                number_lines.set(index, number_lines.get(index)+2);

            } else if(temp[0].compareTo("and")==0) {
                machine_code+=" ";
                machine_code+="5"+temp[2]+"00";
                machine_code+=" 2"+temp[1]+"00";
                number_lines.set(index, number_lines.get(index)+2);
                
            } else if(temp[0].compareTo("clro")==0) {
                machine_code+=" 6eff";
                machine_code+=" 8e00";
                number_lines.set(index, number_lines.get(index)+2);
                
            } else if(temp[0].compareTo("or")==0) {
                machine_code+=" ";
                machine_code+="5"+temp[2]+"00";
                machine_code+=" 3"+temp[1]+"00";
                number_lines.set(index, number_lines.get(index)+2);
                
            } else if(temp[0].compareTo("xor")==0) {
                machine_code+=" ";
                machine_code+="5"+temp[2]+"00";
                machine_code+=" 4"+temp[1]+"00";
                number_lines.set(index, number_lines.get(index)+2);
                
            } else if(temp[0].compareTo("sub")==0) {
                machine_code+=" ";
                machine_code+="5"+temp[2]+"00";
                machine_code+=" 7"+temp[1]+"00";
                number_lines.set(index, number_lines.get(index)+2);
                
            } else if(temp[0].compareTo("mov")==0) {
                machine_code+=" 6" + temp[1];
                if(Integer.parseInt(temp[2]) < 16) {
                    machine_code+="0";
                }
                machine_code+=Integer.toHexString(Integer.parseInt(temp[2]));
                number_lines.set(index, number_lines.get(index)+1);
                
            } else if(temp[0].compareTo("out")==0) {
                machine_code+=" 6e00";
                machine_code+=" 5" + temp[1] + "00";
                machine_code+=" 1e40";
                machine_code+=" 8e00";
                number_lines.set(index, number_lines.get(index)+4);
                
            } else if(temp[0].compareTo("jmp")==0) {
                int number_temp=0;

                for(int i=0; i <= Integer.parseInt(temp[1]); i++)
                    number_temp+=number_lines.get(i);
                
                machine_code+=" 6f";
                if(number_temp < 16) {
                    machine_code+="0";
                }
                machine_code+=Integer.toHexString(number_temp);
                machine_code+=" af00";
                machine_code+=" 6d00";
                machine_code+=" bd00";
                machine_code+=" c000";
                number_lines.set(index, number_lines.get(index)+5);
                
            } else if(temp[0].compareTo("jmpz")==0) {
                int number_temp=0;

                String last10 = machine_code.substring(machine_code.length()-10, machine_code.length());
                machine_code = machine_code.substring(0, machine_code.length()-10);

                for(int i=0; i <= Integer.parseInt(temp[1]); i++)
                    number_temp+=number_lines.get(i);
                
                machine_code+=" 6f";
                if(number_temp < 16) {
                    machine_code+="0";
                }
                machine_code+=Integer.toHexString(number_temp);
                machine_code+=" af00";
                machine_code+=" 6d00";
                machine_code+=" bd00";
                machine_code+=last10;
                machine_code+=" d000";
                number_lines.set(index, number_lines.get(index)+5);
                
            } else if(temp[0].compareTo("jmpnz")==0) {
                int number_temp=0;

                String last10 = machine_code.substring(machine_code.length()-10, machine_code.length());
                machine_code = machine_code.substring(0, machine_code.length()-10);

                for(int i=0; i <= Integer.parseInt(temp[1]); i++)
                    number_temp+=number_lines.get(i);
                
                machine_code+=" 6f";
                if(number_temp < 16) {
                    machine_code+="0";
                }
                machine_code+=Integer.toHexString(number_temp);
                machine_code+=" af00";
                machine_code+=" 6d00";
                machine_code+=" bd00";
                machine_code+=last10;
                machine_code+=" f000";
                number_lines.set(index, number_lines.get(index)+5);
                
            } else if(temp[0].compareTo("jmpc")==0) {
                int number_temp=0;

                String last10 = machine_code.substring(machine_code.length()-10, machine_code.length());
                machine_code = machine_code.substring(0, machine_code.length()-10);

                for(int i=0; i <= Integer.parseInt(temp[1]); i++)
                    number_temp+=number_lines.get(i);
                
                machine_code+=" 6f";
                if(number_temp < 16) {
                    machine_code+="0";
                }
                machine_code+=Integer.toHexString(number_temp);
                machine_code+=" af00";
                machine_code+=" 6d00";
                machine_code+=" bd00";
                machine_code+=last10;
                machine_code+=" e000";
                number_lines.set(index, number_lines.get(index)+5);
            }
        }

        System.out.println(machine_code);
        try {
            File program = new File("Program_Memory");
            program.createNewFile();
        } catch(IOException e) {
            System.out.println("There is a problem :(");
        }

        try(FileWriter output = new FileWriter("Program_Memory")) {
            output.write(machine_code);
        } catch(IOException e) {
            System.out.println("There is a problem :(");
        }
    }
}