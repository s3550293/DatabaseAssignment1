import javax.sound.midi.SysexMessage;

public class dbquery{
    private static int pagesize = 32768;

    private static int convert(String s) throws NumberFormatException{
        return Integer.parseInt(s);
    }

    //MAIN ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    public static void main(String[] args) {
        System.out.println(args.length);
        if(args.length > 1){
            try{
                pagesize = convert(args[1]);
            }catch(java.lang.NumberFormatException pe){
                System.out.println(pe.getMessage());
            }
        }
        
        System.out.println(args[0]);
        System.out.println(pagesize);
    }
}