package cpu;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;


public class Program
{
   // constants
   
   // fields
   
   private ArrayList <Long> _memory;
   private ArrayList <Long> _output;
   private Iterator <Long>  _inputFeed;
   
   private int     _instructionPtr = 0;
   private int     _relativePtr    = 0;
   private boolean _isHalted       = false;
   
   // constructor
   
   
   public Program()
   {
      setMemory(new Long[0]);
      setInput(new Long[0]);
      _output = new ArrayList <>();
   }
   
   // public methods
   
   
   public void setMemory(Long[] memory)
   {
      _memory = new ArrayList <>(Arrays.asList(memory));
      _instructionPtr = 0;
   }
   
   
   public void setInput(Long[] inputSequence)
   {
      _inputFeed = Arrays.asList(inputSequence).iterator();
   }
   
   
   public Long[] getMemory()
   {
      return _memory.toArray(new Long[0]);
   }
   
   
   public Long[] getOutput()
   {
      return _output.toArray(new Long[0]);
   }
   
   
   public void compute()
   {
      while (OperatorCode.compute(this, nextCode()))
      {
      
      }
   }
   
   // private methods
   
   
   public boolean isHalted()
   {
      return _isHalted;
   }
   
   // package private methods
   
   
   private long nextCode()
   {
      return getCode(_instructionPtr++);
   }
   
   
   long getCode(int address)
   {
      while (_memory.size() <= address)
      {
         _memory.add(0L);
      }
      
      return _memory.get(address);
   }
   
   
   long nextValue(ParameterMode mode)
   {
      long value = mode.getValue(this, getCode(_instructionPtr));
      _instructionPtr++;
      return value;
   }
   
   
   int nextAddress(ParameterMode mode)
   {
      int address = mode.getAddress(this, getCode(_instructionPtr));
      _instructionPtr++;
      return address;
   }
   
   
   void setCode(int address, long value)
   {
      while (_memory.size() <= address)
      {
         _memory.add(0L);
      }
      
      _memory.set(address, value);
   }
   
   
   void setInstPtr(int ptr)
   {
      _instructionPtr = ptr;
   }
   
   
   int getInstPtr()
   {
      return _instructionPtr;
   }
   
   
   void setRelPtr(int ptr)
   {
      _relativePtr = ptr;
   }
   
   
   int getRelPtr()
   {
      return _relativePtr;
   }
   
   
   void halt()
   {
      _isHalted = true;
   }
   
   
   void addOutput(long value)
   {
      _output.add(value);
   }
   
   
   boolean hasInput()
   {
      return _inputFeed.hasNext();
   }
   
   
   long getInput()
   {
      return _inputFeed.next();
   }
   
   // public static methods
   
   
   public static Long[] getMemoryFromFile(String fileLoc) throws IOException
   {
      String fileString = Files.readString(Path.of(fileLoc), StandardCharsets.US_ASCII).trim();
      ArrayList <Long> opCodesArray = new ArrayList <>();
      Arrays.asList(fileString.split(",")).forEach((i) -> opCodesArray.add(Long.parseLong(i)));
      return opCodesArray.toArray(new Long[0]);
   }
   
   // package private static methods
   
   
   static void log(String string)
   {
      boolean logging = false;
      if (logging)
         System.out.print(string);
   }
}
