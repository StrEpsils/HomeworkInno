package ru.cherkasov.w2d5;

import javassist.CannotCompileException;

public class Starter {

    static javassist.ClassPool cp = javassist.ClassPool.getDefault();

    public static void main(String[] args) throws CannotCompileException {
//        generateOOM();

        oomMetaspace();
    }

    /**
     * Убиваем JVM Metaspace
     * @throws CannotCompileException
     */
    private static void oomMetaspace() throws CannotCompileException {
        //-XX:MaxMetaspaceSize=64m
        for (int i = 0; i < 100000; i++) {
            Class c = cp.makeClass("ru.cherkasov.w2d5.Starter" + i).toClass();
        }
    }

    /**
     * Убиваем память JVM (Java heap space)
     * @throws Exception
     */
    static void generateOOM() {
        StringBuilder s = new StringBuilder();

        while (true) {
            s.append("dummy");
        }
    }


}
