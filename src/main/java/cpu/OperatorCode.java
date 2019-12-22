package cpu;

public enum OperatorCode
{
   ADD(1)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           long value2 = program.nextValue(mode2);
           int addr3 = program.nextAddress(mode3);
           
           program.setCode(addr3, value1 + value2);
           
           Program.log(String.format("&%d = %d + %d", addr3, value1, value2));
           
           return true;
        }
     },
   MULTIPLY(2)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           long value2 = program.nextValue(mode2);
           int addr3 = program.nextAddress(mode3);
           
           program.setCode(addr3, value1 * value2);
           
           Program.log(String.format("&%d = %d * %d", addr3, value1, value2));
           
           return true;
        }
     },
   END(99)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           program.halt();
           
           Program.log("HALT");
           
           return false;
        }
     },
   INPUT(3)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           int addr1 = program.nextAddress(mode1);
           
           if (program.hasInput())
           {
              program.setCode(addr1, program.getInput());
           }
           else
           {
              program.setInstPtr(program.getInstPtr() - 2);
              
              Program.log("WARN: waiting input...");
              
              return false;
           }
           
           Program.log(String.format("&%d <= input", addr1));
           
           return true;
        }
     },
   OUTPUT(4)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           
           program.addOutput(value1);
           
           Program.log(String.format("output <= %d", value1));
           
           return true;
        }
     },
   JUMP_IF_TRUE(5)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           long value2 = program.nextValue(mode2);
           
           if (value1 != 0)
              program.setInstPtr((int) value2);
           
           Program.log(String.format("if (%d != 0): inst_ptr = %d", value1, value2));
           
           return true;
        }
     },
   JUMP_IF_FALSE(6)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           long value2 = program.nextValue(mode2);
           
           if (value1 == 0)
              program.setInstPtr((int) value2);
           
           Program.log(String.format("if (%d == 0): inst_ptr = %d", value1, value2));
           
           return true;
        }
     },
   LESS_THAN(7)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           long value2 = program.nextValue(mode2);
           int addr3 = program.nextAddress(mode3);
           
           program.setCode(addr3, (value1 < value2 ? 1 : 0));
           
           Program.log(String.format("&%d = (%d < %d)", addr3, value1, value2));
           
           return true;
        }
     },
   EQUALS(8)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           long value2 = program.nextValue(mode2);
           int addr3 = program.nextAddress(mode3);
           
           program.setCode(addr3, (value1 == value2 ? 1 : 0));
           
           Program.log(String.format("&%d = (%d == %d)", addr3, value1, value2));
           
           return true;
        }
     },
   SET_REL_PTR(9)
     {
        @Override
        public boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
          ParameterMode mode3)
        {
           long value1 = program.nextValue(mode1);
           
           program.setRelPtr((int) value1 + program.getRelPtr());
           
           Program.log(String.format("rel_ptr += %d", value1));
           
           return true;
        }
     };
   
   // instance fields
   
   public final int code;
   
   // constructor
   
   
   OperatorCode(int code)
   {
      this.code = code;
   }
   
   // private methods
   
   
   abstract boolean compute(Program program, ParameterMode mode1, ParameterMode mode2,
     ParameterMode mode3);
   
   // public static methods
   
   
   public static boolean compute(Program program, long code)
   {
      Program.log(String.format("@%03d: ", program.getInstPtr()));
      
      int op = (int) (code % 100);
      
      ParameterMode mode1 = ParameterMode.getModeFromCode(code, 1);
      ParameterMode mode2 = ParameterMode.getModeFromCode(code, 2);
      ParameterMode mode3 = ParameterMode.getModeFromCode(code, 3);
      
      Program.log(String.format("[%02d:%d:%d:%d] ", op, mode1.code, mode2.code, mode3.code));
      
      OperatorCode opCode = getOpFromCode(op);
      
      Program.log(String.format("%s: ", opCode));
      
      boolean res = opCode.compute(program, mode1, mode2, mode3);
      
      Program.log("\n");
      
      return res;
   }
   
   // private static methods
   
   
   private static OperatorCode getOpFromCode(int code)
   {
      for (OperatorCode op : values())
      {
         if (op.code == code)
            return op;
      }
      
      throw new IllegalArgumentException("Invalid operatorCode code: " + code);
   }
}
