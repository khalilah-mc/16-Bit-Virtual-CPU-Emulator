
public class Memory {
	// 4096 memory locations (12-bit address space)
    private int[] ram;

    public Memory() {
        ram = new int[4096];
    }

    // Write a value to a specific memory address
    public void write(int address, int value) {
        if (address >= 0 && address < ram.length) {
            ram[address] = value;
        }
    }

    // Read a value from a specific memory address
    public int read(int address) {
        if (address >= 0 && address < ram.length) {
            return ram[address];
        }
        return 0;
    }
}
