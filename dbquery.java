import javax.sound.midi.SysexMessage;

public class dbquery{
    private static File inFile = null;
    private static int pagesize = 32768;
    private static ArrayList<Page> heap = new ArrayList<Page>();
    private static ExecutionTimer timer = new ExecutionTimer();

    private static int convert(String s) throws NumberFormatException{
        return Integer.parseInt(s);
    }

    private static void readArray(){
        BufferedReader reader = new BufferedReader(new FileReader(inFile));
        while ((line = reader.readLine()) != null) {
            
        }
    }

    private static void searchResulkt(){
        System.out.printf("## stdout ##\n");
        System.out.printf("%-20s %-20s %-20s %-20s %-20s %-20s %-20s %-20s\n","BN_NAME","BN_STATUS","BN_REG_DT","BN_CANCEL_DT","BN_RENEW_DT","BN_STATE_NUM","BN_STATE_OF_REG","BN_ABN");
        System.out.println("----------------------------------------------------------------------------------------------------------------------------------------------");
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
        
        infile = new File(args[0]);
    }
}