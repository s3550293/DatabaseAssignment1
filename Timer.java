class ExecutionTimer {
    private static long start;
    private static long end;
  
    public ExecutionTimer() {
        start = 0;
        end = 0;
    }

    public static void start(){
        start = System.currentTimeMillis();
    }
  
    public static void end() {
      end = System.currentTimeMillis();
    }
  
    public static long duration(){
      return (end-start);
    }
}