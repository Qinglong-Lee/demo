/*
package com.example.demo.spring.aop.aspect.cglibGen;

import com.example.demo.spring.aop.aspect.AspectBean;
import java.lang.reflect.InvocationTargetException;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.reflect.FastClass;

public class AspectBean$$FastClassBySpringCGLIB$$6bc5920b extends FastClass {

   public AspectBean$$FastClassBySpringCGLIB$$6bc5920b(Class var1) {
      super(var1);
   }

   public int getIndex(Signature var1) {
      String var10000 = var1.toString();
      switch(var10000.hashCode()) {
      case 12917806:
         if(var10000.equals("testAspect1()V")) {
            return 1;
         }
         break;
      case 12947597:
         if(var10000.equals("testAspect2()V")) {
            return 0;
         }
         break;
      case 1826985398:
         if(var10000.equals("equals(Ljava/lang/Object;)Z")) {
            return 2;
         }
         break;
      case 1913648695:
         if(var10000.equals("toString()Ljava/lang/String;")) {
            return 3;
         }
         break;
      case 1984935277:
         if(var10000.equals("hashCode()I")) {
            return 4;
         }
      }

      return -1;
   }

   public int getIndex(String var1, Class[] var2) {
      switch(var1.hashCode()) {
      case -1776922004:
         if(var1.equals("toString")) {
            switch(var2.length) {
            case 0:
               return 3;
            }
         }
         break;
      case -1295482945:
         if(var1.equals("equals")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("java.lang.Object")) {
                  return 2;
               }
            }
         }
         break;
      case -1100160537:
         if(var1.equals("testAspect1")) {
            switch(var2.length) {
            case 0:
               return 1;
            }
         }
         break;
      case -1100160536:
         if(var1.equals("testAspect2")) {
            switch(var2.length) {
            case 0:
               return 0;
            }
         }
         break;
      case 147696667:
         if(var1.equals("hashCode")) {
            switch(var2.length) {
            case 0:
               return 4;
            }
         }
      }

      return -1;
   }

   public int getIndex(Class[] var1) {
      switch(var1.length) {
      case 0:
         return 0;
      default:
         return -1;
      }
   }

   public Object invoke(int var1, Object var2, Object[] var3) throws InvocationTargetException {
      AspectBean var10000 = (AspectBean)var2;
      int var10001 = var1;

      try {
         switch(var10001) {
         case 0:
            var10000.testAspect2();
            return null;
         case 1:
            var10000.testAspect1();
            return null;
         case 2:
            return new Boolean(var10000.equals(var3[0]));
         case 3:
            return var10000.toString();
         case 4:
            return new Integer(var10000.hashCode());
         }
      } catch (Throwable var4) {
         throw new InvocationTargetException(var4);
      }

      throw new IllegalArgumentException("Cannot find matching method/constructor");
   }

   public Object newInstance(int var1, Object[] var2) throws InvocationTargetException {
      AspectBean var10000 = new AspectBean;
      AspectBean var10001 = var10000;
      int var10002 = var1;

      try {
         switch(var10002) {
         case 0:
            var10001.<init>();
            return var10000;
         }
      } catch (Throwable var3) {
         throw new InvocationTargetException(var3);
      }

      throw new IllegalArgumentException("Cannot find matching method/constructor");
   }

   public int getMaxIndex() {
      return 4;
   }
}
*/
