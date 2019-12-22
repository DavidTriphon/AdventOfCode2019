package cpu;

public enum ParameterMode
{
   // states
   
   POSITION(0, '&')
     {
        @Override
        public int getAddress(Program program, long param)
        {
           return (int) param;
        }
     },
   IMMEDIATE(1, '#')
     {
        @Override
        public long getValue(Program program, long param)
        {
           return param;
        }
        
        
        @Override
        public int getAddress(Program program, long param)
        {
           throw new IllegalStateException(
             "Immediate Parameter Mode cannot be called to create an address");
        }
     },
   RELATIVE(2, '@')
     {
        @Override
        public int getAddress(Program program, long param)
        {
           return (int) (param + program.getRelPtr());
        }
     };
   
   // instance fields
   
   public final  int  code;
   private final char _symbol;
   
   // constructor
   
   
   ParameterMode(int code, char symbol)
   {
      this.code = code;
      _symbol = symbol;
   }
   
   // abstract methods
   
   
   public abstract int getAddress(Program program, long param);
   
   // default methods
   
   
   public long getValue(Program program, long param)
   {
      return program.getCode(getAddress(program, param));
   }
   
   
   public String getNotation(int param)
   {
      return ("" + _symbol) + param;
   }
   
   // public static methods
   
   
   public static ParameterMode getModeFromCode(long code, int paramNum)
   {
      code = (code / (int) Math.pow(10, 1 + paramNum) % 10);
      
      for (ParameterMode mode : values())
      {
         if (mode.code == code)
            return mode;
      }
      
      throw new IllegalArgumentException("Invalid parameter mode code: " + code);
   }
}
