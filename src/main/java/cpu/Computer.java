package cpu;

public class Computer
{
   private static final int OP_ADD    = 1;
   private static final int OP_MUL    = 2;
   private static final int OP_INPUT  = 3;
   private static final int OP_OUTPUT = 4;
   private static final int OP_END    = 99;
   
   private static boolean logging = false;
   
   // static methods
   
   
   public static Integer[] compute(Integer[] initMemory)
   {
      Integer[] memory = initMemory.clone();
      
      for (int i = 0; i < memory.length; i++)
      {
         log(String.format("%03d: ", i));
         int op = memory[i++];
         
         switch (op)
         {
            case OP_ADD:
            {
               int r1 = memory[i++];
               int r2 = memory[i++];
               int rd = memory[i];
               
               memory[rd] = memory[r1] + memory[r2];
               
               log(String
                 .format("ADD: [op:%02d, r%03d + r%03d = r%03d]: %d + %d = %d\n", op, r1, r2, rd,
                   memory[r1], memory[r2], memory[rd]));
            }
            break;
            case OP_MUL:
            {
               int r1 = memory[i++];
               int r2 = memory[i++];
               int rd = memory[i];
               
               memory[rd] = memory[r1] * memory[r2];
               
               log(String
                 .format("MUL: [op:%02d, r%03d * r%03d = r%03d]: %d * %d = %d\n", op, r1, r2, rd,
                   memory[r1], memory[r2], memory[rd]));
            }
            break;
            case OP_END:
            {
               i = memory.length;
               
               log(String.format("END: [op:%02d]\n", op));
            }
            break;
            default:
            {
               log(String.format("UNRECOGNIZED OP: %d", op));
            }
            break;
         }
      }
      
      return memory;
   }
   
   
   private static final void log(String string)
   {
      if (logging)
         System.out.print(string);
   }
   
   
   public static String formatMemory(Integer[] ints)
   {
      StringBuilder sb = new StringBuilder();
      
      for (int i = 0; i < ints.length; i++)
      {
         sb.append(ints[i]);
         
         if (i + 1 < ints.length)
         {
            sb.append(',');
         }
      }
      
      return sb.toString();
   }
}
