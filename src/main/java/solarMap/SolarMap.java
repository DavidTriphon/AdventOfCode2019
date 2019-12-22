package solarMap;

import java.util.*;


public class SolarMap
{
   private HashMap <String, String>           _orbits;
   private HashMap <String, HashSet <String>> _connections = new HashMap <>();
   private HashMap <String, Integer>          _levels      = new HashMap <>();
   
   
   public SolarMap(Map <String, String> orbitMap)
   {
      // copy orbits map
      _orbits = new HashMap <>(orbitMap);
      
      // generate levels
      for (String body : _orbits.keySet())
      {
         generateBodyLevels(body);
      }
      
      // generate connection graph
      for (String body : _levels.keySet())
      {
         _connections.put(body, new HashSet<>());
      }
      
      for (Map.Entry<String, String> pair : _orbits.entrySet())
      {
         _connections.get(pair.getKey()).add(pair.getValue());
         _connections.get(pair.getValue()).add(pair.getKey());
      }
   }
   
   public Set<String> getBodies()
   {
      return _levels.keySet();
   }
   
   public int getBodyLevel(String body)
   {
      return _levels.get(body);
   }
   
   
   public int getDistanceBetween(String body1, String body2)
   {
      HashMap<String, Integer> distances = new HashMap <>();
      
      distances.put(body1, 0);
      fillTree(distances, body1, 1);
      
      return distances.get(body2);
   }
   
   private int generateBodyLevels(String body)
   {
      if (_levels.containsKey(body))
         return _levels.get(body);
   
      if (!_orbits.containsKey(body))
      {
         _levels.put(body, 0);
         return 0;
      }
   
      String parentBody = _orbits.get(body);
      int parentLevel = generateBodyLevels(parentBody);
      _levels.put(body, parentLevel + 1);
      return parentLevel + 1;
   }
   
   private void fillTree(HashMap<String, Integer> distances, String body, int distance)
   {
      for (String body2 : _connections.get(body))
      {
         if (!distances.containsKey(body2))
         {
            distances.put(body2, distance);
            fillTree(distances, body2, distance + 1);
         }
      }
   }
}
