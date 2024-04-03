/*
package com.example.demo.spring.aop.aspect.cglibGen;

import com.example.demo.spring.aop.aspect.AspectBean..EnhancerBySpringCGLIB..cdec3835;
import java.lang.reflect.InvocationTargetException;
import org.aopalliance.aop.Advice;
import org.springframework.aop.Advisor;
import org.springframework.aop.TargetSource;
import org.springframework.cglib.core.Signature;
import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.reflect.FastClass;

public class AspectBean$$EnhancerBySpringCGLIB$$cdec3835$$FastClassBySpringCGLIB$$2f80c054 extends FastClass {

   public AspectBean$$EnhancerBySpringCGLIB$$cdec3835$$FastClassBySpringCGLIB$$2f80c054(Class var1) {
      super(var1);
   }

   public int getIndex(Signature var1) {
      String var10000 = var1.toString();
      switch(var10000.hashCode()) {
      case -2103996935:
         if(var10000.equals("isExposeProxy()Z")) {
            return 23;
         }
         break;
      case -1938347695:
         if(var10000.equals("getTargetClass()Ljava/lang/Class;")) {
            return 36;
         }
         break;
      case -1891827894:
         if(var10000.equals("getProxiedInterfaces()[Ljava/lang/Class;")) {
            return 12;
         }
         break;
      case -1887170608:
         if(var10000.equals("getTargetSource()Lorg/springframework/aop/TargetSource;")) {
            return 35;
         }
         break;
      case -1870561232:
         if(var10000.equals("CGLIB$findMethodProxy(Lorg/springframework/cglib/core/Signature;)Lorg/springframework/cglib/proxy/MethodProxy;")) {
            return 33;
         }
         break;
      case -1745842178:
         if(var10000.equals("setCallbacks([Lorg/springframework/cglib/proxy/Callback;)V")) {
            return 45;
         }
         break;
      case -1656914424:
         if(var10000.equals("indexOf(Lorg/aopalliance/aop/Advice;)I")) {
            return 4;
         }
         break;
      case -1641413109:
         if(var10000.equals("newInstance([Lorg/springframework/cglib/proxy/Callback;)Ljava/lang/Object;")) {
            return 7;
         }
         break;
      case -1411812934:
         if(var10000.equals("CGLIB$hashCode$4()I")) {
            return 43;
         }
         break;
      case -1378580207:
         if(var10000.equals("toProxyConfigString()Ljava/lang/String;")) {
            return 13;
         }
         break;
      case -1127359161:
         if(var10000.equals("setExposeProxy(Z)V")) {
            return 18;
         }
         break;
      case -1068101097:
         if(var10000.equals("setPreFiltered(Z)V")) {
            return 22;
         }
         break;
      case -1034266769:
         if(var10000.equals("CGLIB$SET_STATIC_CALLBACKS([Lorg/springframework/cglib/proxy/Callback;)V")) {
            return 31;
         }
         break;
      case -1025895669:
         if(var10000.equals("CGLIB$SET_THREAD_CALLBACKS([Lorg/springframework/cglib/proxy/Callback;)V")) {
            return 32;
         }
         break;
      case -1012917840:
         if(var10000.equals("addAdvice(ILorg/aopalliance/aop/Advice;)V")) {
            return 27;
         }
         break;
      case -1006102474:
         if(var10000.equals("isProxyTargetClass()Z")) {
            return 16;
         }
         break;
      case -988317324:
         if(var10000.equals("newInstance([Ljava/lang/Class;[Ljava/lang/Object;[Lorg/springframework/cglib/proxy/Callback;)Ljava/lang/Object;")) {
            return 8;
         }
         break;
      case -980141523:
         if(var10000.equals("replaceAdvisor(Lorg/springframework/aop/Advisor;Lorg/springframework/aop/Advisor;)Z")) {
            return 19;
         }
         break;
      case -904152374:
         if(var10000.equals("removeAdvisor(I)V")) {
            return 25;
         }
         break;
      case -872603166:
         if(var10000.equals("removeAdvisor(Lorg/springframework/aop/Advisor;)Z")) {
            return 24;
         }
         break;
      case -552893142:
         if(var10000.equals("addAdvisor(ILorg/springframework/aop/Advisor;)V")) {
            return 29;
         }
         break;
      case -508378822:
         if(var10000.equals("clone()Ljava/lang/Object;")) {
            return 3;
         }
         break;
      case -439517399:
         if(var10000.equals("isPreFiltered()Z")) {
            return 20;
         }
         break;
      case 12917806:
         if(var10000.equals("testAspect1()V")) {
            return 11;
         }
         break;
      case 12947597:
         if(var10000.equals("testAspect2()V")) {
            return 10;
         }
         break;
      case 103043491:
         if(var10000.equals("getAdvisorCount()I")) {
            return 39;
         }
         break;
      case 105274628:
         if(var10000.equals("CGLIB$testAspect1$1()V")) {
            return 14;
         }
         break;
      case 133873988:
         if(var10000.equals("CGLIB$testAspect2$0()V")) {
            return 15;
         }
         break;
      case 187947380:
         if(var10000.equals("setTargetSource(Lorg/springframework/aop/TargetSource;)V")) {
            return 17;
         }
         break;
      case 374345669:
         if(var10000.equals("CGLIB$equals$2(Ljava/lang/Object;)Z")) {
            return 44;
         }
         break;
      case 610042816:
         if(var10000.equals("newInstance(Lorg/springframework/cglib/proxy/Callback;)Ljava/lang/Object;")) {
            return 6;
         }
         break;
      case 644726458:
         if(var10000.equals("isInterfaceProxied(Ljava/lang/Class;)Z")) {
            return 37;
         }
         break;
      case 1132856532:
         if(var10000.equals("getCallbacks()[Lorg/springframework/cglib/proxy/Callback;")) {
            return 30;
         }
         break;
      case 1238545150:
         if(var10000.equals("indexOf(Lorg/springframework/aop/Advisor;)I")) {
            return 5;
         }
         break;
      case 1246779367:
         if(var10000.equals("setCallback(ILorg/springframework/cglib/proxy/Callback;)V")) {
            return 46;
         }
         break;
      case 1364367423:
         if(var10000.equals("getCallback(I)Lorg/springframework/cglib/proxy/Callback;")) {
            return 34;
         }
         break;
      case 1426835813:
         if(var10000.equals("addAdvice(Lorg/aopalliance/aop/Advice;)V")) {
            return 26;
         }
         break;
      case 1517819849:
         if(var10000.equals("CGLIB$toString$3()Ljava/lang/String;")) {
            return 42;
         }
         break;
      case 1636583119:
         if(var10000.equals("isFrozen()Z")) {
            return 9;
         }
         break;
      case 1744736673:
         if(var10000.equals("addAdvisor(Lorg/springframework/aop/Advisor;)V")) {
            return 28;
         }
         break;
      case 1826985398:
         if(var10000.equals("equals(Ljava/lang/Object;)Z")) {
            return 0;
         }
         break;
      case 1913648695:
         if(var10000.equals("toString()Ljava/lang/String;")) {
            return 1;
         }
         break;
      case 1984935277:
         if(var10000.equals("hashCode()I")) {
            return 2;
         }
         break;
      case 2011844968:
         if(var10000.equals("CGLIB$clone$5()Ljava/lang/Object;")) {
            return 41;
         }
         break;
      case 2061418941:
         if(var10000.equals("CGLIB$STATICHOOK15()V")) {
            return 40;
         }
         break;
      case 2119600998:
         if(var10000.equals("removeAdvice(Lorg/aopalliance/aop/Advice;)Z")) {
            return 21;
         }
         break;
      case 2122046924:
         if(var10000.equals("getAdvisors()[Lorg/springframework/aop/Advisor;")) {
            return 38;
         }
      }

      return -1;
   }

   public int getIndex(String var1, Class[] var2) {
      String var10001;
      switch(var1.hashCode()) {
      case -1776922004:
         if(var1.equals("toString")) {
            switch(var2.length) {
            case 0:
               return 1;
            }
         }
         break;
      case -1567161245:
         if(var1.equals("isProxyTargetClass")) {
            switch(var2.length) {
            case 0:
               return 16;
            }
         }
         break;
      case -1383403876:
         if(var1.equals("removeAdvice")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("org.aopalliance.aop.Advice")) {
                  return 21;
               }
            }
         }
         break;
      case -1295482945:
         if(var1.equals("equals")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("java.lang.Object")) {
                  return 0;
               }
            }
         }
         break;
      case -1260627944:
         if(var1.equals("setPreFiltered")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("boolean")) {
                  return 22;
               }
            }
         }
         break;
      case -1137093050:
         if(var1.equals("toProxyConfigString")) {
            switch(var2.length) {
            case 0:
               return 13;
            }
         }
         break;
      case -1100160537:
         if(var1.equals("testAspect1")) {
            switch(var2.length) {
            case 0:
               return 11;
            }
         }
         break;
      case -1100160536:
         if(var1.equals("testAspect2")) {
            switch(var2.length) {
            case 0:
               return 10;
            }
         }
         break;
      case -1053468136:
         if(var1.equals("getCallbacks")) {
            switch(var2.length) {
            case 0:
               return 30;
            }
         }
         break;
      case -592051094:
         if(var1.equals("isFrozen")) {
            switch(var2.length) {
            case 0:
               return 9;
            }
         }
         break;
      case -560688532:
         if(var1.equals("replaceAdvisor")) {
            switch(var2.length) {
            case 2:
               if(var2[0].getName().equals("org.springframework.aop.Advisor") && var2[1].getName().equals("org.springframework.aop.Advisor")) {
                  return 19;
               }
            }
         }
         break;
      case -529128199:
         if(var1.equals("addAdvice")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("org.aopalliance.aop.Advice")) {
                  return 26;
               }
               break;
            case 2:
               if(var2[0].getName().equals("int") && var2[1].getName().equals("org.aopalliance.aop.Advice")) {
                  return 27;
               }
            }
         }
         break;
      case -258655664:
         if(var1.equals("isPreFiltered")) {
            switch(var2.length) {
            case 0:
               return 20;
            }
         }
         break;
      case -124978608:
         if(var1.equals("CGLIB$equals$2")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("java.lang.Object")) {
                  return 44;
               }
            }
         }
         break;
      case -60403779:
         if(var1.equals("CGLIB$SET_STATIC_CALLBACKS")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("[Lorg.springframework.cglib.proxy.Callback;")) {
                  return 31;
               }
            }
         }
         break;
      case -29025554:
         if(var1.equals("CGLIB$hashCode$4")) {
            switch(var2.length) {
            case 0:
               return 43;
            }
         }
         break;
      case 64168604:
         if(var1.equals("removeAdvisor")) {
            switch(var2.length) {
            case 1:
               var10001 = var2[0].getName();
               switch(var10001.hashCode()) {
               case 104431:
                  if(var10001.equals("int")) {
                     return 25;
                  }
                  break;
               case 554907597:
                  if(var10001.equals("org.springframework.aop.Advisor")) {
                     return 24;
                  }
               }
            }
         }
         break;
      case 71818497:
         if(var1.equals("getProxiedInterfaces")) {
            switch(var2.length) {
            case 0:
               return 12;
            }
         }
         break;
      case 85179481:
         if(var1.equals("CGLIB$SET_THREAD_CALLBACKS")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("[Lorg.springframework.cglib.proxy.Callback;")) {
                  return 32;
               }
            }
         }
         break;
      case 94756189:
         if(var1.equals("clone")) {
            switch(var2.length) {
            case 0:
               return 3;
            }
         }
         break;
      case 147696667:
         if(var1.equals("hashCode")) {
            switch(var2.length) {
            case 0:
               return 2;
            }
         }
         break;
      case 232340296:
         if(var1.equals("setExposeProxy")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("boolean")) {
                  return 18;
               }
            }
         }
         break;
      case 495524492:
         if(var1.equals("setCallbacks")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("[Lorg.springframework.cglib.proxy.Callback;")) {
                  return 45;
               }
            }
         }
         break;
      case 726974136:
         if(var1.equals("CGLIB$STATICHOOK15")) {
            switch(var2.length) {
            case 0:
               return 40;
            }
         }
         break;
      case 776910815:
         if(var1.equals("addAdvisor")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("org.springframework.aop.Advisor")) {
                  return 28;
               }
               break;
            case 2:
               if(var2[0].getName().equals("int") && var2[1].getName().equals("org.springframework.aop.Advisor")) {
                  return 29;
               }
            }
         }
         break;
      case 811662670:
         if(var1.equals("isInterfaceProxied")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("java.lang.Class")) {
                  return 37;
               }
            }
         }
         break;
      case 1055615909:
         if(var1.equals("getAdvisorCount")) {
            switch(var2.length) {
            case 0:
               return 39;
            }
         }
         break;
      case 1081419650:
         if(var1.equals("getTargetSource")) {
            switch(var2.length) {
            case 0:
               return 35;
            }
         }
         break;
      case 1154623345:
         if(var1.equals("CGLIB$findMethodProxy")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("org.springframework.cglib.core.Signature")) {
                  return 33;
               }
            }
         }
         break;
      case 1234312576:
         if(var1.equals("isExposeProxy")) {
            switch(var2.length) {
            case 0:
               return 23;
            }
         }
         break;
      case 1366611497:
         if(var1.equals("getAdvisors")) {
            switch(var2.length) {
            case 0:
               return 38;
            }
         }
         break;
      case 1543336190:
         if(var1.equals("CGLIB$toString$3")) {
            switch(var2.length) {
            case 0:
               return 42;
            }
         }
         break;
      case 1544020273:
         if(var1.equals("getTargetClass")) {
            switch(var2.length) {
            case 0:
               return 36;
            }
         }
         break;
      case 1636909265:
         if(var1.equals("CGLIB$testAspect1$1")) {
            switch(var2.length) {
            case 0:
               return 14;
            }
         }
         break;
      case 1636910225:
         if(var1.equals("CGLIB$testAspect2$0")) {
            switch(var2.length) {
            case 0:
               return 15;
            }
         }
         break;
      case 1811874389:
         if(var1.equals("newInstance")) {
            switch(var2.length) {
            case 1:
               var10001 = var2[0].getName();
               switch(var10001.hashCode()) {
               case -1997738671:
                  if(var10001.equals("[Lorg.springframework.cglib.proxy.Callback;")) {
                     return 7;
                  }
                  break;
               case 1364160985:
                  if(var10001.equals("org.springframework.cglib.proxy.Callback")) {
                     return 6;
                  }
               }
            case 2:
            default:
               break;
            case 3:
               if(var2[0].getName().equals("[Ljava.lang.Class;") && var2[1].getName().equals("[Ljava.lang.Object;") && var2[2].getName().equals("[Lorg.springframework.cglib.proxy.Callback;")) {
                  return 8;
               }
            }
         }
         break;
      case 1817099975:
         if(var1.equals("setCallback")) {
            switch(var2.length) {
            case 2:
               if(var2[0].getName().equals("int") && var2[1].getName().equals("org.springframework.cglib.proxy.Callback")) {
                  return 46;
               }
            }
         }
         break;
      case 1905679803:
         if(var1.equals("getCallback")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("int")) {
                  return 34;
               }
            }
         }
         break;
      case 1943291465:
         if(var1.equals("indexOf")) {
            switch(var2.length) {
            case 1:
               var10001 = var2[0].getName();
               switch(var10001.hashCode()) {
               case -201664093:
                  if(var10001.equals("org.aopalliance.aop.Advice")) {
                     return 4;
                  }
                  break;
               case 554907597:
                  if(var10001.equals("org.springframework.aop.Advisor")) {
                     return 5;
                  }
               }
            }
         }
         break;
      case 1951977611:
         if(var1.equals("CGLIB$clone$5")) {
            switch(var2.length) {
            case 0:
               return 41;
            }
         }
         break;
      case 1992172174:
         if(var1.equals("setTargetSource")) {
            switch(var2.length) {
            case 1:
               if(var2[0].getName().equals("org.springframework.aop.TargetSource")) {
                  return 17;
               }
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
      cdec3835 var10000 = (cdec3835)var2;
      int var10001 = var1;

      try {
         switch(var10001) {
         case 0:
            return new Boolean(var10000.equals(var3[0]));
         case 1:
            return var10000.toString();
         case 2:
            return new Integer(var10000.hashCode());
         case 3:
            return var10000.clone();
         case 4:
            return new Integer(var10000.indexOf((Advice)var3[0]));
         case 5:
            return new Integer(var10000.indexOf((Advisor)var3[0]));
         case 6:
            return var10000.newInstance((Callback)var3[0]);
         case 7:
            return var10000.newInstance((Callback[])var3[0]);
         case 8:
            return var10000.newInstance((Class[])var3[0], (Object[])var3[1], (Callback[])var3[2]);
         case 9:
            return new Boolean(var10000.isFrozen());
         case 10:
            var10000.testAspect2();
            return null;
         case 11:
            var10000.testAspect1();
            return null;
         case 12:
            return var10000.getProxiedInterfaces();
         case 13:
            return var10000.toProxyConfigString();
         case 14:
            var10000.CGLIB$testAspect1$1();
            return null;
         case 15:
            var10000.CGLIB$testAspect2$0();
            return null;
         case 16:
            return new Boolean(var10000.isProxyTargetClass());
         case 17:
            var10000.setTargetSource((TargetSource)var3[0]);
            return null;
         case 18:
            var10000.setExposeProxy(((Boolean)var3[0]).booleanValue());
            return null;
         case 19:
            return new Boolean(var10000.replaceAdvisor((Advisor)var3[0], (Advisor)var3[1]));
         case 20:
            return new Boolean(var10000.isPreFiltered());
         case 21:
            return new Boolean(var10000.removeAdvice((Advice)var3[0]));
         case 22:
            var10000.setPreFiltered(((Boolean)var3[0]).booleanValue());
            return null;
         case 23:
            return new Boolean(var10000.isExposeProxy());
         case 24:
            return new Boolean(var10000.removeAdvisor((Advisor)var3[0]));
         case 25:
            var10000.removeAdvisor(((Number)var3[0]).intValue());
            return null;
         case 26:
            var10000.addAdvice((Advice)var3[0]);
            return null;
         case 27:
            var10000.addAdvice(((Number)var3[0]).intValue(), (Advice)var3[1]);
            return null;
         case 28:
            var10000.addAdvisor((Advisor)var3[0]);
            return null;
         case 29:
            var10000.addAdvisor(((Number)var3[0]).intValue(), (Advisor)var3[1]);
            return null;
         case 30:
            return var10000.getCallbacks();
         case 31:
            cdec3835.CGLIB$SET_STATIC_CALLBACKS((Callback[])var3[0]);
            return null;
         case 32:
            cdec3835.CGLIB$SET_THREAD_CALLBACKS((Callback[])var3[0]);
            return null;
         case 33:
            return cdec3835.CGLIB$findMethodProxy((Signature)var3[0]);
         case 34:
            return var10000.getCallback(((Number)var3[0]).intValue());
         case 35:
            return var10000.getTargetSource();
         case 36:
            return var10000.getTargetClass();
         case 37:
            return new Boolean(var10000.isInterfaceProxied((Class)var3[0]));
         case 38:
            return var10000.getAdvisors();
         case 39:
            return new Integer(var10000.getAdvisorCount());
         case 40:
            cdec3835.CGLIB$STATICHOOK15();
            return null;
         case 41:
            return var10000.CGLIB$clone$5();
         case 42:
            return var10000.CGLIB$toString$3();
         case 43:
            return new Integer(var10000.CGLIB$hashCode$4());
         case 44:
            return new Boolean(var10000.CGLIB$equals$2(var3[0]));
         case 45:
            var10000.setCallbacks((Callback[])var3[0]);
            return null;
         case 46:
            var10000.setCallback(((Number)var3[0]).intValue(), (Callback)var3[1]);
            return null;
         }
      } catch (Throwable var4) {
         throw new InvocationTargetException(var4);
      }

      throw new IllegalArgumentException("Cannot find matching method/constructor");
   }

   public Object newInstance(int var1, Object[] var2) throws InvocationTargetException {
      cdec3835 var10000 = new cdec3835;
      cdec3835 var10001 = var10000;
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
      return 46;
   }
}
*/
