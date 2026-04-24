
public class Simulator {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Memory ram = new Memory();
        CPU cpu = new CPU(ram);

        System.out.println("--- 16-BIT CPU SIMULATOR BOOT SEQUENCE ---");

        // 1. Load raw data into memory addresses 20 and 21
        ram.write(20, 50); // The number 50 is stored at address 20
        ram.write(21, 15); // The number 15 is stored at address 21

        // 2. Load the Machine Code program into the start of memory (Address 0+)
        
        // Instruction 0: LOAD 20 (Opcode 1, Operand 20) -> Load 50 into AC
        ram.write(0, (1 << 12) | 20); 
        
        // Instruction 1: SUB 21 (Opcode 4, Operand 21) -> Subtract 15 from AC (50 - 15 = 35)
        ram.write(1, (4 << 12) | 21);
        
        // Instruction 2: STORE 22 (Opcode 2, Operand 22) -> Store AC (35) into address 22
        ram.write(2, (2 << 12) | 22);
        
        // Instruction 3: OUTPUT (Opcode 5, Operand 0) -> Print AC to console
        ram.write(3, (5 << 12) | 0);
        
        // Instruction 4: HALT (Opcode 0, Operand 0) -> Stop the CPU
        ram.write(4, (0 << 12) | 0);

        // 3. Execute the program
        System.out.println("Executing Program in Memory...\n");
        cpu.run();
        
        // 4. Verify memory state
        System.out.println("\n--- SIMULATION COMPLETE ---");
        System.out.println("Value successfully stored at memory address 22: " + ram.read(22));
	}

}
