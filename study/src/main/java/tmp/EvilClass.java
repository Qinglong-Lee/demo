package tmp;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.util.Hashtable;

public class EvilClass implements ObjectFactory {
    static void log(String key) {
        try {
            System.out.println("EvilClass: " + key);
        } catch (Exception e) {
            // do nothing
        }
    }

    {
        EvilClass.log("IIB block");
    }

    static {
        System.out.println("==== static ====");
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        for (StackTraceElement stackTraceElement : stackTrace) {
            System.out.println(stackTraceElement.toString());
        }
        System.out.println("==== static ====");
    }

    public EvilClass() {
        EvilClass.log("constructor");
    }

    @Override
    public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception{
        EvilClass.log("getObjectInstance");
        return null;
    }

}

