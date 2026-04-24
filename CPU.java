
public class CPU {
	private int pc = 0; // Program Counter: tracks where we are in memory
    private int ac = 0; // Accumulator: holds math/logic results
    private int ir = 0; // Instruction Register: holds the current machine code
    
    private Memory memory;
    private boolean running = true;

    public CPU(Memory memory) {
        this.memory = memory;
    }

    public void run() {
        while (running) {
            fetch();
            decodeAndExecute();
        }
    }

    private void fetch() {
        // Fetch the 16-bit instruction from memory at the current PC address
        ir = memory.read(pc);
        pc++; // Increment PC for the next CPU cycle
    }

    private void decodeAndExecute() {
        // Bitwise operations to split the 16-bit instruction
        // Top 4 bits = Opcode (Command), Bottom 12 bits = Operand (Memory Address)
        // For example, if the instruction is 0100 0000 0001 0101
        // The opcode is 0100 (4 - SUB) and the operand is 000000010101 (21)
        int opcode = (ir & 0xF000) >> 12; 
        int operand = ir & 0x0FFF;        

        switch (opcode) {
            case 0: // HALT (Stop CPU)
                running = false;
                System.out.println("CPU: HALT instruction received.");
                break;
            case 1: // LOAD (Load value from memory into AC)
                ac = memory.read(operand);
                break;
            case 2: // STORE (Store AC value into memory)
                memory.write(operand, ac);
                break;
            case 3: // ADD (Add memory value to AC)
                ac += memory.read(operand);
                break;
            case 4: // SUB (Subtract memory value from AC)
                ac -= memory.read(operand);
                break;
            case 5: // OUTPUT (Print AC to console)
                System.out.println("CPU OUTPUT -> " + ac);
                break;
            default:
                System.err.println("CPU ERROR: Unknown Opcode " + opcode);
                running = false;
        }
    }
}
