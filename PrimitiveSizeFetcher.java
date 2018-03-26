class PrimitiveSizeFetcher{
    static int memSize(byte x) {
        return (Byte.SIZE/8);
    }
    static int memSize(int x) {
        return (Integer.SIZE/8);
    }
    static int memSize(String x) {
        return (8 * (int) ((((x.length()) * 2) + 45) / 8));
    }
    static int memSize(float x) {
        return (Float.SIZE/8);
    }
    static int memSize(double x) {
        return (Double.SIZE/8);
    }
    static int memSize(char x) {
        return (Character.SIZE/8);
    }
    static int memSize(boolean x) {
        return 16;
    }
}